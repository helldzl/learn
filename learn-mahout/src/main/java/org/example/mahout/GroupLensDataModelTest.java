package org.example.mahout;

import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created at 2020/8/31
 * <p>
 * <a href="https://grouplens.org/datasets/movielens/">movieLens</a>
 * </p>
 * <p>
 * <a href="https://github.com/apache/mahout/blob/9af01127cbadca3980c18974c33d1b2dbe8d44c6/website/docs/latest/tutorials/map-reduce/recommender/recommender-documentation.md"></a>
 * </p>
 *
 * @author quzile3
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
public class GroupLensDataModelTest {

    private static final String PATH = "D:\\Quzile\\23 推荐系统\\ml-10m\\ml-10M100K\\ratings.dat";

    public static void grouplensDataModel() throws IOException, TasteException {
        DataModel model = new GroupLensDataModel(new File(PATH));

        // user-based
//        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
//        UserNeighborhood neighborhood = new NearestNUserNeighborhood(100, similarity, model);
//        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        // item-based
        ItemSimilarity itemSimilarity = new LogLikelihoodSimilarity(model);
        Recommender recommender = new GenericItemBasedRecommender(model, itemSimilarity);
        // Recommender recommender = new CachingRecommender();

        // warm up
        // LoadStatistics loadStatistics = LoadEvaluator.runLoad(recommender);
        // System.out.println(loadStatistics);

        Scanner scanner = new Scanner(System.in);
        long next = scanner.nextLong();
        while (next != -1L) {
            try {
                List<RecommendedItem> recommendations = recommender.recommend(next, 3);
                for (RecommendedItem recommendation : recommendations) {
                    log.info("recommendation: {}", recommendation);
                }
            } catch (TasteException e) {
                log.error("error", e);
            } finally {
                next = scanner.nextLong();
            }
        }
    }

    public static void evaluateGroupLensDataModel() throws IOException, TasteException {
        DataModel model = new GroupLensDataModel(new File(PATH));
        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
        RecommenderBuilder builder = m -> {
            UserSimilarity similarity = new PearsonCorrelationSimilarity(m);
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(100, similarity, m);
            return new GenericUserBasedRecommender(m, neighborhood, similarity);
        };
        double score = evaluator.evaluate(builder, null, model, 0.95, 0.05);
        System.out.println(score);
    }

    public static void main(String[] args) throws Exception {
        grouplensDataModel();
        // evaluateGroupLensDataModel();
    }

}
