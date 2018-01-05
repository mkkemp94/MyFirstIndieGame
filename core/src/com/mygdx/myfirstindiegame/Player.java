package com.mygdx.myfirstindiegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mkemp on 1/3/18.
 */

public class Player extends Sprite {

    private static float frames;
    public static float gravity;
    private Vector2 gravityVector = new Vector2(0, -1);
    private Vector2 jumpVector = new Vector2(0, 1);
    private Vector2 momentum = new Vector2(0, 0);

    public Player(Texture texture, float x, float y) {
        this.setTexture(texture);
        this.setPosition(x, y);
    }

    public void Update() {
        int speed = 5;

        frames =  Gdx.graphics.getFramesPerSecond();
        frames = (frames == 0) ? 60 : frames;
        gravity = 19.6f / frames;

        // Stop player if he gets too low (on ground).
        if (this.getY() <= 50) {
            momentum.y = 0;

            // Jump
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                momentum.y = gravity * 27;
            }

        } else {
            // How much force has been applied
            momentum.y -= gravity;
        }



        // Move left/right
        momentum.x = GameInput.KeyForce.x * speed;

        this.translateX(momentum.x);
        this.translateY(momentum.y);
        this.setY(Math.max(this.getY(), 50));
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(this.getTexture(), this.getX(), this.getY());
    }
}
