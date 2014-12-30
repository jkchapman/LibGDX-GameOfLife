package com.jacobkchapman.gameoflife;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class SetupScreen implements Screen {
	
	final GameOfLife game;
	
	OrthographicCamera camera;
	
	Cell[][] grid;
	
	public SetupScreen( final GameOfLife game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		grid = new Cell[game.HEIGHT / game.SIZE][game.WIDTH / game.SIZE];
		for( int ii = 0; ii < game.HEIGHT / game.SIZE; ii++) {
			for( int jj = 0; jj < game.WIDTH / game.SIZE; jj++) {
				grid[ii][jj] = new Cell(jj, ii, game.SIZE, game.shape);
			}
		}
	}

	@Override
	public void render(float delta) {
		update();
		draw();
	}
	
	private void update() {
		//setup camera
		camera.update();
		game.shape.setProjectionMatrix( camera.combined);
	}
	
	private void draw() {
		//set background to white
		Gdx.gl.glClearColor( 255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.shape.begin(ShapeType.Line);
		game.shape.setColor(0, 0, 0, 1);
		for( int ii = 0; ii < game.HEIGHT / game.SIZE; ii ++) {
			for( int jj = 0; jj < game.WIDTH / game.SIZE; jj ++) {
				grid[ii][jj].draw();
			}
		}
		game.shape.end();
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
