package com.mygdx.myfirstindiegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MyFirstIndieGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	GameObject gameObject;
	Player player;

	// Window windowWidth and windowHeight
	float windowWidth;
	float windowHeight;

	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;

	int levelPixelWidth;
	int levelPixelHeight;

	@Override
	public void create () {
		batch = new SpriteBatch();

		// Window windowWidth and windowHeight
		windowWidth = Gdx.graphics.getWidth();
		windowHeight = Gdx.graphics.getHeight();

		// Set up camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, windowWidth, windowHeight);
		camera.update();

		// Set up map
		tiledMap = new TmxMapLoader().load("stage1.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		TextManager.SetSpriteBatch(batch, camera);
		gameObject = new GameObject("player.png", batch, 50, 50);

		// Player
		Texture playerTexture = new Texture("player.png");
		player = new Player(playerTexture, 200, 50, (TiledMapTileLayer) tiledMap.getLayers().get(0));

		// Map properties
		MapProperties properties = tiledMap.getProperties();
		int levelWidth = properties.get("width", Integer.class); // in tiles
		int levelHeight = properties.get("height", Integer.class); // in tiles
		int tilePixelWidth = properties.get("tilewidth", Integer.class); // in pixels
		int tilePixelHeight = properties.get("tileheight", Integer.class); // in pixels

		levelPixelWidth = levelWidth * tilePixelWidth;
		levelPixelHeight = levelHeight * tilePixelHeight;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Lets you use half-transparent tiles.
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		GameInput.Update();
		gameObject.updatePosition();
		player.Update();


		// Still center, but clamp screen
		camera.position.x = Math.min(Math.max(gameObject.x, windowWidth / 2), levelPixelWidth - windowWidth / 2);
		camera.position.y = Math.min(Math.max(gameObject.y, windowHeight / 2), levelPixelHeight - windowHeight / 2);
		camera.update();

		Time.Update();

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		batch.begin();

		batch.setProjectionMatrix(camera.combined);

		// Render game gameObject.
		//gameObject.draw();
		player.draw(batch);

		// Draw text to screen. Useful for debugging.
		//TextManager.Draw("X: " + gameObject.x + " Y: " + gameObject.y +
		//		"\nFPS: " + Gdx.graphics.getFramesPerSecond() + " Time: " + Time.time, 10, 20);
		TextManager.Draw("FPS: " + Gdx.graphics.getFramesPerSecond() + " Time: " + Time.time, 10, 10);

		batch.end();

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
//		img.dispose();
	}
}
