package com.mygdx.myfirstindiegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MyFirstIndieGame extends ApplicationAdapter {
	SpriteBatch batch;
	GameObject gameObject;

	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;

	@Override
	public void create () {
		batch = new SpriteBatch();

		// Window width and height
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		// Set up camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		camera.update();

		// Set up map
		tiledMap = new TmxMapLoader().load("stage1.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		TextManager.SetSpriteBatch(batch);
		gameObject = new GameObject("player.png", batch, 0, 0);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Lets you use half-transparent tiles.
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		GameInput.Update();
		gameObject.updatePosition();

		camera.position.set(gameObject.x, gameObject.y, 0);
		camera.update();

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		batch.begin();

		batch.setProjectionMatrix(camera.combined);

		// Render game gameObject.
		gameObject.draw();

		// Draw text to screen. Useful for debugging.
		TextManager.Draw("X: " + gameObject.x + "Y: " + gameObject.y, camera);

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
