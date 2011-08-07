package com.iwildwest.user;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;

import com.iwildwest.R;
import com.iwildwest.core.Animated;
import com.iwildwest.core.PictureManager;
import com.iwildwest.core.Storable;

public class SoundButton implements Animated, Storable {

	private static final String KEY_ENABLED = "WildWest.SoundButton.enabled";

	private boolean enabled;

	private PictureManager pictureManager;

	public SoundButton(PictureManager pictureManager) {
		this.pictureManager = pictureManager;
		this.enabled = true;
	}

	public void restoreState(Bundle bundle) {
		enabled = bundle.getBoolean(KEY_ENABLED);
	}

	public void saveState(Bundle map) {
		map.putBoolean(KEY_ENABLED, enabled);
	}

	public void doPhysics(long currentTimeInMillies) {
		// TODO add animation
	}

	public void doDraw(Canvas canvas, Rect rect, Point point) {
		Bitmap currentBitmap;
		if (enabled)
			currentBitmap = pictureManager.getPicture(R.drawable.sound_on);
		else
			currentBitmap = pictureManager.getPicture(R.drawable.sound_off);
		
		if (currentBitmap != null)
			canvas.drawBitmap(currentBitmap, point.x, point.y, null);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
