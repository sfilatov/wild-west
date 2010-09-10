package com.iwildwest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.iwildwest.core.AbstractAnimationView;
import com.iwildwest.core.ExtendedContext;
import com.iwildwest.core.MenuThread;

public class MenuView extends AbstractAnimationView<MenuThread> {

	public MenuView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setThread(new MenuThread(getSurfaceHolder(), (ExtendedContext) context));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN)
			return getThread().onTouchEvent(event);
		else
			return false;
	}

}
