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
	

	public SplashThread(SurfaceHolder holder, Game game) {
		super(holder);

		PictureManager pictureManager = game.getPictureManager();
		
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
		if (currentPosition == COUNT - 1) return;
		
		long now = System.currentTimeMillis();
		
		if (lastUpdateTime == 0) lastUpdateTime = now;
		
		if (now - lastUpdateTime > DELAY) {
			currentPosition ++;
			lastUpdateTime = now;
		}
	}

}
