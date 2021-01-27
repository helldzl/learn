package org.example.disruptor.exp03;

/**
 * Created at 2019/8/7
 *
 * @author quzile3
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 */
public class LongEvent {

    private long value;

    public void set(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
