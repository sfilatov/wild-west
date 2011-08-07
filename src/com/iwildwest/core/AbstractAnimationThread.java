package com.iwildwest.core;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public abstract class AbstractAnimationThread extends Thread implements AnimationThread{
	private SurfaceHolder holder;
	
	private boolean running = false;

	public AbstractAnimationThread(SurfaceHolder holder)
	{
		this.holder = holder;
	}
	
	@Override
    public void run() {
		while (running) {
			doPhysics();
			doCanvasDraw();
		}
	}

	
	private void doCanvasDraw() {
		Canvas canvas = holder.lockCanvas();
		
		synchronized (holder) {
			doDraw(canvas);
			canvas.restore();
		}
		
		holder.unlockCanvasAndPost(canvas);
		
	}
	
	@Override
	public void start(){
		if (!running) {
			running = true;
			super.start();
		}
	}
	
	public void pause(){
		running = false;
	}
}
