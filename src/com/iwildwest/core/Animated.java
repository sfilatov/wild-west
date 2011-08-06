package com.iwildwest.core;


import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public interface Animated {
	void doDraw(Canvas canvas, Rect rect, Point point);
	
	void doPhysics(long currentTimeInMillies);
	
//	void doSound(SoundManager soundManager);
	
}
