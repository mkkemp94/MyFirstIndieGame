package com.mygdx.myfirstindiegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mkemp on 1/3/18.
 */

public class Player extends Sprite {

    private static float frames;
    public static float gravity;
    private Vector2 gravityVector = new Vector2(0, -1);
    private Vector2 momentum = new Vector2(0, 0);

    private TiledMapTileLayer tileLayer;
    private FootProbe footProbe;

    public Player(Texture texture, float x, float y, TiledMapTileLayer tiledMapTileLayer) {
        this.setTexture(texture);
        this.setPosition(x, y);
        footProbe = new FootProbe(this, new Texture("footprobe.png"), tiledMapTileLayer);
        tileLayer = tiledMapTileLayer;
    }

    public void Update() {
        int speed = 5;

        frames =  Gdx.graphics.getFramesPerSecond();
        frames = (frames == 0) ? 60 : frames;
        gravity = 29.4f / frames;

        // Stop player when he lands on ground.
        if (this.getY() <= footProbe.getY() + 5) {
            momentum.y = 0;

            // Jump
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

                // Jumping works in the opposite direction as gravity.
                momentum.set(-gravity * 23 * gravityVector.x / (float) Time.time, -gravity * 23 * gravityVector.y / (float) Time.time);
            }

        } else {

            // Pull the player in the direction we want them to go.
            momentum.add(gravity * gravityVector.x, gravity * gravityVector.y);
        }

        // Move left/right
        momentum.x = GameInput.KeyForce.x * speed;
        this.translateX(momentum.x * (float) Time.time);
        this.translateY(momentum.y * (float) Time.time);

        // Don't let the player move beyond the bottom of the level.
        // Put him right on top of the foot probe.
        this.setY(Math.max(this.getY(), footProbe.getY() + 5));

        footProbe.Update();
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(this.getTexture(), this.getX(), this.getY());
        footProbe.draw(batch);
    }
}
