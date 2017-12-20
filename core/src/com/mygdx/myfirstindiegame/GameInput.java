package com.mygdx.myfirstindiegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mkemp on 12/18/17.
 */

public class GameInput {

    // Vector cancels out contradictory key presses.
    public static Vector2 KeyForce = new Vector2();

    public static void Update() {

        // Stop moving (x). Start fresh.
        KeyForce.x = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            KeyForce.x -= 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            KeyForce.x += 1;
        }

        // Stop moving (y). Start fresh.
        KeyForce.y = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            KeyForce.y += 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            KeyForce.y -= 1;
        }
    }
}
