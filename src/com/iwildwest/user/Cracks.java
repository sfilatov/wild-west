package com.iwildwest.user;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;

import com.iwildwest.R;
import com.iwildwest.core.Animated;
import com.iwildwest.core.PictureManager;
import com.iwildwest.core.SoundManager;
import com.iwildwest.core.Storable;

public class Cracks implements Animated, Storable{

//	private CrackInformation[] cracks;
//	private final int ZONES_COUNT = 12;
//	private int cracksCount = 0;
//	private boolean[] zonesPlaced = new boolean[ZONES_COUNT];
//	public long CRACK_LIVE_TIME = 1000;
	
	private Bitmap[] cracksPictures;
	private ArrayList<CrackInformation> cracks;
	
	private int screenHeight;
	private int screenWidth;
	
	private SoundManager soundManager;
	
	public Cracks(PictureManager pictureManager, SoundManager soundManager) {
		cracksPictures = new Bitmap[] {
				pictureManager.getPicture(R.drawable.crack1),				
				pictureManager.getPicture(R.drawable.crack2),
				pictureManager.getPicture(R.drawable.crack3),
				pictureManager.getPicture(R.drawable.crack4)
		};
		
		this.soundManager = soundManager;
		
		cracks = new ArrayList<CrackInformation>();
		
//		cracks = new CrackInformation[size];
//		for (int i = 0; i < cracks.length; i++) {
//			cracks[i] = new CrackInformation();
//			cracks[i].setStartDisplayingTime(-1);
//		}
	}
	
	public void doDraw(Canvas canvas, Rect rect, Point point) {
//		for (int i = 0; i < cracks.length; i++) {
//			if (cracks[i].getStartDisplayingTime() != -1) {
//				if (cracks[i].getStartDisplayingTime() == -2) cracks[i].setStartDisplayingTime(nowTime);
//
//				if (nowTime - cracks[i].getStartDisplayingTime() > CRACK_LIVE_TIME)
//					removeCrack(i);
//				else cracks[i].doDraw(canvas);
//			}
//		}
		
		setupRandom(rect);
		
		for (CrackInformation holder : cracks) {
			holder.doDraw(canvas);
		}
	}

	private void setupRandom(Rect rect) {
		screenHeight = rect.height();
		screenWidth = rect.width();
	}
	
	public void addCrack() {
		new Thread() {
			public void run(){
				soundManager.playSound(R.raw.player_broke_glass1);
			}
		}.start();
		
//		int i;
//		for (i = 0; i < cracks.length && cracks[i].getStartDisplayingTime() != -1; i++);
//		
//		if (i > cracks.length) i = 0;
//		
//		cracks[i].setPicture(getRandomCrackBitmap());
//		cracks[i].setStartDisplayingTime(-2);
//		
//		int index = (int) (Math.random() * (ZONES_COUNT - cracksCount));
//		if (index == (ZONES_COUNT - cracksCount)) index = (ZONES_COUNT - cracksCount);
//		
//		int j = 0;
//		for (; index > 0; j++)
//			if (!zonesPlaced[j]) index--;
//		
//		Point point = new Point((int) ((j % 4) * 120 + Math.random()*40), (int) ((j / 4) * 100 + Math.random()*20));
//		Log.i("[k]", "p.x=" + point.x);
//		Log.i("[k]", "p.y=" + point.y);
//		cracks[i].setPosition(point);
//		zonesPlaced[j] = true;
//		cracksCount++;
		
		CrackInformation holder = new CrackInformation();
		holder.setPicture(getRandomCrackBitmap());
		holder.setPosition(getRandomCrackPosition());		
		cracks.add(holder);
	}
	
	
	private Point getRandomCrackPosition() {
		Point point = new Point((int)(10 + Math.random()*(screenWidth - 10)), (int) (10 + Math.random()*(screenHeight - 10)));
		Log.i("[k]", "p.x=" + point.x);
		Log.i("[k]", "p.y=" + point.y);	
		return point;
	}

//	private void removeCrack(int i) {
//		cracks[i].setStartDisplayingTime(-1);
//
//		//TODO: HARD CODE!!!
//		int index = (cracks[i].getPosition().x / 120) + 4 * (cracks[i].getPosition().y / 100);
//		Log.i("[k]", "index=" + index);
//		Log.i("[k]", "x=" + cracks[i].getPosition().x);
//		Log.i("[k]", "y=" + cracks[i].getPosition().y);
//		zonesPlaced[index] = false;
//		cracksCount--;
//	}
	
	public void removeCracks(){
		cracks.clear();
	}
	
	private Bitmap getRandomCrackBitmap() {
		int rand = (int) (Math.random() * cracksPictures.length);
		
		if (rand > cracksPictures.length) rand = cracksPictures.length;
		
		return cracksPictures[rand];
	}

	public void saveState(Bundle map) {
		// TODO 
	}

	public void restoreState(Bundle savedState) {
		// TODO 
		
	}

	public void doPhysics(long currentTimeInMillies) {
		// TODO Auto-generated method stub
		
	}

	public void doSound(SoundManager soundManager) {
		
		
	}
	
}
