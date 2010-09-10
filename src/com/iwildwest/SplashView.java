package com.iwildwest;

import com.iwildwest.core.AbstractAnimationView;
import com.iwildwest.core.ExtendedContext;
import com.iwildwest.core.SplashThread;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ViewAnimator;

public class SplashView extends AbstractAnimationView<SplashThread>{

	public SplashView(Context context, AttributeSet attrs) {
		super(context, attrs);

        setThread(new SplashThread(getSurfaceHolder(), (ExtendedContext) context));
	}
}
