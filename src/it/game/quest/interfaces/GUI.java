package it.game.quest.interfaces;

import it.game.quest.entity.Entity;
import it.game.quest.entity.Player;
import it.game.quest.game.AssetSetter;
import it.game.quest.game.CollisionChecker;
import it.game.quest.game.KeyHandler;
import it.game.quest.object.SuperObject;
import it.game.quest.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel implements Runnable {

    // SCREEN SETTINGS
    public final int tileSize = 50;
    public final int columns = 16;
    public final int rows = 12;
    public final int screenWidth = tileSize * columns;
    public final int screenHeigth = tileSize * rows;

    //FPS

    int FPS = 60;

    //SYSTEM
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Thread gameThread;

    //ENTITY and OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[10];
    public Entity[] guards = new Entity[10];




    public GUI() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeigth));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){

        aSetter.setObject();
        aSetter.setGuard();
    }



    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            //keep the window focused
            requestFocusInWindow();
            //UPDATE Information
            update();
            //DRAW Screen
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if (remainingTime < 0){
                    remainingTime=0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void update() {

        player.update();

        for (Entity guard : guards) {
            if (guard != null) {
                guard.update();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        //Tile
        tileM.draw(g2d);

        //Object
        for (SuperObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(g2d, this);
            }
        }
        //Guards
        for (Entity guard : guards) {
            if (guard != null) {
                guard.draw(g2d);
            }
        }

        //Player
        player.draw(g2d);

        //UI
        ui.draw(g2d);

        g2d.dispose();
    }


}