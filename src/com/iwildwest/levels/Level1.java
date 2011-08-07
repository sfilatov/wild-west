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


        cowboysPositions = new CowboyPosition[] {
                new CowboyPosition(110, 75, Cowboy.HORIZONTAL_CENTER, Cowboy.VERTICAL_TOP),
                new CowboyPosition(363, 75, Cowboy.HORIZONTAL_CENTER, Cowboy.VERTICAL_TOP),
                new CowboyPosition(240, 75, Cowboy.HORIZONTAL_CENTER, Cowboy.VERTICAL_TOP),
                new CowboyPosition(240, 280, Cowboy.HORIZONTAL_CENTER, Cowboy.VERTICAL_BOTTOM),
                new CowboyPosition(110, 280, Cowboy.HORIZONTAL_CENTER, Cowboy.VERTICAL_BOTTOM),
                new CowboyPosition(363, 280, Cowboy.HORIZONTAL_CENTER, Cowboy.VERTICAL_BOTTOM),

        };

        cowboysArray = new Cowboy[cowboysPositions.length];

        state = Level.STATE_STARTED;
    }

    @Override
    protected Rect[] getKillZones() {
        return new Rect[] {
                new Rect(75, 65, 145, 121),
                new Rect(328, 65, 398, 121),
                new Rect(204, 65, 274, 121),
                new Rect(204, 181, 274, 280),
                new Rect(75, 181, 145, 278),
                new Rect(328, 181, 398, 280)

        };
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
