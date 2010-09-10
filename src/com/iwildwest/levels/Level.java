package com.iwildwest.levels;

import java.util.Collection;

import android.view.MotionEvent;

import com.iwildwest.core.Animated;
import com.iwildwest.core.SoundManager;
import com.iwildwest.core.Storable;
import com.iwildwest.cowboys.CowboysCreator;

public interface Level extends Storable, Animated {

	int STATE_START = 0;
	int STATE_STARTING = 1;
	int STATE_STARTED = 2;
	int STATE_RUNNING = 3;
	int STATE_STOPING = 4;
	int STATE_FINISHED = 5;
	
	public void onTouchEvent(MotionEvent event);

	public void setCowboysCreator(CowboysCreator cowboysCreator);
	
	public void setTouchEvents(Collection<MotionEvent> touchEvents);
	
	public void setState(int state);
	
	public int getState();
	
	public void doSound(SoundManager soundManager);
}
