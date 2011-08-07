package com.iwildwest.core;

import android.graphics.Canvas;

public interface AnimationThread {
	void doDraw(Canvas canvas);

	void doPhysics();
	
	void start();
	
	void pause();

}
