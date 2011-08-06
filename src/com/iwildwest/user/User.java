package com.iwildwest.user;

import java.lang.reflect.Field;

import com.iwildwest.R;
import com.iwildwest.core.Animated;
import com.iwildwest.core.PictureManager;
import com.iwildwest.core.SoundManager;
import com.iwildwest.core.Storable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;

/**
 * 
 * @author kodla
 */
public class User implements Animated, Storable{
	public static final int MAX_LIVES = 10;
	
	private int lives = MAX_LIVES;
	
	private static final String KEY_LIVES = "iWildWestLives"; 
	
//	private CartridgeDrawer cartridgeDrawer;
	private Bitmap[] livesPics;
	
	public User(PictureManager pictureManager) {
		livesPics = new Bitmap[]{
			pictureManager.getPicture(R.drawable.horseshoe_sectors_00),
			pictureManager.getPicture(R.drawable.horseshoe_sectors_10),
			pictureManager.getPicture(R.drawable.horseshoe_sectors_20),
			pictureManager.getPicture(R.drawable.horseshoe_sectors_30),
			pictureManager.getPicture(R.drawable.horseshoe_sectors_40),
			pictureManager.getPicture(R.drawable.horseshoe_sectors_50),
			pictureManager.getPicture(R.drawable.horseshoe_sectors_60),
			pictureManager.getPicture(R.drawable.horseshoe_sectors_70),
			pictureManager.getPicture(R.drawable.horseshoe_sectors_80),
			pictureManager.getPicture(R.drawable.horseshoe_sectors_90),
			pictureManager.getPicture(R.drawable.horseshoe_sectors_100),
		};

		
//		cartridgeDrawer = new CartridgeDrawer(resources);
	}
	
	public boolean isAlive() {
		return lives > 0;
	}
	
	public void resetLivesToMaximum() {
		lives = MAX_LIVES;
	}
	
	public void decreaseLive() {
		lives -= 1;
	}
	
	public void increaseLives(int c) {
		lives += c;
	}
	
	public void saveState(Bundle map) {
		
		map.putInt(KEY_LIVES, lives);
		
	}
	
	public void restoreState(Bundle savedState) {
		lives = savedState.getInt(KEY_LIVES);
	}
	
	public void doDraw(Canvas canvas, Rect rect, Point point) {
		canvas.drawBitmap(livesPics[lives], point.x, point.y, null);
		
//		cartridgeDrawer.doDraw(canvas);
	}

	public void doPhysics(long currentTimeInMillies) {
		// TODO Auto-generated method stub
		
	}

//	private class CartridgeDrawer {
//		private Bitmap cartridgePic;
//		private Bitmap bulletPic;
//		
//		public CartridgeDrawer(Resources resources) {
//			cartridgePic = BitmapFactory.decodeResource(resources, R.drawable.cartridge); 
//			bulletPic = BitmapFactory.decodeResource(resources, R.drawable.bullet); 
//		}
//		
//		public void doDraw(Canvas canvas) {
//			int x = 0;
//			int y = 0;
//			
//			canvas.drawBitmap(cartridgePic, x, y, null);
//			for (int i = 0; i < bullets; i++) {
//				canvas.drawBitmap(bulletPic, x + 28, y + 10, null);
//			}
//		}
//	}
}
