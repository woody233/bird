package com.bird.main;

/**
 * 游戏计时器
 */
public class GameTime {

    //计时
    private long beginTime;
    private long endTime;

    private long differ;

    public GameTime() {
    }

    public void begin() {
        beginTime = System.currentTimeMillis();
    }

    public long differ() {
        endTime = System.currentTimeMillis();
        return differ = (endTime - beginTime) / 1000;
    }


}
