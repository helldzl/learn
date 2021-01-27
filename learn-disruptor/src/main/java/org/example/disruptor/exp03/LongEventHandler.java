package org.example.disruptor.exp03;

import com.lmax.disruptor.EventHandler;

/**
 * Created at 2019/8/7
 *
 * @author quzile3
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        try {
            Thread.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (sequence % 10 == 0) {
            throw new RuntimeException();
        }
        System.out.println("Event: " + event + ", Seq: " + sequence);
    }
}
