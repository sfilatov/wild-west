package com.iwildwest.cowboys;

public class StateAttributes {
	private long[] timeOuts;
	private State state;
	private int substateCount;
	private Integer[] soundIds;
	private Integer[] pictureIds;
	
	public StateAttributes(State state, long[] timeOuts, Integer[] sounds, Integer[] pictureIds) {
		super();
		this.state = state;
		this.timeOuts = timeOuts;
		this.substateCount = timeOuts.length;
		this.soundIds = sounds;
		this.pictureIds = pictureIds;
	}

	public long getTimeOut(int substateIndex) {
		return timeOuts[substateIndex];
	}

	public int getSubstateCount() {
		return substateCount;
	}
	
	public State getState() {
		return state;
	}

	public Integer getSound(int substateIndex){
		return soundIds[substateIndex];
	}
	
	public Integer getPictureId(int substateIndex){
		return pictureIds[substateIndex];
	}
}
