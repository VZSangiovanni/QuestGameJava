package it.game.quest.object;

import it.game.quest.interfaces.GUI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision;
    public int objx, objy;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;


    public void draw(Graphics2D g2d, GUI gp){

        g2d.drawImage(image,objx,objy,gp.tileSize, gp.tileSize, null);

    }


}
