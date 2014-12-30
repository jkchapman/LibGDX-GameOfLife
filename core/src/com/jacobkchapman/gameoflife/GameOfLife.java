package com.jacobkchapman.gameoflife;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameOfLife extends Game {
	public final static int WIDTH = 800;
	public final static int HEIGHT = 480;
	public final static int SIZE = 5;
	public ShapeRenderer shape;
	public Cell[][] grid;
	
	@Override
	public void create () {
		shape = new ShapeRenderer();
		//required to allow the shapetype to change mid drawing
		shape.setAutoShapeType(true);
		//setting up the grid
		grid = new Cell[HEIGHT / SIZE][WIDTH / SIZE];
		for( int ii = 0; ii < HEIGHT / SIZE; ii++) {
			for( int jj = 0; jj < WIDTH / SIZE; jj++) {
				grid[ii][jj] = new Cell(jj, ii, SIZE, shape);
			}
		}
		this.setScreen(new SetupScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose() {
		shape.dispose();
	}
}
