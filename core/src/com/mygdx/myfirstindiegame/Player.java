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

        // Stop player when he lands on ground.
        if (this.getY() <= 50) {
            momentum.y = 0;

            // Jump
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

                // Jumping works in the opposite direction as gravity.
                momentum.add(gravity * 27 * -1 * gravityVector.x, gravity * 23 * -1 * gravityVector.y);
            }

        } else {

            // Pull the player in the direction we want them to go.
            momentum.add(gravity * gravityVector.x, gravity * gravityVector.y);
        }

        // Move left/right
//        momentum.x = GameInput.KeyForce.x * speed;

        this.translateX(momentum.x * (float) Time.time);
        this.translateY(momentum.y * (float) Time.time);
        this.setY(Math.max(this.getY(), 50));
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(this.getTexture(), this.getX(), this.getY());
    }
}
