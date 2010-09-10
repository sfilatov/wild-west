package com.iwildwest.levels;

import com.iwildwest.R;
import com.iwildwest.core.PictureManager;
import com.iwildwest.cowboys.Cowboy;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

class Level1 extends DoubleLayerLevel {
	public Level1(PictureManager pictureManager, LevelListener listener) {
		super(listener);

		backgroundImage = pictureManager.getPicture(R.drawable.level1);
		buildingImage = pictureManager.getPicture(R.drawable.level1up);

		cowboys = new Cowboy[3];
		cowboysPositions = new CowboyPosition[3];
		
		cowboysPositions[0] = new CowboyPosition(110, 73, Cowboy.HORIZONTAL_CENTER, Cowboy.VERTICAL_TOP);
		cowboysPositions[1] = new CowboyPosition(363, 73, Cowboy.HORIZONTAL_CENTER, Cowboy.VERTICAL_TOP);
		cowboysPositions[2] = new CowboyPosition(240, 278, Cowboy.HORIZONTAL_CENTER, Cowboy.VERTICAL_BOTTOM);
		
		state = Level.STATE_STARTED;
	}

	@Override
	protected Rect[] getKillZones() {
		Rect[] killZones = new Rect[3];
		
		killZones[0] = new Rect(75, 65, 145, 121);
		killZones[1] = new Rect(328, 65, 398, 121);
		killZones[2] = new Rect(204, 181, 274, 278);

		return killZones;
	}

	@Override
	public long getTimeBetweenCowboys() {
		return 2000;
	}

	@Override
	public long getCowboysLiveTime() {
		return 3000;
	}

	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
