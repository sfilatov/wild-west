package com.iwildwest.cowboys;

public interface CowboysCreator {

	public Cowboy getRandomCowboy(long liveTime);

	public Cowboy getRandomCowboy(long liveTime, boolean aliveAfterShoot);

}
