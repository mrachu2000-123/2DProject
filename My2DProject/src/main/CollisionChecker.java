package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gp;
	
	CollisionChecker(GamePanel gp){
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityleftWorldx = entity.Worldx+entity.solidArea.x;
		int entityRightWorldx = entity.Worldx+entity.solidArea.x+entity.solidArea.width;
		int entityTopWorldy = entity.Worldy+entity.solidArea.y;
		int entitybottomWorldy = entity.Worldy+entity.solidArea.y+entity.solidArea.height;
		
		int entityleftcol = entityleftWorldx/gp.tilesize;
		int entityRightcol = entityRightWorldx/gp.tilesize;
		int entitytoprow = entityTopWorldy/gp.tilesize;
		int entitybottomrow = entitybottomWorldy/gp.tilesize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entitytoprow = (entityTopWorldy - entity.speed)/gp.tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityleftcol][entitytoprow];
			tileNum2 = gp.tileM.mapTileNum[entityRightcol][entitytoprow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionon = true;
			}
			break;
		case "down":
			entitybottomrow = (entitybottomWorldy + entity.speed)/gp.tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityleftcol][entitybottomrow];
			tileNum2 = gp.tileM.mapTileNum[entityRightcol][entitybottomrow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionon = true;
			}
			break;
		case "left":
			entityleftcol = (entityleftWorldx- entity.speed)/gp.tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityleftcol][entitytoprow];
			tileNum2 = gp.tileM.mapTileNum[entityleftcol][entitybottomrow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionon = true;
			}
			break;
		case "right":
			entityRightcol = (entityRightWorldx + entity.speed)/gp.tilesize;
			tileNum1 = gp.tileM.mapTileNum[entityRightcol][entitytoprow];
			tileNum2 = gp.tileM.mapTileNum[entityRightcol][entitybottomrow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionon = true;
			}
			break;
		}
	}
}
