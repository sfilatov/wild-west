package com.iwildwest.cowboys;

import com.iwildwest.core.SoundManager;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public interface Cowboy {
	
	public static final int SHORT_TIMEOUT = 50;
	public static final int PREPARE_TIMEOUT = 500;
	public static final int SHOOT_TIMEOUT = 500;
	public static final int FADE_TIMEOUT = 100;
	
	// state constants
	public static final int APPEAR_ANIMATION_STATE = 0;
	public static final int SHOOT_ANIMATION_STATE = 1;
	public static final int DEAD_ANIMATION_STATE = 2;
	public static final int FINISH_STATE = 3;
	
	//Align Constants
	final int HORIZONTAL_LEFT = 0;
	final int HORIZONTAL_CENTER = -1;
	final int HORIZONTAL_RIGHT = -2;
	final int VERTICAL_TOP = 0;
	final int VERTICAL_CENTER = -1;
	final int VERTICAL_BOTTOM = -2;
	
	void doPhysics(long timeNowInMillisec);

	void doDraw(Canvas canvas, Rect dst);
	
	void doDraw(Canvas canvas, Rect dst, Paint paint);
	
	void doDraw(Canvas canvas, int x, int y, Paint paint);

	void doDraw(Canvas canvas, int x, int y, int horizontalAlign, int verticalAlign);
	
	void doSound(SoundManager soundManager);
	
	void setState(State state, long currentTime);
	
	void setListener(CowboyListener listener);
	
	State getState();
}
