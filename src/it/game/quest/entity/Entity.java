package it.game.quest.entity;

import it.game.quest.interfaces.GUI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GUI gp;
    public int x, y;
    public int speed;

    public BufferedImage entityImage;
    public String direction;

    public Rectangle solidArea = new Rectangle(0,0,48,48);

    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public int actionClockCount = 0;

    public Entity(GUI gp) {
        this.gp = gp;
    }


    public void setAction() {}

    public void update() {

        /*setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this,false);
        gp.cChecker.checkPlayer(this);
        // this checker is invalid, the entity check himself and don't move
        //gp.cChecker.checkEntity(this, gp.guards);

        if (!collisionOn) {
            switch (direction) {
                case "up" -> y -= speed;
                case "down" -> y += speed;
                case "left" -> x -= speed;
                case "right" -> x += speed;
            }
        }*/

    }

    public void draw(Graphics2D g2d){

        g2d.drawImage(entityImage,x,y,gp.tileSize, gp.tileSize, null);

    }

}
