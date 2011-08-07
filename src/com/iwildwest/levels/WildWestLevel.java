package com.iwildwest.levels;

import java.util.ArrayList;
import java.util.Collection;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;

import com.iwildwest.core.SoundManager;
import com.iwildwest.cowboys.Cowboy;
import com.iwildwest.cowboys.Cowboys;
import com.iwildwest.cowboys.State;

public abstract class WildWestLevel implements Level {
	//Bundle keys
	private static final String KEY_COWBOYS_ARRAY = "iWildWest.level.CowboysPositions";
	private static final String KEY_ELAPSED_TIME = "iWildWest.level.elapsedTime";
	private static final String KEY_STATE = "iWildWest.level.state";
	
	protected int state = Level.STATE_FINISHED;
	protected LevelListener listener;

	protected long startTime = -1;
    protected long elapsedTime = -1;
	protected long lastAdded = -1;

	protected Cowboys cowboys;
	protected Cowboy[] cowboysArray;
	protected int cowboysAtLevel = 0;
	protected int cowboysAlreadyWereAdded = 0;
	
	private Rect[] killZones;
	
	private Collection<MotionEvent> killEvents = new ArrayList<MotionEvent>();
	
	private Collection<MotionEvent> touchEvents = new ArrayList<MotionEvent>();

	public WildWestLevel(LevelListener listener) {
		this(listener, null);
	}
	
	public WildWestLevel(LevelListener listener, Cowboys cowboys) {
		this.listener = listener;
		this.cowboys = cowboys;
		
		killZones = getKillZones();
		
		state = Level.STATE_STARTING;
	}

	public final int getCowboysAtLevel() {
		//TODO fix this
		return 200;
	}

	public abstract long getTimeBetweenCowboys();
	
	public abstract long getCowboysLiveTime();
	
	protected abstract Rect[] getKillZones();
	
	public void onTouchEvents(Collection<MotionEvent> touchEvents){
		this.touchEvents.addAll(touchEvents);
	}
	
	public void doPhysics(long currentTime) {
		killEvents.clear();
		
		if (startTime == -1) {
			if (elapsedTime == -1) {
				startTime = currentTime;
				state = Level.STATE_RUNNING;
			}
			else {
				startTime = currentTime - elapsedTime;
				elapsedTime = -1;
			}
		}
		
		if (lastAdded == -1) lastAdded = currentTime;
		
		for (MotionEvent event : touchEvents) {
			for (int i = 0; i < killZones.length; i++) {
				if (killZones[i].contains((int)event.getX(), (int)event.getY()) && cowboysArray[i] != null) {
					cowboysArray[i].setState(State.DEAD_ANIMATION_STATE, currentTime);
					killEvents.add(event);
				}
			}
		}
		
		touchEvents.removeAll(killEvents);
		
		for (int i = 0; i < cowboysArray.length; i++)
			if (cowboysArray[i] != null) {
				cowboysArray[i].doPhysics(currentTime);
				
				if (cowboysArray[i].getState() == com.iwildwest.cowboys.State.FINISH_STATE)
				{
					cowboysArray[i] = null;
					cowboysAtLevel--;
				}
			}
		
		if (cowboysAlreadyWereAdded < getCowboysAtLevel())
		{
			if (currentTime - lastAdded > getTimeBetweenCowboys()) addCowboy(currentTime);
		}
//		else if (cowboysAtLevel == 0) {
//			state = STATE_FINISHED;
//			listener.levelFinished();
//		}
	}

	public void doSound(SoundManager soundManager) {
		for (int i=0; i < cowboysArray.length; i++)
			if (cowboysArray[i] != null) cowboysArray[i].doSound(soundManager);
	}

	protected void addCowboy(long currentTime) {
		int rand = (int) (Math.random() * (cowboysArray.length - cowboysAtLevel));
		if (rand >= cowboysArray.length - cowboysAtLevel) rand = cowboysArray.length - cowboysAtLevel;
		
		for (int i = 0; i < cowboysArray.length; i++) {
			if (cowboysArray[i] != null) continue;
			if (rand != 0)	rand--;
			else 
			{
				cowboysArray[i] = this.cowboys.getRandomCowboy(getCowboysLiveTime());
				break;
			}
		}
		
		cowboysAtLevel++;
		cowboysAlreadyWereAdded++;
		lastAdded = currentTime;
	}
		
	public void setCowboys(Cowboys cowboys) {
		this.cowboys = cowboys;
	}

	//----------------- System ----------------
	public void restoreState(Bundle savedState) {
		state = savedState.getInt(KEY_STATE);
		elapsedTime = savedState.getLong(KEY_ELAPSED_TIME);
	}

	public void saveState(Bundle map) {
		
		map.putInt(KEY_STATE, state);
		
		if (startTime == -1) map.putLong(KEY_ELAPSED_TIME, -1);
		else map.putLong(KEY_ELAPSED_TIME, System.currentTimeMillis() - startTime);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		
		switch (this.state) {
		case Level.STATE_START : this.state = Level.STATE_STARTING;
		}
	}
	
}
