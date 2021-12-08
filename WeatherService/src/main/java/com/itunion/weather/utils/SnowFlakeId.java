package com.itunion.weather.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SnowFlakeId {
    private static SnowFlakeId flowIdWorker = new SnowFlakeId(1L);
    private final long id;
    private final long epoch = 1524291141010L;
    private final long workerIdBits = 10L;
    private final long maxWorkerId;
    private final long sequenceBits;
    private final long workerIdShift;
    private final long timestampLeftShift;
    private final long sequenceMask;
    private long sequence;
    private long lastTimestamp;

    public SnowFlakeId(long id) {
        this.getClass();
        this.maxWorkerId = ~(-1L << 10);
        this.sequenceBits = 12L;
        this.getClass();
        this.workerIdShift = 12L;
        this.getClass();
        this.getClass();
        this.timestampLeftShift = 12L + 10L;
        this.getClass();
        this.sequenceMask = ~(-1L << 12);
        this.sequence = 0L;
        this.lastTimestamp = -1L;
        if (id <= this.maxWorkerId && id >= 0L) {
            this.id = id;
        } else {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
        }
    }

    public static SnowFlakeId getFlowIdInstance() {
        return flowIdWorker;
    }

    private static long timeGen() {
        return System.currentTimeMillis();
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = this.sequence + 1L & this.sequenceMask;
            if (this.sequence == 0L) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0L;
        }

        if (timestamp < this.lastTimestamp) {
            log.error(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
            return -1L;
        } else {
            this.lastTimestamp = timestamp;
            this.getClass();
            return timestamp - 1524291141010L << (int)this.timestampLeftShift | this.id << (int)this.workerIdShift | this.sequence;
        }
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for(timestamp = timeGen(); timestamp <= lastTimestamp; timestamp = timeGen()) {
        }

        return timestamp;
    }
    public static void main(String args[]) {
        SnowFlakeId snowFlakeId = SnowFlakeId.getFlowIdInstance();
        for (int i = 0;i < 10;i++){
            System.out.println(snowFlakeId.nextId());
        }
    }
}
