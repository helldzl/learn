package org.example.mahout;

import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Created at 2020/7/29
 *
 * @author quzile3
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
public class SampleRecommender {

//    public static void a() throws Exception {
//        DataModel model = new FileDataModel(new File("/path/to/dataset.csv"));
//        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
//        RecommenderBuilder builder = new MyRecommenderBuilder();
//        double result = evaluator.evaluate(builder, null, model, 0.9, 1.0);
//        System.out.println(result);
//    }
//
//    public static void b() {
//        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
//        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, dataModel);
//        return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
//    }

    public static void main(String[] args) throws Exception {
        // DataModel model = new FileDataModel(new File("/path/to/dataset.csv"));
        DataModel model = new FileDataModel(new File("C:\\Users\\quzile3\\Desktop\\r.csv"));
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        Scanner scanner = new Scanner(System.in);
        long next = scanner.nextLong();
        while (next != -1L) {
            try {
                List<RecommendedItem> recommendations = recommender.recommend(next, 3);
                for (RecommendedItem recommendation : recommendations) {
                    log.info("recommendation");
                }
                next = scanner.nextLong();
            } catch (TasteException e) {
                log.error("error", e);
            }
        }
    }

}
