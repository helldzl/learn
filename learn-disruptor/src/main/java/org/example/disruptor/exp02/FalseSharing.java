package org.example.disruptor.exp02;

import sun.misc.Contended;

/**
 * Created at 2020/11/12
 *
 * @author quzile3
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
public class FalseSharing implements Runnable {

    public final static long ITERATIONS = 500L * 1000L * 100L;
    private int arrayIndex = 0;

    private static ValueNoPadding[] longs;

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    // -XX:-RestrictContended
    // com.lmax.disruptor.Sequence
    public static void main(final String[] args) throws Exception {
        for (int i = 1; i < 10; i++) {
            System.gc();
            final long start = System.currentTimeMillis();
            runTest(i);
            System.out.println("Thread num " + i + " duration = " + (System.currentTimeMillis() - start));
        }

    }

    private static void runTest(int num) throws InterruptedException {
        Thread[] threads = new Thread[num];
        longs = new ValueNoPadding[num];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new ValueNoPadding();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = 0L;
        }
    }

    public final static class ValuePadding {
        protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        protected long p9, p10, p11, p12, p13, p14;
        protected long p15;
    }

    public final static class ValueNoPadding {
        // protected long p1, p2, p3, p4, p5, p6, p7;
         @Contended
        protected volatile long value = 0L;
        // protected long p9, p10, p11, p12, p13, p14, p15;
    }

//    Thread num 1 duration = 1066
//    Thread num 2 duration = 1002
//    Thread num 3 duration = 987
//    Thread num 4 duration = 1620
//    Thread num 5 duration = 1685
//    Thread num 6 duration = 1435
//    Thread num 7 duration = 1596
//    Thread num 8 duration = 2092
//    Thread num 9 duration = 2428

//    Thread num 1 duration = 966
//    Thread num 2 duration = 1402
//    Thread num 3 duration = 1646
//    Thread num 4 duration = 2094
//    Thread num 5 duration = 2801
//    Thread num 6 duration = 3241
//    Thread num 7 duration = 3009
//    Thread num 8 duration = 3600
//    Thread num 9 duration = 2860
}
