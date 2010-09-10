package com.iwildwest.levels;

public class CowboyPosition {
	private int x;
	private int y;
	private int h;
	private int v;
	
	public CowboyPosition(int x, int y, int hAlign, int vAlign) {
		this.x = x;
		this.y = y;
		h = hAlign;
		v = vAlign;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

}
