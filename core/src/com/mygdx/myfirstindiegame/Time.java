package com.mygdx.myfirstindiegame;

import com.badlogic.gdx.Gdx;

/**
 * Created by mkemp on 1/3/18.
 */

public class Time {

    // Ratio of "idealized" framerate and actual framerate
    public static double time = 1.0d;

    private static int defaultFPS = 60;

    public static void Update() {

        // Average number of frames per second.
        int actualFPS = Gdx.graphics.getFramesPerSecond();

        // If fps is 0... slow down by a lot.
        actualFPS = (actualFPS == 0) ? 3000 : actualFPS;

        time = (double) defaultFPS / actualFPS;
    }
}
