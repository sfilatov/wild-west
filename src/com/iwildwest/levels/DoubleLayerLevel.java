package com.iwildwest.levels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public abstract class DoubleLayerLevel extends WildWestLevel {
	protected Bitmap backgroundImage;
	protected Bitmap buildingImage;
	
	protected CowboyPosition[] cowboysPositions;

	public DoubleLayerLevel(LevelListener listener) {
		super(listener);
	}

	public void doDraw(Canvas canvas, Rect rect, Point point) {
    	if (backgroundImage != null) 
    		canvas.drawBitmap(backgroundImage, point.x, point.y, null);

    	for(int i = 0; i < cowboys.length; i++) {
    		if (cowboys[i] != null) 
    			drawCowboy(canvas, i);
    	}
    	
    	if (buildingImage != null) 
    		canvas.drawBitmap(buildingImage, point.x, point.y, null);
	}
	
	protected void drawCowboy(Canvas canvas, int i) {
		cowboys[i].doDraw(canvas, cowboysPositions[i].getX(), cowboysPositions[i].getY(), cowboysPositions[i].getH(), cowboysPositions[i].getV());
	}

}
