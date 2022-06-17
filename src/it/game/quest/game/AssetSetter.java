package it.game.quest.game;

import it.game.quest.entity.Guard;
import it.game.quest.interfaces.GUI;
import it.game.quest.object.OBJ_Door;
import it.game.quest.object.OBJ_Key;

public class AssetSetter {


    GUI gp;

    public AssetSetter(GUI gp){
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Key();
        gp.obj[0].objx = 7 * gp.tileSize;
        gp.obj[0].objy = 3 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].objx = 7 * gp.tileSize;
        gp.obj[1].objy = 8 * gp.tileSize;

        gp.obj[2] = new OBJ_Door();
        gp.obj[2].objx = 15 * gp.tileSize;
        gp.obj[2].objy = 10 * gp.tileSize;


    }

    public void setGuard(){

        gp.guards[0] = new Guard(gp);
        gp.guards[0].x = 6 * gp.tileSize;
        gp.guards[0].y = 3 * gp.tileSize;

        gp.guards[1] = new Guard(gp);
        gp.guards[1].x = 8 * gp.tileSize;
        gp.guards[1].y = 8 * gp.tileSize;

    }

}
