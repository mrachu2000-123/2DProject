package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];

	public TileManager(GamePanel gp) {

		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		loadMap("/Maps/WorldMap.txt");
	}

	public void getTileImage() {


		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResource("/Tiles/grass.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResource("/Tiles/wall.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResource("/Tiles/water.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResource("/Tiles/earth.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResource("/Tiles/tree.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResource("/Tiles/sand.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String filePath) {
		InputStream is = getClass().getResourceAsStream(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		int col = 0;
		int row = 0;
		try {

			while(col<gp.maxWorldCol && row <gp.maxWorldRow) {
				String line;
				line = br.readLine();

				while(col<gp.maxWorldCol) {
					String number[] = line.split(" ");
					int num = Integer.parseInt(number[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		}  catch (Exception e) {

		}
	}

	public void draw(Graphics2D g2) {

		int Worldcol =0;
		int Worldrow =0;
		
		while(Worldcol<gp.maxWorldCol && Worldrow <gp.maxWorldRow) {

			int tileNum = mapTileNum[Worldcol][Worldrow];

			int Worldx = Worldcol * gp.tilesize;
			int Worldy = Worldrow * gp.tilesize;
			int Screenx = Worldx - gp.player.Worldx + gp.player.Screenx;
			int Screeny = Worldy - gp.player.Worldy + gp.player.Screeny;
			
			if(Worldx+gp.tilesize>gp.player.Worldx-gp.player.Screenx &&
			  Worldx-gp.tilesize<gp.player.Worldx+gp.player.Screenx &&	
			  Worldy+gp.tilesize>gp.player.Worldy-gp.player.Screeny &&
			  Worldy-gp.tilesize<gp.player.Worldy+gp.player.Screeny) {
			g2.drawImage(tile[tileNum].image, Screenx, Screeny, gp.tilesize, gp.tilesize, null);
			}
//			g2.drawImage(tile[tileNum].image, Screenx, Screeny, gp.tilesize, gp.tilesize, null);
			Worldcol++;

			if(Worldcol == gp.maxWorldCol) {
				Worldcol = 0;
				Worldrow++;
			}
		}

	}

}	

