package com.bird.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 游戏中障碍物层
 */
public class GameBarrierLayer {
	
	private GameTime gameTime;
	
	private int txt;
	
	private Random random = new Random();
	
    private List<Barrier> barriers;

    public GameBarrierLayer(){
        barriers = new ArrayList<>();
        gameTime = new GameTime();
        
    }

    //绘制障碍物
    public void draw(Graphics g,Bird bird){
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);
            if (barrier.isVisible()) {
                barrier.draw(g);
            }else {
                Barrier remove = barriers.remove(i);
                Barrierpool.setPool(remove);
                i--;
            }
        }
        collideBird(bird);
        logic(g);

    }
    
    public void logic(Graphics g){
        if (barriers.size() == 0) {
            ran();
            ran();
            gameTime.begin();
//            Barrier top = new Barrier(600, 0, numberTop, 0);
//            barriers.add(top);
//            Barrier down = new Barrier(600, 500-numberDown, numberDown, 2);
//            barriers.add(down);
            insert(600,0,numberTop,0);
            insert(600,500-numberDown,numberDown,2);
        }else {
            long differ = gameTime.differ();
            g.setColor(Color.white);
            g.setFont(new Font("メイリオ", 1, 20));
            g.drawString("時間："+differ + "s", 30, 50);
            
            //显示最高成绩
            txt = getTxt();
            if (differ <= txt){
                g.drawString("最高記録:" + txt + "s" , 200, 50);
            }else {
                setTxt(String.valueOf(differ));
                g.drawString("最高記録:" + getTxt() + "s"  , 200, 50);
            }

            //判断最后一个障碍物是否完全进入屏幕内
            Barrier last = barriers.get(barriers.size() - 1);
            if (last.isInFrame()) {
                ran();
//                Barrier top = new Barrier(600, 0, numberTop, 0);
//                barriers.add(top);
//                Barrier down = new Barrier(600, 500-numberDown, numberDown, 2);
//                barriers.add(down);
                if (number < 50){
                    insert(600,100,300,4);
                }else if (number>450){
                   insert(600,125,200,6);
                }else {
                    insert(600, 0, numberTop, 0);
                    insert(600, 500 - numberDown, numberDown, 2);
                }
            }
        }
    }


    
    //用于储存游戏数据
    File file = new File("D:\\game.txt");

    /**
     * 从文件获取数据
     * @return
     */
    public int getTxt()   {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int read = 0;
        try {
            read = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return read;
    }

    /**
     * 写入数据到文件
     * @param str
     */
    public void setTxt(String str)   {
        FileWriter out = null;
        try {
            out = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 用于从池中获取对象，并吧参数封装成barrier 存入barriers数组中
     */
    public void insert(int x,int y ,int num,int type){
        Barrier top = Barrierpool.getPool();
        top.setX(x);
        top.setY(y);
        top.setHeight(num);
        top.setType(type);
        top.setVisible(true);
        barriers.add(top);
    } 


//上方障碍物高度
    private int numberTop;
//下方障碍物高度
    private int numberDown;
    //中间障碍物高度
    private int number;
//产生两个100-500之间的随机高度
    public void ran(){
        numberTop = random.nextInt(400)+100;
        numberDown = random.nextInt(400)+100;
        number = random.nextInt(500);
        //如果管道重合，则重新随机
        if (numberTop + numberDown > 450) {
            ran();
        }
    }
   
    /**
     * 判断障碍物和小鸟发生碰撞
     */
    public boolean collideBird(Bird bird){
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);
            //判断矩形是否相交
            if (barrier.getRect().intersects(bird.getRect())){
                // System.out.println("装上啦");
                bird.life=false;
            }
        }
        return false;
    }

    /**
     * 用于清空障碍物的池子
     */
     public void restant(){
         barriers.clear();
     }
     
}
