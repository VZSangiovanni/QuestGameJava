package it.game.quest.tile;


import it.game.quest.interfaces.GUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GUI gp;
    public Tile[] tile;
    public int[][] mapTileNum;


    public TileManager(GUI gp){
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.columns][gp.rows];

        getTileImage();
        loadMap("maps/map01.txt");
    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/floor1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/floor2.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/wall.png"));
            tile[2].collision = true;


        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath){

        try {

            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.columns && row < gp.rows){

                String line = br.readLine();

                while (col < gp.columns){

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.columns){
                    col = 0;
                    row++;
                }

            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void draw(Graphics2D g2d){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.columns && row < gp.rows){

            int tileNum = mapTileNum[col][row];

            g2d.drawImage(tile[tileNum].image,x,y,gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.columns){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }

        }


    }

}
