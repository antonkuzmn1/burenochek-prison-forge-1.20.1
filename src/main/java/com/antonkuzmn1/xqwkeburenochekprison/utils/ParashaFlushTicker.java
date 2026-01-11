package com.antonkuzmn1.xqwkeburenochekprison.utils;

public class ParashaFlushTicker {
    public static final int framesCount = 17;
    public static final int ticksPerFrame = 10;

    private int currentFrame = 1;
    private int tick = 0;

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void resetCurrentFrame() {
        this.currentFrame = 0;
    }

    public void incrementCurrentFrame() {
        this.currentFrame++;
    }

    public int getTick() {
        return tick;
    }

    public void resetTick() {
        this.tick = 0;
    }

    public void incrementTick() {
        tick++;
    }
}
