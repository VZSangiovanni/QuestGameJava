package it.game.quest.interfaces;

import it.game.quest.interfaces.GUI;
import it.game.quest.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GUI gp;
    Font arial_40;
    Font arial_80B;
    BufferedImage keyImage;
    public boolean messageON = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameWin = false;

    public UI(GUI gp){
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageON = true;
    }

    public void draw(Graphics2D g2d){

        if (gameWin) {

            String text;
            int textLength;
            int x;
            int y;

            g2d.setFont(arial_80B);
            g2d.setColor(Color.GREEN);
            text = "GAME WIN";
            textLength = (int)g2d.getFontMetrics().getStringBounds(text,g2d).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeigth/2 - (gp.tileSize*2);
            g2d.drawString(text, x, y);

            gp.gameThread = null;

        }else {
            g2d.setFont(arial_40);
            g2d.setColor(Color.WHITE);
            g2d.drawImage(keyImage, gp.tileSize/5, gp.tileSize/5, gp.tileSize, gp.tileSize, null);
            g2d.drawString("= " + gp.player.hasKey, 65, 50);

            //MESSAGE
            if (messageON){
                g2d.setFont(g2d.getFont().deriveFont(30F));
                g2d.setColor(Color.GREEN);
                g2d.drawString(message, 150, 50);

                messageCounter++;
                if (messageCounter > 120){
                    messageCounter = 0;
                    messageON = false;
                }
            }

        }
    }

}
