package com.iwildwest.core;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public abstract class AbstractAnimationThread implements AnimationThread, Runnable {
    private SurfaceHolder holder;

    private boolean running = false;

    private Thread thread;

    public AbstractAnimationThread(SurfaceHolder holder) {
        this.holder = holder;
    }

    @Override
    public void run() {
        while (running) {
            doPhysics();
            doCanvasDraw();
        }
    }


    private void doCanvasDraw() {
        Canvas canvas = holder.lockCanvas();

        synchronized (holder) {
            doDraw(canvas);
            canvas.restore();
        }

        holder.unlockCanvasAndPost(canvas);

    }

    @Override
    public void start() {
        if (running) return;

        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public void pause(){
        running = false;
        thread = null;
    }
}
