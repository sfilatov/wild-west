package com.iwildwest.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.iwildwest.buckle.Buckle;
import com.iwildwest.cowboys.CowboyListener;
import com.iwildwest.cowboys.CowboysCreator;
import com.iwildwest.cowboys.CowboysFactory;
import com.iwildwest.levels.Level;
import com.iwildwest.levels.LevelListener;
import com.iwildwest.levels.LevelsFactory;
import com.iwildwest.timer.Timer;
import com.iwildwest.timer.TimerListener;
import com.iwildwest.user.CracksFactory;
import com.iwildwest.user.SoundButton;
import com.iwildwest.user.User;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public final class MainThread extends AbstractAnimationThread implements LevelListener, CowboyListener, TimerListener {
	//CONSTANTS
	//Game State
	public static final int STATE_ONSTART = 0;
	public static final int STATE_RUNNING = 1; 
	public static final int STATE_PAUSE = 2; 

	//Object positions constants
	private static final Rect BUCKLE_RECT = new Rect(0, 240, 64, 304);
	private static final Rect USER_RECT = new Rect(400, 240, 472, 316);
	private static final Rect SOUND_RECT = new Rect(0, 0, 59, 51);
	private static final Rect TIMER_RECT = new Rect(200, 0, 272, 44);

	private int state = STATE_ONSTART;
	
	//private DrawThread draw;
	
	private List<MotionEvent> touches = Collections.synchronizedList(new LinkedList<MotionEvent>());
	private LevelsFactory levelsFactory;
	private CracksFactory cracksFactory;
	private CowboysCreator cowboysFactory;
	private User user;
	private Buckle buckle;
	private SoundButton soundButton;
	
	private SoundManager soundManager;
	
	private PictureManager pictureManager;
	
	private boolean levelFinished;
	private Timer timer;
	
	public MainThread(SurfaceHolder holder, ExtendedContext extendedContext)
	{
		super(holder);
		
		//draw = new DrawThread();
		
		pictureManager = extendedContext.getPictureManager();
		soundManager = extendedContext.getSoundManager();
		
		cowboysFactory = new CowboysFactory(pictureManager, this);
		levelsFactory = new LevelsFactory(pictureManager, cowboysFactory, this);
		cracksFactory = new CracksFactory(pictureManager, soundManager);
		user = new User(pictureManager);
		buckle = new Buckle(pictureManager, soundManager);
		soundButton = new SoundButton(pictureManager);
		timer = new Timer(pictureManager, this);
		
		levelFinished = false;
		state = STATE_ONSTART;
		
	}
	
//------------------------- Event Listeners ------------------------
	
	public boolean onTouchEvent(MotionEvent event) {
		if (MotionEvent.ACTION_DOWN != event.getAction()) {
			return false;
		}
		
		int eventX = (int)event.getX();
		int eventY = (int)event.getY();
		
		if (isPauseButtonZone(eventX, eventY)) {
			state = STATE_PAUSE;
			return true;    		
		}
		
		if (isChargerZone(eventX, eventY)) {
			buckle.reloadCharger();
			return true;
		}
		
		if (isSoundZone(eventX, eventY)) {
			soundManager.setEnabled(!soundManager.isEnabled());
			soundButton.setEnabled(soundManager.isEnabled());
			return true;
		}
		
		if (buckle.userShooted())
			touches.add(event);
		
		return true;
	}
	
	public void shouted() {
		user.decreaseLive();
		cracksFactory.addCrack();
	}

//-----------------------Main Game Process----------------------------	
	public void doPhysics() {
		
		if (state == STATE_ONSTART) {
    		restartTimer();
    		state = STATE_RUNNING;
		}
		
		if (!user.isAlive()) return;
		
    	if (levelFinished) {
    		levelsFactory.next();
    		levelFinished = false;
    		restartTimer();
    	}
    	
		long now = System.currentTimeMillis();    	
		levelsFactory.getCurrentLevel().setTouchEvents(touches);
  		levelsFactory.getCurrentLevel().doPhysics(now);
  		touches.clear();
  		
    	user.doPhysics(now);
    	buckle.doPhysics(now);
    	timer.doPhysics(now);
    }

	private void restartTimer() {
		//TODO refactore timer restart
		timer.setTimeInSeconds(10);
		timer.start(System.currentTimeMillis());
	}
	
	public void doSound() {
		levelsFactory.getCurrentLevel().doSound(soundManager);
	}
    
    private boolean isPauseButtonZone(int x, int y) {
    	return false;
    }
    
    private boolean isChargerZone(int x, int y) {
    	return BUCKLE_RECT.contains(x, y);
    }
    
	private boolean isSoundZone(int x, int y) {
		return SOUND_RECT.contains(x, y);
	}

	public synchronized void restoreState(Bundle savedState)
	{
//		synchronized (getHolder()) {
			if (savedState != null) {
				if (cowboysFactory == null) 
					cowboysFactory = new CowboysFactory(pictureManager, this);
				/**/
				if (levelsFactory == null)
					levelsFactory = new LevelsFactory(pictureManager, cowboysFactory, this);
				levelsFactory.restoreState(savedState);
				
				if (cracksFactory == null)
					cracksFactory = new CracksFactory(pictureManager, soundManager);
				cracksFactory.restoreState(savedState);
			}
			
			buckle.restoreState(savedState);
			user.restoreState(savedState);
			timer.restoreState(savedState);
//		}		
	}

	public Bundle saveState(Bundle map) {
//		synchronized (getHolder()) {
			levelsFactory.saveState(map);
			cracksFactory.saveState(map);
			user.saveState(map);
			buckle.saveState(map);
			soundButton.saveState(map);
			timer.saveState(map);
//		}
		
		return map;
	}
	
	public int getCurrentState()
	{
		return state;
	}
	
	public void setCurrentState(int state)
	{
		this.state = state;
	}
	
	public void doDraw(Canvas canvas) {
		
		if (user.isAlive()) {
				Level level = levelsFactory.getCurrentLevel();
				if (level != null) level.doDraw(canvas, new Rect(0,0, canvas.getWidth(), canvas.getHeight()),new Point(0,0));
				
				cracksFactory.doDraw(canvas, new Rect(0,0, canvas.getWidth(), canvas.getHeight()),new Point(0,0));
				
				user.doDraw(canvas, USER_RECT, new Point(USER_RECT.left, USER_RECT.top));
				soundButton.doDraw(canvas, SOUND_RECT, new Point(SOUND_RECT.left, SOUND_RECT.top));
				buckle.doDraw(canvas, BUCKLE_RECT, new Point(BUCKLE_RECT.left, BUCKLE_RECT.top));
				timer.doDraw(canvas, TIMER_RECT, new Point(TIMER_RECT.left, TIMER_RECT.top));
		}
		else {
			//LOSE!
		}
		
	}

	@Override
	public void execute() {
		levelFinished = true;
	}

	/**
	 * Main thread for drawing game layout
	 *   
	 * @author kodla
	 */
	/*
	private class DrawThread extends Thread {
		private boolean running = false;
		
		@Override
		public void run() {
			running = true;
	 		
			Log.w("{k:Draw Start}", Long.toString(System.currentTimeMillis()));
			Canvas canvas = holder.lockCanvas();

			synchronized (holder) {
				doDraw(canvas);
			}
			
			holder.unlockCanvasAndPost(canvas);
			Log.w("{k:Draw End}", Long.toString(System.currentTimeMillis()));
			
			running = false;
		}
		
		public boolean isRunning() {
			return running;
		}
		
		private void doDraw(Canvas canvas) {
			Level level = levelsFactory.getCurrentLevel();
			if (level != null) level.doDraw(canvas);
			
			canvas.restore();
		}
	}*/
}