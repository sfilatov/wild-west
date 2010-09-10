package com.iwildwest.core;


public interface SoundManager {
	
	/**
	 * Initialize sound manager and all sounds
	 */
	public void init();
	
	/**
	 * Correctly release sound pool instance
	 */
	public void release();

	/**
	 * Play sound defined by id
	 * @param sound - R.raw.id
	 */
	public void playSound(int sound);
	
	
	public void stopSound(int sound);

	/**
	 * Enabling/disabling sound playing
	 * @param enabled - true or false.
	 */
	public void setEnabled(boolean enabled);

	public boolean isEnabled();
}
