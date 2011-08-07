package com.iwildwest.levels;

import java.util.NoSuchElementException;

import com.iwildwest.core.PictureManager;
import com.iwildwest.core.Storable;

import android.os.Bundle;
import com.iwildwest.cowboys.Cowboys;

/**
 * Contains all information about levels
 * @author kodla
 */
public class Levels implements Storable{
	public static final int LEVELS_COUNT = 2;
	
	//Bundle Keys
	private static final String KEY = Storable.KEY + "LevelFactory";
	private static final String KEY_LEVEL =KEY + "id";

	private Level[] levels = new Level[LEVELS_COUNT];
	private int currentLevel;
	
	public Levels(PictureManager pictureManager, Cowboys cowboys, LevelListener listener) {
		levels[0] = new Level1(pictureManager, listener);
		levels[0].setCowboys(cowboys);
		
		levels[1] = new Level2(pictureManager, listener);
		levels[1].setCowboys(cowboys);
		
		currentLevel = 0;
	}
	
	public synchronized Level setLevel(int i) {
		currentLevel = i;
		return getCurrentLevel();
	}
	
	public Level getCurrentLevel() {
		return levels[currentLevel];
	}
	
	public int getCurrentLevelNumber() {
		return currentLevel;
	}
	
	public synchronized Level next() {
		if (++currentLevel >= LEVELS_COUNT) throw new NoSuchElementException();
		
		return getCurrentLevel();
	}
	
	public boolean hasNext() {
		return currentLevel < (LEVELS_COUNT - 1);
	}

	public synchronized Level previous() {
		if (--currentLevel < 0) throw new NoSuchElementException();
		
		return getCurrentLevel();
	}
	
	public boolean hasPrevious() {
		return currentLevel > 0;
	}
	
	public synchronized void restoreState(Bundle savedState) {
		setLevel(savedState.getInt(KEY_LEVEL)).restoreState(savedState);
	}
	
	public void saveState(Bundle map) {

		map.putInt(KEY_LEVEL, currentLevel);
		
		getCurrentLevel().saveState(map);
	}	
}
