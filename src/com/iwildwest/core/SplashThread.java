package com.iwildwest.core;

import com.iwildwest.R;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

public final class SplashThread extends AbstractAnimationThread{
	
	private static final int DELAY = 100;
	private static final int COUNT = 9;
	
	private Bitmap[] splashImages;
	
	private int currentPosition;
	private long lastUpdateTime;
	
	private ExtendedContext context;
	
	public SplashThread(SurfaceHolder holder, ExtendedContext extendedContext) {
		super(holder);
		this.context = extendedContext;
		PictureManager pictureManager = context.getPictureManager();
		
		if (pictureManager == null) {
			Log.e("SplashThread", "Picture manager not loaded");
			return;
		}

		splashImages = new Bitmap[COUNT];
		currentPosition = 0;
		splashImages[currentPosition++] = pictureManager.getPicture(R.drawable.s10000);
		splashImages[currentPosition++] = pictureManager.getPicture(R.drawable.s10001);
		splashImages[currentPosition++] = pictureManager.getPicture(R.drawable.s10002);
		splashImages[currentPosition++] = pictureManager.getPicture(R.drawable.s10003);
		splashImages[currentPosition++] = pictureManager.getPicture(R.drawable.s10004);
		splashImages[currentPosition++] = pictureManager.getPicture(R.drawable.s10005);
		splashImages[currentPosition++] = pictureManager.getPicture(R.drawable.s10006);
		splashImages[currentPosition++] = pictureManager.getPicture(R.drawable.s10007);
		splashImages[currentPosition++] = pictureManager.getPicture(R.drawable.s10008);
		currentPosition = 0;
		
		lastUpdateTime = 0;
	}

	public void doDraw(Canvas canvas) {
		canvas.drawBitmap(splashImages[currentPosition],
				new Rect(0, 0, splashImages[currentPosition].getWidth(), splashImages[currentPosition].getHeight()),
				new Rect(0, 0, canvas.getWidth(), canvas.getHeight()),
				null);
		
	}

	public void doPhysics() {
		if (currentPosition == COUNT - 1) {
			return;
		}
		
		long currentTime = System.currentTimeMillis();
		
		if (lastUpdateTime == 0) lastUpdateTime = currentTime;
		
		long changeTime = currentTime - lastUpdateTime;
		
		if (changeTime > DELAY) {
			currentPosition ++;
			lastUpdateTime = currentTime;
		}
	}

	public void doSound() {
	}

}
