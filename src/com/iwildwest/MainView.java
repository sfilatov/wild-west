package com.iwildwest;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.iwildwest.core.AbstractAnimationView;
import com.iwildwest.core.ExtendedContext;
import com.iwildwest.core.MainThread;

public final class MainView extends AbstractAnimationView<MainThread>
{
	
	public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        // create thread only; it's started in surfaceCreated()
        setThread(new MainThread(getSurfaceHolder(), (ExtendedContext) context));
	}
	

	@Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (!hasWindowFocus) getThread().pause();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
		return getThread().onTouchEvent(event);
    }
    
	public void restoreState(Bundle savedInstanceState) {
		getThread().restoreState(savedInstanceState);
	}

	public void onPause() {
		getThread().pause();
	}

	public void saveState(Bundle outState) {
		getThread().saveState(outState);
	}

}
