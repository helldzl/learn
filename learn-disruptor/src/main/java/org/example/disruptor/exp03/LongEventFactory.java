package org.example.disruptor.exp03;

import com.lmax.disruptor.EventFactory;

/**
 * Created at 2019/8/7
 *
 * @author quzile3
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        LongEvent longEvent = new LongEvent();
        System.out.println(longEvent);
        return longEvent;
    }
}
