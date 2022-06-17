package it.game.quest.entity;

import it.game.quest.game.KeyHandler;
import it.game.quest.interfaces.GUI;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity{


    KeyHandler keyH;
    public int hasKey = 0;
    public boolean playerMove = false;



    public Player(GUI gp, KeyHandler keyH){

        super(gp);
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 2;
        solidArea.y = 2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = gp.tileSize-5;
        solidArea.height = gp.tileSize-5;

        getPlayerImage();
        setDefaultValues();

    }

    public void setDefaultValues(){

        x = 7 * gp.tileSize;
        y = 6 * gp.tileSize;
        speed = 4;
        direction = "";

    }

    public void getPlayerImage(){

        try {

            entityImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/player.png"));

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
                playerMove = true;
            } else if (keyH.downPressed) {
                direction = "down";
                playerMove = true;
            } else if (keyH.leftPressed) {
                direction = "left";
                playerMove = true;
            } else {
                direction = "right";
                playerMove = true;
            }


            // Check Tile Collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //Check Object Collision
            int objindex = gp.cChecker.checkObject(this, true);
            pickUpObject(objindex);

            //Check Guard Collision
            int guardIndex = gp.cChecker.checkEntity(this, gp.guards);
            interactGuard(guardIndex);

            //If collision is false, player can move
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> y -= speed;
                    case "down" -> y += speed;
                    case "left" -> x -= speed;
                    case "right" -> x += speed;
                }
            }


        }
    }

    public void pickUpObject(int i){

        if (i != 999){

            String objectName = gp.obj[i].name;

            switch (objectName){
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    if (hasKey == 2){
                        gp.ui.showMessage("Door Open");
                    }
                    break;
                case "Door":
                    if (hasKey == 2){
                        gp.obj[i] = null;
                        if (gp.obj[i] == null){
                            gp.ui.gameWin = true;

                        }

                    }
                    break;
            }

        }

    }

    public void interactGuard(int i){

        if (i != 999){
            System.out.println("HIT");
        }

    }

    public void draw(Graphics2D g2d){

        BufferedImage image = entityImage;

        g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

}
