package com.bird.main;

import static com.bird.util.Constant.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

public class GameBackGround {
	private BufferedImage bkimg;
	
	public GameBackGround() {
		bkimg = GameUtil.loadBufferedImage(Constant.BK_IMG_PATH);
	}
	
    public void draw(Graphics g){
    	
        //填充背景色
        g.setColor(BK_COLOR);
        g.fillRect(0,0,FRAM_WIDTH,FRAM_HEIGHT);
        g.setColor(Color.black);

        //得到图片的高度和宽度
        int height = bkimg.getHeight();
        int weight = bkimg.getWidth();
        //循环的次数
        int count = Constant.FRAM_WIDTH/weight+1;
        for (int i = 0; i < count; i++) {
            g.drawImage(bkimg,weight*i,Constant.FRAM_HEIGHT-height,null);
        }
    }
}