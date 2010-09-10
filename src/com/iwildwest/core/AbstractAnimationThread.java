package com.iwildwest.core;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public abstract class AbstractAnimationThread extends Thread implements AnimationThread{
	//System parameters
	private static final int THREAD_TIMEOUT_MILIS = 0;

	private SurfaceHolder holder;
	
	private boolean running = false;
	private long timeout;

	public AbstractAnimationThread(SurfaceHolder holder)
	{
		this.holder = holder;
		timeout = THREAD_TIMEOUT_MILIS;
	}
	
	@Override
    public void run() {
		while (running) {
//			Log.i("{k.Perfomance}Main thread", "Run start:" + System.currentTimeMillis());
			doPhysics();
//			Log.i("{k.Perfomance}Main thread", "Run physics ended:" + System.currentTimeMillis());
			
			doCanvasDraw();
//			Log.i("{k.Perfomance}Main thread", "Run draw ended:" + System.currentTimeMillis());
			
			doSound();

//			Log.i("{k.Perfomance}Main thread", "Run end:" + System.currentTimeMillis());
			try {
				sleep(timeout);
			}
			catch (Exception e) {}
//			Log.i("{k.Perfomance}Main thread", "Run awake:" + System.currentTimeMillis());
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
	
	protected void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	
	protected long getTimeout() {
		return timeout;
	}


}
