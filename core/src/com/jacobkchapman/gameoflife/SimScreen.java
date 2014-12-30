package com.jacobkchapman.gameoflife;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class SimScreen implements Screen {
	
	//limiter used to ensure only one update at a time, before switching
	//back to setup. allows for hold space to sim. (a bit hacky i know).
	int limiter = 0;
	
	final GameOfLife game;
	
	OrthographicCamera camera;
	
	public SimScreen( final GameOfLife game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameOfLife.WIDTH, GameOfLife.HEIGHT);
		
	}

	@Override
	public void render(float delta) {
		if( limiter != 1) {
			update();
			draw();
			limiter++;
		}
		game.setScreen(new SetupScreen(game));
	}
	
	/*update first checks the number of neighbours each cell has, and
	  sets the next state accordingly. after every cell has been checked,
	  the current state is then actually changed. */
	public void update() {
		int neighbours;
		for( int ii = 0; ii < GameOfLife.HEIGHT / GameOfLife.SIZE; ii ++) {
			for( int jj = 0; jj < GameOfLife.WIDTH / GameOfLife.SIZE; jj ++) {
				neighbours = game.grid[ii][jj].getNumOfNeighbours(game.grid);
				boolean nextState = false;
				if( game.grid[ii][jj].isAlive() && neighbours < 2) {
					nextState = false;
				}
				if( game.grid[ii][jj].isAlive() && (neighbours == 2 || neighbours == 3)) {
					nextState = true;
				}
				if( game.grid[ii][jj].isAlive() && neighbours > 3) {
					nextState = false;
				}
				if( !game.grid[ii][jj].isAlive() && neighbours == 3) {
					nextState = true;
				}
				game.grid[ii][jj].setNext( nextState);
			}
		}
		for( int ii = 0; ii < GameOfLife.HEIGHT / GameOfLife.SIZE; ii ++) {
			for( int jj = 0; jj < GameOfLife.WIDTH / GameOfLife.SIZE; jj ++) {
				game.grid[ii][jj].mutate();
			}
		}
	}
	
	//same as setup screen
	public void draw() {
		//set background to white
		Gdx.gl.glClearColor( 255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.shape.begin(ShapeType.Line);
		game.shape.setColor(0, 0, 0, 1);
		for( int ii = 0; ii < GameOfLife.HEIGHT / GameOfLife.SIZE; ii ++) {
			for( int jj = 0; jj < GameOfLife.WIDTH / GameOfLife.SIZE; jj ++) {
				game.grid[ii][jj].draw();
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
