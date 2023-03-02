package com.bird.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bird.util.GameUtil;

/**
 * 游戏的前景类
 */
public class GameFrontGround {
    //云彩的个数
    private static final int CLOUND_COUNT = 2;
    //存放云彩的容器
    private List<Cloud> clouds;
    //云彩的飞行速度
    private static final int CLOUNG_SPEED = 1;
    //使用到图片资源
    private BufferedImage[] img;

    //用于产生随机数
    private Random random;

    // 构造器初始化数据
    public GameFrontGround() {
        clouds = new ArrayList<>();
        img = new BufferedImage[CLOUND_COUNT];

        //容器中添加云彩的图片
        for (int i = 0; i < CLOUND_COUNT; i++) {
            img[i] = GameUtil.loadBufferedImage("img/cloud" + i + ".png");
        }
        random = new Random();
    }

    //绘制云彩
    public void draw(Graphics g) {
        logic();
        for (int i = 0; i < clouds.size(); i++) {
            clouds.get(i).draw(g);
        }
    }

    /**
     * 用于云彩的个数控制
     */
    private void logic() {
        if ((int) (500 * Math.random()) < 5) {
            Cloud cloud = new Cloud(img[random.nextInt(CLOUND_COUNT)], CLOUNG_SPEED, 600, random.nextInt(150));
            clouds.add(cloud);
        }
        for (int i = 0; i < clouds.size(); i++) {
            Cloud cloud = clouds.get(i);
            if (cloud.isOutFrame()){
                clouds.remove(i);
                i--;
                System.out.println("移除了"+cloud);
            }
        }

    }
}
