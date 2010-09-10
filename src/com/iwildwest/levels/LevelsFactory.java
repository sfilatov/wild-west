package com.iwildwest.levels;

import java.util.NoSuchElementException;

import com.iwildwest.core.PictureManager;
import com.iwildwest.core.Storable;
import com.iwildwest.cowboys.CowboysCreator;
import com.iwildwest.cowboys.CowboysFactory;
import com.iwildwest.levels.*;

import android.content.res.Resources;
import android.os.Bundle;

/**
 * Contains all information about levels
 * @author kodla
 */
public class LevelsFactory implements Storable{
	public static final int LEVELS_COUNT = 2;
	
	//Bundle Keys
	private static final String KEY = Storable.KEY + "LevelFactory";
	private static final String KEY_LEVEL =KEY + "id";

	private Level[] levels = new Level[LEVELS_COUNT];
	private int currentLevel;
	
	public LevelsFactory(PictureManager pictureManager, CowboysCreator cowboysFactory, LevelListener listener) {
		levels[0] = new Level1(pictureManager, listener);
		levels[0].setCowboysCreator(cowboysFactory);
		
		levels[1] = new Level2(pictureManager, listener);
		levels[1].setCowboysCreator(cowboysFactory);
		
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
