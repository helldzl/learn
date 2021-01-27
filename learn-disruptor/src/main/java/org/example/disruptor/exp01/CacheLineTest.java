package org.example.disruptor.exp01;

/**
 * Created at 2020/11/11
 *
 * @author quzile3
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
public class CacheLineTest {

    //考虑一般缓存行大小是64字节，一个 long 类型占8字节
    static long[][] arr;

    public static void main(String[] args) {
        int x = 1024 * 1024 * 10;
        int y = 8;
        arr = new long[x][];
        for (int i = 0; i < x; i++) {
            arr[i] = new long[y];
            for (int j = 0; j < y; j++) {
                arr[i][j] = 0L;
            }
        }

        run(x, y, (i, j) -> arr[i][j]);
        run(y, x, (i, j) -> arr[j][i]);
    }

    private static void run(int x, int y, Function function) {
        long sum = 0L;
        long marked = System.currentTimeMillis();
        for (int i = 0; i < x; i += 1) {
            for (int j = 0; j < y; j++) {
                sum = function.apply(i, j);
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");
    }

    @FunctionalInterface
    private interface Function {
        long apply(int i, int j);
    }

}
