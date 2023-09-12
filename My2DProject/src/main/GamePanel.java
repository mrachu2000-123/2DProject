package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	//	Screen Settings

	final int originalTileSize = 16; //16*16 tile
	final int scale = 3;
	public final int tilesize = originalTileSize*scale; //16*3 = 48

	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tilesize*maxScreenCol;
	public final int screenHeight = tilesize*maxScreenRow;
	
	//world settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int WorldWidth = tilesize*maxWorldCol;
	public final int WorldHeight = tilesize*maxWorldRow;
	
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	
	Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	public CollisionChecker checker = new CollisionChecker(this);
	public Player player = new Player(this,keyH);


	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread =  new Thread(this); 
		gameThread.start();

	}
	
	@Override
	public void run() {

		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime()+drawInterval;

		while(gameThread!= null) {
			update();
			repaint();

			try {
				
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;

				if(remainingTime<0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

//	@Override
//	public void run() {
//		
//		double drawInterval = 1000000000/FPS;
//		double delta = 0;
//		long lastTime = System.nanoTime();
//		long currentTime;
//		
//		while(gameThread != null) {
//			 currentTime = System.nanoTime();
//			 delta += (currentTime- lastTime)/drawInterval;
//			 lastTime = currentTime;
//			 
//			 if(delta>=1) {
//				 update();
//				 repaint();
//				 delta--;
//			 }
//		}
//		
//	}
	
	public void update() {

		player.update();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		tileM.draw(g2);
		player.draw(g2);
		g2.dispose();
	}

}
