package com.iwildwest.levels;

import com.iwildwest.R;
import com.iwildwest.core.PictureManager;
import com.iwildwest.cowboys.Cowboy;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

class Level2 extends DoubleLayerLevel {

	public Level2(PictureManager pictureManager, LevelListener listener) {
		super(listener);
		
		backgroundImage = pictureManager.getPicture(R.drawable.level2);
		buildingImage = pictureManager.getPicture(R.drawable.level2up);

		cowboysArray = new Cowboy[6];
		cowboysPositions = new CowboyPosition[6];

		state = Level.STATE_STARTED;
	}

	@Override
	protected Rect[] getKillZones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTimeBetweenCowboys() {
		return 3000;
	}

	@Override
	public long getCowboysLiveTime() {
		return 800;
	}

	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
