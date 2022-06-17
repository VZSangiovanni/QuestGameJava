package it.game.quest.entity;

import it.game.quest.interfaces.GUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Guard extends Entity{


    public Guard(GUI gp) {
        super(gp);

        direction = "up";
        speed = 4;

        solidArea = new Rectangle();
        solidArea.x = 2;
        solidArea.y = 2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = gp.tileSize-5;
        solidArea.height = gp.tileSize-5;

        getGuardImage();
    }

    public void getGuardImage(){

        try {

            entityImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("guards/guard.png"));

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void setAction() {
        //TODO far muovere le guardie!
        actionClockCount++;

        if (actionClockCount == 60){

            Random random = new Random();
            int i = random.nextInt(100)+1;

            if (i <= 25){
                direction = "up";
            } else if (i > 25 && i <= 50) {
                direction = "down";
            } else if (i > 50 && i < 75) {
                direction = "left";
            } else if ( i > 75 && i <= 100) {
                direction = "right";
            }
            actionClockCount = 0;
        }

    }

}
