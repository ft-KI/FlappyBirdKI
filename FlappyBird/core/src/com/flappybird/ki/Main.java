package com.flappybird.ki;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flappybird.ki.Game.FlappyBirdGame;

public class Main extends ApplicationAdapter {
	public static SpriteBatch batch;
	public static ShapeRenderer shapeRenderer;
	FlappyBirdGame flappyBirdGame;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		flappyBirdGame = new FlappyBirdGame(50,50,500,Gdx.graphics.getHeight()-100);
		shapeRenderer=new ShapeRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		batch.end();
		flappyBirdGame.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
