package com.iwildwest.core;

public interface AnimationView<T extends AnimationThread> {
	
	void setThread(T thread);
	
	T getThread();
}
