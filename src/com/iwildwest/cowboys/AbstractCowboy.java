package com.iwildwest.cowboys;

import java.util.TreeMap;

import com.iwildwest.core.PictureManager;
import com.iwildwest.core.SoundManager;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class AbstractCowboy implements Cowboy {

	private StateAttributes currentAttributes;

	private long lastUpdateTime;

	private int substateIndex;
	
	private boolean startPlaySound = false;
	
	private CowboyListener listener;

	private TreeMap<State, StateAttributes> stateToAttributes;

	private PictureManager pictureManager;
	
	
	public AbstractCowboy(PictureManager pictureManager) {
		stateToAttributes = new TreeMap<State, StateAttributes>();
		this.pictureManager = pictureManager;
	}

	protected final void putStateAttributes(StateAttributes attributes) {
		stateToAttributes.put(attributes.getState(), attributes);
	}
	
	public void doDraw(Canvas canvas, Rect dst) {
		doDraw(canvas, dst, null);
	}
	
	public void doDraw(Canvas canvas, Rect dst, Paint paint) {
		if (currentAttributes != null) {
			Bitmap currentImage = pictureManager.getPicture(currentAttributes.getPictureId(substateIndex));
			
			int width, height;
			
			if (currentImage.getWidth() < dst.width()) {
				width = currentImage.getWidth();
				int cx = dst.centerX();
				dst.left = cx - (width >> 1);
				dst.right = cx + (width >> 1);
			}
			else width = dst.width();
			
			if (currentImage.getHeight() < dst.height()) {
				height = currentImage.getHeight();
				int cy = dst.centerY();
				dst.top = cy - (height >> 1);
				dst.bottom = cy + (height >> 1);
			}
			else height = dst.height();
			
			Rect src = new Rect(0,0, width, height);
			
			canvas.drawBitmap(currentImage, src, dst, paint);
		}
	}
	
	public void doDraw(Canvas canvas, int x, int y, Paint paint) {
		if (currentAttributes != null) {
			Bitmap currentImage = pictureManager.getPicture(currentAttributes.getPictureId(substateIndex));
			canvas.drawBitmap(currentImage, x, y, paint);
		}
	}

	public void doDraw(Canvas canvas, int x, int y, int horizontalAlign, int verticalAlign) {
		if (currentAttributes != null) {
			Bitmap currentImage = pictureManager.getPicture(currentAttributes.getPictureId(substateIndex));
			
			x += horizontalAlign*(currentImage.getWidth() >> 1);
			y += verticalAlign*(currentImage.getHeight() >> 1);
			
			canvas.drawBitmap(currentImage, x, y, null);
		}
	}

	public void setState(State state, long currentTime) {
		this.currentAttributes = stateToAttributes.get(state);
		this.substateIndex = 0;
		substateIndexChanged(state, substateIndex);
		lastUpdateTime = currentTime;
	}

	public State getState() {
		if (currentAttributes != null)
			return currentAttributes.getState();
		else 
			return State.FINISH_STATE;
	}

	public void doPhysics(long timeNowInMillisec) {
		
		if (lastUpdateTime == 0) lastUpdateTime = timeNowInMillisec;
			
		long elapsed = timeNowInMillisec - lastUpdateTime;

		if (elapsed < 0) return;

		if (currentAttributes != null) {
			if (elapsed >= currentAttributes.getTimeOut(substateIndex)) {
				substateIndex++;
				substateIndexChanged(currentAttributes.getState(), substateIndex);
				lastUpdateTime = timeNowInMillisec;
				
			}
		}
	}
	
	public void setListener(CowboyListener listener) {
		this.listener = listener;
	}
	
	public CowboyListener getListener() {
		return listener;
	}

	private void setStartPlaySound(boolean flag) {
		this.startPlaySound = flag;
	}
	
	private boolean isStartPlaySound() {
		return startPlaySound;
	}

	private State getNextState(State state) {
		switch (state) {
		case APPEAR_ANIMATION_STATE:
			return State.SHOOT_ANIMATION_STATE;
		case SHOOT_ANIMATION_STATE:
			return State.FINISH_STATE;
		case DEAD_ANIMATION_STATE:
			return State.FINISH_STATE;
		default:
			return State.FINISH_STATE;
		}
	}

	protected void substateIndexChanged(State state, int substate){
		
		if (substate >= currentAttributes.getSubstateCount()) {
			substateIndex = 0;
			
			State next = getNextState(state);
			
			if (next != null ) {
				currentAttributes = stateToAttributes.get(next);
			} else {
				currentAttributes = null;
			}
			
		}  
			
	    setStartPlaySound(true);

	}

	public void doSound(SoundManager soundManagerSound) {
		if (isStartPlaySound()) {
			if (currentAttributes != null) {
				Integer soundId = currentAttributes.getSound(substateIndex);
				if (soundId != null)
					soundManagerSound.playSound(soundId);
			}
		}
		setStartPlaySound(false);
	}
	
	


}
