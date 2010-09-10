package com.iwildwest.user;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public class CrackInformationHolder {

	private Bitmap picture;
	private Point position;
	private long startDisplayingTime;

	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}

	public Bitmap getPicture() {
		return picture;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Point getPosition() {
		return position;
	}

	public void setStartDisplayingTime(long startDisplayingTime) {
		this.startDisplayingTime = startDisplayingTime;
	}

	public long getStartDisplayingTime() {
		return startDisplayingTime;
	}
	
	public void doDraw(Canvas canvas) {
		canvas.drawBitmap(picture, position.x, position.y, null);
	}
}
