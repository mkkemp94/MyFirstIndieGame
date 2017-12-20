package com.mygdx.myfirstindiegame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by mkemp on 12/20/17.
 */

public class TextManager {
    private static BitmapFont bitmapFont = new BitmapFont();
    private static SpriteBatch spriteBatchHandler;

    public static void SetSpriteBatch(SpriteBatch batch) {
        spriteBatchHandler = batch;
    }

    public static void Draw(CharSequence msg) {
        bitmapFont.draw(spriteBatchHandler, msg, 10, 20);
    }
}
