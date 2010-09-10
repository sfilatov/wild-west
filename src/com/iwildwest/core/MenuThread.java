package com.iwildwest.core;

import com.iwildwest.R;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class MenuThread extends AbstractAnimationThread {

	// View states
	private static final int PLAY_MENU = 0;
	private static final int SETTINGS_MENU = 1;
	private static final int ABOUT_MENU = 2;

	// Object positions constants
	private static final Point PLAY_POINT = new Point(152, 80);
	private static final Rect PLAY_RECT = new Rect(PLAY_POINT.x, PLAY_POINT.y, PLAY_POINT.x + 177, PLAY_POINT.y + 41);
	private static final Point SETTINGS_POINT = new Point(172, 130);
	private static final Rect SETTINGS_RECT = new Rect(SETTINGS_POINT.x, SETTINGS_POINT.y, SETTINGS_POINT.x + 137, SETTINGS_POINT.y + 41);	
	private static final Point HIGHSCORES_POINT = new Point(145, 180);
	private static final Rect HIGHSCORES_RECT = new Rect(HIGHSCORES_POINT.x, HIGHSCORES_POINT.y, HIGHSCORES_POINT.x + 190, HIGHSCORES_POINT.y + 41);	
	private static final Point ABOUT_POINT = new Point(190, 230);
	private static final Rect ABOUT_RECT = new Rect(ABOUT_POINT.x, ABOUT_POINT.y, ABOUT_POINT.x + 98, ABOUT_POINT.y + 41);	
	private static final Point SOUNDON_POINT = new Point(165, 80);
	private static final Rect SOUNDON_RECT = new Rect(SOUNDON_POINT.x, SOUNDON_POINT.y, SOUNDON_POINT.x + 149, SOUNDON_POINT.y + 39);	
	private static final Point SOUNDOF_POINT = new Point(158, 130);
	private static final Rect SOUNDOF_RECT = new Rect(SOUNDOF_POINT.x, SOUNDOF_POINT.y, SOUNDOF_POINT.x + 165, SOUNDOF_POINT.y + 42);	
	private static final Point BACK_POINT = new Point(210, 230);
	private static final Rect BACK_RECT = new Rect(BACK_POINT.x, BACK_POINT.y, BACK_POINT.x + 59, BACK_POINT.y + 31);	
	private static final Point ABOUTTEXT_POINT = new Point(72, 60);
	private static final Rect ABOUTTEXT_RECT = new Rect(ABOUTTEXT_POINT.x, ABOUTTEXT_POINT.y, ABOUTTEXT_POINT.x + 336, ABOUTTEXT_POINT.y + 236);
	private static final Rect CURSOR_RECT = new Rect(0, 0, 226, 78);

	private int currentMenu = PLAY_MENU;

	private Bitmap background;
	private Bitmap playButton;
	private Bitmap settingsButton;
	private Bitmap highscoresButton;
	private Bitmap aboutButton;
	private Bitmap soundonButton;
	private Bitmap soundoffButton;
	private Bitmap backButton;
	private Bitmap aboutText;
	private Bitmap cursorImage;
	
	private Rect cursorRect = ABOUT_RECT;
	
	private ExtendedContext context;

	public MenuThread(SurfaceHolder holder, ExtendedContext extendedContext) {
		super(holder);

		setTimeout(100);
		
		this.context = extendedContext;

		background = extendedContext.getPictureManager().getPicture(
				R.drawable.startmenu);
		playButton = extendedContext.getPictureManager().getPicture(
				R.drawable.menu_play_game_text);
		settingsButton = extendedContext.getPictureManager().getPicture(
				R.drawable.menu_settings_text);
		highscoresButton = extendedContext.getPictureManager().getPicture(
				R.drawable.high_scores_menu);
		aboutButton = extendedContext.getPictureManager().getPicture(
				R.drawable.menu_about_text);
		soundonButton = extendedContext.getPictureManager().getPicture(
				R.drawable.menu_sound_on);
		soundoffButton = extendedContext.getPictureManager().getPicture(
				R.drawable.menu_sound_off);
		backButton = extendedContext.getPictureManager().getPicture(
				R.drawable.menu_text_back); 
		aboutText = extendedContext.getPictureManager().getPicture(
				R.drawable.menutext_about);
		cursorImage = extendedContext.getPictureManager().getPicture(
				R.drawable.menu_selecter);

	}

	public void doDraw(Canvas canvas) {
		canvas.drawBitmap(background, 0f, 0f, null);
		if (cursorRect != null) {
			canvas.drawBitmap(cursorImage, CURSOR_RECT, cursorRect, null);
		}
		switch (currentMenu) {
		case PLAY_MENU:
			canvas.drawBitmap(playButton, PLAY_POINT.x, PLAY_POINT.y, null);
			canvas.drawBitmap(settingsButton, SETTINGS_POINT.x, SETTINGS_POINT.y, null);
			canvas.drawBitmap(highscoresButton, HIGHSCORES_POINT.x, HIGHSCORES_POINT.y, null);
			canvas.drawBitmap(aboutButton, ABOUT_POINT.x, ABOUT_POINT.y, null);
			break;
		case SETTINGS_MENU:
			canvas.drawBitmap(soundonButton, SOUNDON_POINT.x, SOUNDON_POINT.y, null);
			canvas.drawBitmap(soundoffButton, SOUNDOF_POINT.x, SOUNDOF_POINT.y, null);
			canvas.drawBitmap(backButton, BACK_POINT.x, BACK_POINT.y, null);
			break;
		case ABOUT_MENU:
			canvas.drawBitmap(aboutText, ABOUTTEXT_POINT.x, ABOUTTEXT_POINT.y, null);
			break;
		}

	}

	public void doPhysics() {

	}

	public void doSound() {

	}

	public boolean onTouchEvent(MotionEvent event) {
		
		switch (currentMenu) {
		case PLAY_MENU:
			if (ABOUT_RECT.contains((int)event.getX(), (int)event.getY())) {
				currentMenu = ABOUT_MENU;
				context.getSoundManager().playSound(R.raw.menu_sound_in);
				cursorRect = null;
			} else if (SETTINGS_RECT.contains((int)event.getX(), (int)event.getY())){
				currentMenu = SETTINGS_MENU;
				context.getSoundManager().playSound(R.raw.menu_sound_in);
				if (context.getSoundManager().isEnabled())
					cursorRect = SOUNDON_RECT;
				else
					cursorRect = SOUNDOF_RECT;
			} else if (PLAY_RECT.contains((int)event.getX(), (int)event.getY())) {
				context.startGame();
				context.getSoundManager().playSound(R.raw.menu_sound_in);
				cursorRect = PLAY_RECT;
			} else if (HIGHSCORES_RECT.contains((int)event.getX(), (int)event.getY())) {
				//TODO
				context.getSoundManager().playSound(R.raw.menu_sound_in);
				cursorRect = HIGHSCORES_RECT;
			}
			break;
		case SETTINGS_MENU:
			if (SOUNDON_RECT.contains((int)event.getX(), (int)event.getY())) {
				context.getSoundManager().setEnabled(true);
				context.getSoundManager().playSound(R.raw.menu_sound_in);
				cursorRect = SOUNDON_RECT;
			} else if (SOUNDOF_RECT.contains((int)event.getX(), (int)event.getY())) {
				context.getSoundManager().setEnabled(false);
				context.getSoundManager().playSound(R.raw.menu_sound_in);
				cursorRect = SOUNDOF_RECT;
			} else if (BACK_RECT.contains((int)event.getX(), (int)event.getY())) {
				currentMenu = PLAY_MENU;
				context.getSoundManager().playSound(R.raw.menu_sound_in);
				cursorRect = SETTINGS_RECT;
			} 
			break;
		case ABOUT_MENU:
			if (ABOUTTEXT_RECT.contains((int)event.getX(), (int)event.getY())) {
				currentMenu = PLAY_MENU;
				cursorRect = ABOUT_RECT;
			}
			break;
		}

		return true;
	}

}
