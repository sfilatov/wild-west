package com.iwildwest;

import com.iwildwest.core.ExtendedContext;
import com.iwildwest.core.PictureManager;
import com.iwildwest.core.SimplePictureManager;
import com.iwildwest.core.SoundManager;
import com.iwildwest.core.SoundPoolSoundManager;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;

public class AndWildWest extends Activity implements ExtendedContext, AnimationListener{
	
	private MainView mainView;

	private SimplePictureManager pictureManager;

	private SoundPoolSoundManager soundManager;

	private FrameLayout layout;

	private SplashView splashView;

	private Animation fadeAnimation;

	private MenuView menuView;
	
	public PictureManager getPictureManager() {
		return pictureManager;
	}

	public SoundManager getSoundManager() {
		return soundManager;
	}
	
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		pictureManager = new SimplePictureManager(this.getResources());
		soundManager = new SoundPoolSoundManager(this);
        
        fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade);
        fadeAnimation.setAnimationListener(this);


        mainView = (MainView)View.inflate(this, R.layout.main_view, null);        
        if (savedInstanceState != null) 
        	mainView.restoreState(savedInstanceState);
        
		splashView = (SplashView)View.inflate(this, R.layout.splash_view, null);
        splashView.setAnimation(fadeAnimation);

        menuView = (MenuView)View.inflate(this, R.layout.menu_view, null);
        
        layout = (FrameLayout)View.inflate(this, R.layout.main_layout, null);
        layout.addView(splashView);
        setContentView(layout);
    }
    
    /**
     * Invoked when the Activity loses user focus.
     */
    @Override
    protected void onPause() {
        super.onPause();
        mainView.onPause();
    }
    
    

    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	/**
     * Notification that something is about to happen, to give the Activity a
     * chance to save state.
     * 
     * @param outState a Bundle into which this Activity should save its state
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mainView.saveState(outState);
    }

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mainView.restoreState(savedInstanceState);
	}

	public void onAnimationEnd(Animation animation) {
		if (fadeAnimation.equals(animation)) {
			layout.removeView(splashView);
			layout.addView(menuView);
		}
	}
	public void onAnimationRepeat(Animation arg0) {}

	public void onAnimationStart(Animation arg0) {}

	public void startGame() {
		layout.removeAllViews();
		layout.addView(mainView);
	}

}