package com.bird.main;


import static com.bird.util.Constant.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.bird.util.GameUtil;

public class Bird {
	
    //小鸟矩形对象
    private Rectangle rect;
    
    //小鸟加速度
    private int acceleration;
    
    //小鸟的生命
    public boolean life = true;

    private BufferedImage[] images;
    public static final int BIRD_IMG_COUNT = 3;


    private int state;
    public static final int STATE_NORMAR = 0;
    public static final int STATE_UP = 1;
    public static final int STATE_DOWN = 2;
    
    //小鸟的位置
    private int x=200,y=200;

    //小鸟移动方向 上下
    private boolean up=false,down=false;

    //小鸟移动速度
    private int speed=4;
    
    //构造方法中对资源初始化
    public Bird() {
        images = new BufferedImage[BIRD_IMG_COUNT];
        for (int i = 0; i < BIRD_IMG_COUNT; i++) {
            images[i] = GameUtil.loadBufferedImage(BIRD_IMG[i]);
        }

        int w = images[0].getWidth();
        int h = images[0].getHeight();
        rect = new Rectangle(w,h);
    }

    //绘制小鸟
    public void draw(Graphics g) {
        flyLogic();
        g.drawImage(images[state], x, y, null);
        
        //绘制小鸟的矩形
        // g.drawRect(x,y,(int)rect.getWidth(), rect.height);
        rect.x=this.x;
        rect.y=this.y;
    }

    //控制小鸟移动方向
    public void flyLogic(){
        if (up){
            acceleration--;
            y+=acceleration;
            if (acceleration < -7) {
                acceleration=-7;
            }
            if (y<20){
                y=20;
                acceleration=0;
            }
        }
        if (!up){
            acceleration++;
            y+=acceleration;
            if (acceleration > 7) {
                acceleration=7;
            }
            if (y>475){
                y=475;
                acceleration=0;
            }
        }

    }

    
    public void fly(int fly){
        switch (fly){
            case 1:
                state=1;
                up=true;
                break;
            case 5:
                state=2;
                up=false;
                break;
        }
    }

    public Rectangle getRect() {
        return rect;
    }


    /**
     * 重新绘制小鸟的位置
     */
    public void  restartDraw(){
        life=true;
        x=200;
        y=200;
    }
}
