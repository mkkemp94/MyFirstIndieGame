package com.mygdx.myfirstindiegame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by mkemp on 12/18/17.
 * This generic class can be used for the player, enemies...
 * anything that can change dynamically.
 *
 * This is the parent class for all other objects.
 */

public class GameObject {

    // Position
    public float x = 0;
    public float y = 0;

    private Texture texture;
    private SpriteBatch spriteBatch;

    public GameObject(String texture_path, SpriteBatch batch, float posX, float posY) {
        texture = new Texture(texture_path);
        spriteBatch = batch;
        x = posX;
        y = posY;
    }

    /**
     * Update position on key press.
     */
    public void updatePosition() {
        int speed = 5;

        x += GameInput.KeyForce.x * speed;
        y += GameInput.KeyForce.y * speed;
    }

    /**
     * Game gameObject should draw itself.
     */
    public void draw() {
        spriteBatch.draw(texture, x, y);
    }
}
