package com.mygdx.myfirstindiegame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * Created by mkemp on 1/5/18.
 */

public class FootProbe extends Sprite {

    private Player player;
    private Texture texture;
    private TiledMapTileLayer tileLayer;

    public FootProbe(Player p, Texture t, TiledMapTileLayer tiledMapTileLayer) {
        player = p;
        setTexture(t);
        tileLayer = tiledMapTileLayer;
        setX(player.getX());
        setY(player.getY() - 50);
    }

    public void Update() {
        boolean collisionTileFound = false;
        int tileToFind = 0;
        int subtractOne = 0;
        TiledMapTileLayer.Cell cell;
        int levelHeight = tileLayer.getHeight();

        // Set foot probe's x to be the player's x
        setX(player.getX());

        while (!collisionTileFound) {

            // Look tiles below
            if (tileToFind > 10) break;

            // Find that specific tile
            cell = tileLayer.getCell((int) player.getX() / 50, (int) (player.getY() / 50) - tileToFind);

            // If we find it...
            if (cell != null) {

                // Put foot probe on top (just under top)
                int heightOfTile = (int) (player.getY() / 50) - tileToFind;
                setY(heightOfTile * 50 + 45);
                break;
            }

            // Else look a tile lower
            tileToFind++;
        }
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(this.getTexture(), this.getX(), this.getY());
    }
}
