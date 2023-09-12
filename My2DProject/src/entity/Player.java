package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	
	public final int Screenx;
	public final int Screeny;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		Screenx = gp.screenWidth/2 - (gp.tilesize/2);
		Screeny = gp.screenHeight/2 - (gp.tilesize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		Worldx = gp.tilesize  *23;
		Worldy = gp.tilesize *21;
		speed = 4;
		direction = "down";
	}

	public void getPlayerImage() {

		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/Player/front1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/Player/front2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/Player/Back1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/Player/Back2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/Player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/Player/left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/Player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/Player/right2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void update() {

		if(keyH.upPressed == true || keyH.leftPressed == true || 
				keyH.downPressed == true || keyH.rightPressed == true ) {
			if(keyH.upPressed == true) {
				direction = "up";
			}else if(keyH.downPressed == true) {
				direction = "down";
			}else if(keyH.leftPressed == true) {
				direction = "left";
			}else if(keyH.rightPressed == true){
				direction = "right";
			}
			
			//check Tile Collision
			collisionon = false;
			gp.checker.checkTile(this);
			
			//if collision is false, player can move
			if(collisionon == false) {
				switch(direction) {
				case "up":
					Worldy -= speed;
				break;
				case "down":
					Worldy += speed;
					break;
				case "left":
					Worldx -= speed;
					break;
				case "right":
					Worldx += speed;
					break;
				}
			}
			
			
			
			spriteCounter++;

			if(spriteCounter>12) {
				if(spriteNum ==1) {
					spriteNum = 2;
				}else if(spriteNum ==2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}

	public void draw(Graphics2D g2) {
		//		g2.setColor(Color.white);
		//		g2.fillRect(x, y, gp.tilesize, gp.tilesize);

		BufferedImage image = null;
		switch(direction) {
		case "up" :
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down" :
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left" : 
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right" : 
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;

		}

		g2.drawImage(image,Screenx, Screeny, gp.tilesize, gp.tilesize,null); //ImageObserver
	}
}
