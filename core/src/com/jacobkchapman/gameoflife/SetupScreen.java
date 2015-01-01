package com.jacobkchapman.gameoflife;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

public class SetupScreen implements Screen {
	
	final GameOfLife game;
	
	OrthographicCamera camera;
		
	Vector3 touchPos;
	
	public SetupScreen( final GameOfLife game) {
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameOfLife.WIDTH, GameOfLife.HEIGHT);
		
		touchPos = new Vector3();
		
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
		
		//input, converts pixel coords to array indexes and swaps the
		//state of the touched cell.
		if(Gdx.input.justTouched()) {
			touchPos.set( Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			game.grid[(int) (touchPos.y / GameOfLife.SIZE)][(int) (touchPos.x / GameOfLife.SIZE)].swap();
			game.grid[(int) (touchPos.y / GameOfLife.SIZE)][(int) (touchPos.x / GameOfLife.SIZE)].mutate();
		}
		
		//press / hold space to activate simulation
		if(Gdx.input.isKeyPressed(Keys.C)) {
			game.setScreen(new SimScreen(game));
		}

	}
	
	private void draw() {
		//set background to white
		Gdx.gl.glClearColor( 255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//loop through and draw all cells
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
