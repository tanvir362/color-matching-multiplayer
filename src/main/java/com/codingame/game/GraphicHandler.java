package com.codingame.game;

import com.codingame.gameengine.core.MultiplayerGameManager;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.entities.Sprite;
import com.codingame.gameengine.module.entities.Text;

import java.util.ArrayList;

public class GraphicHandler {
    private MultiplayerGameManager<Player> gameManager;
    private GraphicEntityModule graphicEntityModule;

    public GraphicHandler(MultiplayerGameManager<Player> gameManager, GraphicEntityModule graphicEntityModule){
        this.gameManager = gameManager;
        this.graphicEntityModule = graphicEntityModule;
    }

    private int getBaseX(Player player){
        return (int)(1920/4) * (player.getIndex()==0 ? 1: 3) - 283;
    }

    private int getBaseY(Player player){
        return (int)1080/2 - 280;
    }

    public void drawBackgroundImage(){
        graphicEntityModule.createSprite()
                .setImage("Background.jpg")
                .setAnchor(0)
                .setX(0)
                .setY(0);
    }


    public void drawGrids() {
        for (Player player : gameManager.getPlayers()) {
            int base_x = getBaseX(player);
            int base_y = getBaseY(player);

        }

    }


    public void drawHud() {
        for (Player player : gameManager.getPlayers()) {
            int x = player.getIndex() == 0 ? 100 : 1920 - 100;
            int y = 75;

            graphicEntityModule
                    .createRectangle()
                    .setWidth(140)
                    .setHeight(140)
                    .setX(x - 70)
                    .setY(y - 70)
                    .setLineWidth(0)
                    .setFillColor(player.getColorToken());

            graphicEntityModule
                    .createRectangle()
                    .setWidth(120)
                    .setHeight(120)
                    .setX(x - 60)
                    .setY(y - 60)
                    .setLineWidth(0)
                    .setFillColor(0xffffff);

            Text text = graphicEntityModule.createText(player.getNicknameToken())
                    .setX(x)
                    .setY(y + 120)
                    .setZIndex(20)
                    .setFontSize(40)
                    .setFillColor(0xffffff)
                    .setAnchor(0.5);

            Sprite avatar = graphicEntityModule.createSprite()
                    .setX(x)
                    .setY(y)
                    .setZIndex(20)
                    .setImage(player.getAvatarToken())
                    .setAnchor(0.5)
                    .setBaseHeight(116)
                    .setBaseWidth(116);

            player.hud = graphicEntityModule.createGroup(text, avatar);
        }
    }
}
