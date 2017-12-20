package com.mygdx.myfirstindiegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyFirstIndieGame extends ApplicationAdapter {
	SpriteBatch batch;
	GameObject gameObject;

	@Override
	public void create () {
		batch = new SpriteBatch();
		TextManager.SetSpriteBatch(batch);
		gameObject = new GameObject("badlogic.jpg", batch, 0, 0);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		// Update the keyforce.
		GameInput.Update();

		// Render game gameObject.
		gameObject.updatePosition();
		gameObject.draw();

		// Draw text to screen. Useful for debugging.
		TextManager.Draw("X: " + gameObject.x + "Y: " + gameObject.y);

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
