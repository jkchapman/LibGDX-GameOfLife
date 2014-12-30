package com.jacobkchapman.gameoflife;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Cell {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean alive;
	private ShapeRenderer shape;

	public Cell( int x, int y, int size, ShapeRenderer shape) {
		this.x = x;
		this.y = y;
		width = size;
		height = size;
		alive = false;
		this.shape = shape;
	}
	
	public void draw() {
		if( alive && shape.getCurrentType() == ShapeRenderer.ShapeType.Line) {
			shape.set( ShapeRenderer.ShapeType.Filled);
		}
		shape.rect( x * width, y * height, width, height);
		if( shape.getCurrentType() == ShapeRenderer.ShapeType.Filled) {
			shape.set( ShapeRenderer.ShapeType.Line);
		}
	}
	
}
