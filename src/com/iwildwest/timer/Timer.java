package com.iwildwest.timer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;

import com.iwildwest.R;
import com.iwildwest.core.Animated;
import com.iwildwest.core.PictureManager;
import com.iwildwest.core.Storable;
import com.iwildwest.core.Utils;

public class Timer implements Animated, Storable{
	private static final int SECOND_IN_MILLIS = 1000;

	private static final String KEY_TIMER = "timer";
	
	private static final int STARTED = 0;
	private static final int STOPED = 1;
	
	
	private TimerListener timerListener;
	
	private Bitmap[] numberPictures;
	
	private long timerInMillis;
	
	private final int numberWidth;
	
	private long startTime;
	
	private byte firstNumber;
	private byte secondNumber;
	
	private static int state;
	
	public Timer(PictureManager pictureManager, TimerListener timerListener){
		
		numberPictures = new Bitmap[]{
				pictureManager.getPicture(R.drawable.n0),
				pictureManager.getPicture(R.drawable.n1),
				pictureManager.getPicture(R.drawable.n2),
				pictureManager.getPicture(R.drawable.n3),
				pictureManager.getPicture(R.drawable.n4),
				pictureManager.getPicture(R.drawable.n5),
				pictureManager.getPicture(R.drawable.n6),
				pictureManager.getPicture(R.drawable.n7),
				pictureManager.getPicture(R.drawable.n8),
				pictureManager.getPicture(R.drawable.n9),
		};
		
		numberWidth = numberPictures[0].getWidth();
		
		this.timerListener = timerListener;
		state = STOPED;
	}

	@Override
	public void doDraw(Canvas canvas, Rect rect, Point point) {
		if (firstNumber > 0) 
			canvas.drawBitmap(numberPictures[firstNumber], point.x, point.y, null);
		
		canvas.drawBitmap(numberPictures[secondNumber], point.x + numberWidth, point.y, null);
	}

	@Override
	public void doPhysics(long currentTime) {
		if (state == STOPED) return;
		
		timerInMillis = timerInMillis - (currentTime - startTime);
		startTime = currentTime;
		
		if (timerInMillis < 0) {
			timerInMillis = 0;
			state = STOPED;
			timerListener.execute();
		}
		
		int timeInSeconds = (int)(timerInMillis / SECOND_IN_MILLIS);
		secondNumber = (byte)(timeInSeconds % 10);
		firstNumber = (byte)((timeInSeconds % 100) / 10); 
	}

	@Override
	public void restoreState(Bundle bundle) {
		timerInMillis = bundle.getLong(Utils.getKey(this, KEY_TIMER));
	}

	@Override
	public void saveState(Bundle map) {
		map.putLong(Utils.getKey(this, KEY_TIMER), timerInMillis);
	}
	
	public void setTimeInSeconds(long seconds) {
		if (seconds > 99 && seconds < 0) 
			throw new Error("Seconds must be greater than 0 but less than 100");
		timerInMillis = seconds * SECOND_IN_MILLIS;
	}
	
	public void start(long currentTime){
		startTime = currentTime;
		state = STARTED;
	}

}
