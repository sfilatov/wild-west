package com.iwildwest.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class AbstractAnimationView<T extends AnimationThread> extends SurfaceView implements AnimationView<T>, SurfaceHolder.Callback{

	private SurfaceHolder holder;
	
	private T thread;

	public AbstractAnimationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
        holder = getHolder();
        holder.addCallback(this);

        setFocusable(true);
	}
	
	
    public void setThread(T thread) {
    	this.thread = thread;
	}
    
	public T getThread() {
		return thread;
	}
	
	protected SurfaceHolder getSurfaceHolder(){
		return holder;
	}
	
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	public void surfaceCreated(SurfaceHolder holder) {
		thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		thread.pause();
	}


}
