package com.codingame.game;
import com.codingame.gameengine.core.AbstractMultiplayerPlayer;
import com.codingame.gameengine.module.entities.Group;
import com.codingame.gameengine.module.entities.Sprite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Player extends AbstractMultiplayerPlayer {
    private boolean isWInner;
    public Group hud;

    Player(){
        super();
        isWInner = false;

    }

    @Override
    public int getExpectedOutputLines() {
        // Returns the number of expected lines of outputs for a player

        // TODO: Replace the returned value with a valid number. Most of the time the value is 1. 
        return 1;
    }

    public void setWInner(boolean isWInner){
        this.isWInner = isWInner;
    }

    public boolean isWInner(){
        return this.isWInner;
    }

}
