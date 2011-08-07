package com.iwildwest;

import com.iwildwest.core.AbstractAnimationView;
import com.iwildwest.core.Game;
import com.iwildwest.core.SplashThread;

import android.content.Context;
import android.util.AttributeSet;

public class SplashView extends AbstractAnimationView<SplashThread>{

	public SplashView(Context context, AttributeSet attrs) {
		super(context, attrs);

        setThread(new SplashThread(getSurfaceHolder(), (Game) context));
	}
}
