package com.bird.main;

import static com.bird.util.Constant.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

public class GameFrame extends Frame {
	
	private static final int STATE_REDAY = 0;
	
	private static final int STATE_PLAYING = 1;
	
	public int gameState;
	
    private GameBackGround gameBackGround;
    
    private Bird bird;
    
    private BufferedImage buffimg = new BufferedImage(FRAM_WIDTH,FRAM_HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);
	
    //实例化GameFrontGround类
    private GameFrontGround gameFrontGround;
    
    private GameBarrierLayer gameBarrierLayer;
    
	public GameFrame() {
		setVisible(true);
		
		setSize(FRAM_WIDTH,FRAM_HEIGHT);
		
		setTitle(FRAM_TITLE);
		
		setLocation(FRAM_X,FRAM_Y);
		
		setResizable(false);
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				
			}
			
		});
		
		
        initGamg();

        new run().start();
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                add(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                minu(e);
            }
        });
        
	}
	

	//对游戏中的对象初始化
    public void initGamg(){
        gameBackGround = new GameBackGround();
        bird = new Bird();
        gameFrontGround = new GameFrontGround();
        gameBarrierLayer = new GameBarrierLayer();
        
        gameState = STATE_REDAY;
    }


    class run extends Thread{
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 所有的我们需要绘制的内容都在此方法中进行调用绘制
     */
    @Override
    public void update(Graphics g) {
    	
        if( bird.life){
            //得到图片的画笔
        	Graphics graphics = buffimg.getGraphics();
            gameBackGround.draw(graphics);
            bird.draw(graphics);
            gameFrontGround.draw(graphics);
            gameBarrierLayer.draw(graphics,bird);

            //一次性的将图片绘制到屏幕中
            g.drawImage(buffimg,0,0,null);
        } else if(gameState == STATE_REDAY && !bird.life){
            
            
            String over = "GAME START";
            g.setColor(Color.blue);
            g.setFont(new Font("微软雅黑",1,60));
            g.drawString(over,130,250);
            
            String reset = "Space Start Game";
            g.drawString(reset,25,350);
        	Graphics graphics = buffimg.getGraphics();
            gameBackGround.draw(graphics);
            //一次性的将图片绘制到屏幕中
            g.drawImage(buffimg,0,0,null);
            
        } else if(gameState == STATE_PLAYING && !bird.life){
            String over = "GAME OVER";
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑",1,60));
            g.drawString(over,130,250);
            
            String reset = "Space Reset Game";
            g.drawString(reset,25,350);
        }
    }
	
    

    //按键
    public void add(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
            	// System.out.println("bird.fly(1)");
                bird.fly(1);
                break;
            case KeyEvent.VK_SPACE:
                if (bird.life == false) {
                	gameState = STATE_REDAY;
                    restart();
                }
                break;
        }
    }



    //抬键
    public void minu(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
            	// System.out.println("bird.fly(5)");
                bird.fly(5);
                break;
        }
    }
    
    /**
     * 重置游戏
     */
    public void restart(){
        gameBarrierLayer.restant();
        bird.restartDraw();
        gameState = STATE_PLAYING;
    }
}

