package com.mygdx.myfirstindiegame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by mkemp on 12/20/17.
 */

public class TextManager {
    private static BitmapFont bitmapFont = new BitmapFont();
    private static SpriteBatch spriteBatchHandler;

    public static void SetSpriteBatch(SpriteBatch batch) {
        bitmapFont.getData().scale(2);
        bitmapFont.setColor(Color.CYAN);
        spriteBatchHandler = batch;
    }

    public static void Draw(CharSequence msg, OrthographicCamera camera) {
        Vector3 position = new Vector3(10, 20, 0);
        camera.unproject(position);
        bitmapFont.draw(spriteBatchHandler, msg, position.x, position.y);
    }
}
