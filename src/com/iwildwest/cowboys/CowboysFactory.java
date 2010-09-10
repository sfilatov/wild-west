package com.iwildwest.cowboys;

import com.iwildwest.core.PictureManager;

public class CowboysFactory implements CowboysCreator {
	private int lastCowboy = -1;
	private PictureManager pictureManager;
	protected CowboyListener cowboyListener;
	
	public CowboysFactory(PictureManager pictureManager, CowboyListener cowboyListener) {
		this.pictureManager = pictureManager;
		this.cowboyListener = cowboyListener;
	}
	
	public Cowboy getRandomCowboy(long liveTime) {
		return getRandomCowboy(liveTime, false);
	}
		
	public Cowboy getRandomCowboy(long liveTime, boolean aliveAfterShoot) {
		int rand = (int)(Math.round(Math.random() * 4));
		
		Cowboy cowboy = null;
		
		switch (rand) {
		case 0: 
			cowboy = new Cowboy1(pictureManager, liveTime);
			break;
		case 1: cowboy = new Cowboy2(pictureManager, liveTime);
			break;
		case 2: cowboy = new Cowboy3(pictureManager, liveTime);
			break;
		case 3: cowboy = new Cowboy4(pictureManager, liveTime);
			break;
		case 4: cowboy = new Cowboy5(pictureManager, liveTime);
			break;
		default:
			cowboy = new Cowboy1(pictureManager, liveTime);
		}
		
		cowboy.setListener(cowboyListener);
		cowboy.setState(com.iwildwest.cowboys.State.APPEAR_ANIMATION_STATE, 0);
		
		return cowboy;
	}

}
