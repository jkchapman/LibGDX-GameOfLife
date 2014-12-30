package com.jacobkchapman.gameoflife;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

@SuppressWarnings("serial")
public class Cell extends Rectangle{
	
	private boolean alive;
	private boolean nextAlive;
	private ShapeRenderer shape;

	public Cell( int x, int y, int size, ShapeRenderer shape) {
		super( x, y, size, size);
		alive = false;
		nextAlive = false;
		this.shape = shape;
	}
	
	/*
	 * if cell is dead, drawn as lines, if alive filled (to give the 
	 *grid look
	 *
	 */
	public void draw() {
		if( alive && shape.getCurrentType() == ShapeType.Line) {
			shape.set( ShapeType.Filled);
		}
		shape.rect( x * width, y * height, width, height);
		if( shape.getCurrentType() == ShapeType.Filled) {
			shape.set( ShapeType.Line);
		}
	}
	
	public void setNext( boolean next) {
		nextAlive = next;
	}
	
	public void swap() {
		if (alive == false)
			nextAlive = true;
		else
			nextAlive = false;
	}
	
	public void mutate() {
		alive = nextAlive;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	/*
	 * returns the number of neighbours from a passed in 2d array of cells,
	 * with careful array index checking.
	 */
	public int getNumOfNeighbours( Cell[][] grid) {
		int result = 0;
		
		//left
		if( x != 0) {
			if(grid[(int)(y)][(int)(x - 1)].isAlive()) {
				result++;
			}
		}
		
		//right
		if( x != (GameOfLife.WIDTH / GameOfLife.SIZE) - 1) {
			if(grid[(int) y][(int) (x + 1)].isAlive()) {
				result++;
			}
		}
		
		//bottom right
		if(  x != (GameOfLife.WIDTH / GameOfLife.SIZE) - 1 &&
				y != 0) {
			if(grid[(int)(y - 1)][(int)(x + 1)].isAlive()) {
				result++;
			}
		}
		
		//bottom left
		if(  x != 0 && y != 0) {
			if(grid[(int)(y - 1)][(int)(x - 1)].isAlive()) {
				result++;
			}
		}
		
		//bottom
		if( y != 0) {
			if(grid[(int)(y - 1)][(int)(x)].isAlive()) {
				result++;
			}
		}
		
		//top left
		if( x != 0 && y != (GameOfLife.HEIGHT / GameOfLife.SIZE) - 1) {
			if( grid[(int)(y + 1)][(int)(x - 1)].isAlive()) {
				result++;
			}
		}
		
		//top right
		if( x != (GameOfLife.WIDTH / GameOfLife.SIZE) - 1 &&
				y!= (GameOfLife.HEIGHT / GameOfLife.SIZE) - 1) {
			if( grid[(int)(y + 1)][(int)(x + 1)].isAlive()) {
				result++;
			}
		}
		
		//top
		if( y != (GameOfLife.HEIGHT / GameOfLife.SIZE) - 1) {
			if(grid[(int)(y + 1)][(int)(x)].isAlive()) {
				result++;
			}
		}
		return result;
	}
	
}
