package com.mygdx.myfirstindiegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mkemp on 1/3/18.
 */

public class Player extends Sprite {

    public static float momentum = 0;
    private static float frames;
    public static float gravity;
    private Vector2 gravityVector = new Vector2(0, -1);

    public Player(Texture texture, float x, float y) {
        this.setTexture(texture);
        this.setPosition(x, y);
    }

    public void Update() {
        frames =  Gdx.graphics.getFramesPerSecond();
        frames = (frames == 0) ? 60 : frames;
        gravity = 19.6f / frames;
        momentum = Math.min(gravity + frames, momentum + gravity);
        this.translateX(gravityVector.x * momentum);
        this.translateY(gravityVector.y * momentum);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(this.getTexture(), this.getX(), this.getY());
    }
}
