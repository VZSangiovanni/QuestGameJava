package it.game.quest.interfaces;

import javax.swing.*;


public class GameWindow extends JFrame {


    public GameWindow(){
        this.setTitle("QUEST");
        this.setResizable(false);
        this.setVisible(true);
        GUI gui = new GUI();
        this.add(gui);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gui.setupGame();
        gui.startGameThread();
        gui.setFocusable(true);
    }


}
