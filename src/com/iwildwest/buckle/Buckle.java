package com.iwildwest.buckle;

import android.util.Log;
import com.iwildwest.R;
import com.iwildwest.core.Animated;
import com.iwildwest.core.PictureManager;
import com.iwildwest.core.SoundManager;
import com.iwildwest.core.Storable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;

public final class Buckle implements Animated, Storable {
    private static final String KEY = Storable.KEY + "Buckle";
    private static final String KEY_BULLETS = KEY + "bullets";

    private static final int MAX_BULLETS = 6;

    private static final float START_ANGLE = 0f;
    private static final float ROTATE_ANGLE = 30f;

    private static final float[] START_BULLET_POSITIONS = new float[] { 37,15, 37,31, 23,40, 8,31, 8,15, 23,6};
    private static final float[] ROTATE_BULLET_POSITIONS = new float[] { 31,9, 39,24, 31,38, 14,38, 5,24, 14,9};

    private static final long DELAY_TIME = 50;

    private int bullets;
    private int animateBullets;

    private final Object marker = new Object();

    private final Bitmap startBucklePicture;
    private final Bitmap rotateBucklePicture;

    private final Bitmap bulletPicture;

    private Bitmap currentBucklePicture;
    private float[] currentBulletPositions;

    private float currentAngle;

    private long lastUpdateTime;

    private SoundManager soundManager;

    public Buckle(PictureManager pictureManager, SoundManager soundManager) {
        startBucklePicture = pictureManager.getPicture(R.drawable.buckle);
        rotateBucklePicture = pictureManager.getPicture(R.drawable.buckle_light);

        bulletPicture = pictureManager.getPicture(R.drawable.bullet);

        this.soundManager = soundManager;

        init();
    }

    private void init(){
        bullets = MAX_BULLETS;
        currentAngle = START_ANGLE;
        lastUpdateTime = 0;
        animateBullets = 0;
        currentBucklePicture = startBucklePicture;
        currentBulletPositions = START_BULLET_POSITIONS;
    }

    public void doDraw(Canvas canvas, Rect rect, Point point) {
        canvas.drawBitmap(currentBucklePicture, point.x, point.y, null);

        try {
            for (int i = (6 - animateBullets - 1)*2 ; i >= (6 - bullets - animateBullets) * 2; i -= 2) {
                canvas.drawBitmap(bulletPicture, point.x + currentBulletPositions[i], point.y + currentBulletPositions[i + 1], null);
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            Log.e(Buckle.class.toString(), "Array out of bounds AnimateBullets: " + animateBullets + ", Bullets: " + bullets);
        }

    }

    public void doPhysics(long now) {
        if (currentAngle == START_ANGLE && animateBullets <= 0) return;

        if (lastUpdateTime == 0) lastUpdateTime = now;

        if (now - lastUpdateTime >= DELAY_TIME) {
            if (currentAngle == START_ANGLE) {
                currentAngle = ROTATE_ANGLE;
                currentBucklePicture = rotateBucklePicture;
                currentBulletPositions = ROTATE_BULLET_POSITIONS;
            } else if (currentAngle == ROTATE_ANGLE) {
                currentAngle = START_ANGLE;
                currentBucklePicture = startBucklePicture;
                currentBulletPositions = START_BULLET_POSITIONS;
                synchronized (marker) {
                    animateBullets--;
                }
            }
            lastUpdateTime = now;
        }
    }

    public void reload() {
        if (bullets == MAX_BULLETS) return;
        bullets = MAX_BULLETS;
        synchronized (marker) {
            animateBullets = 0;
        }
        soundManager.playSound(R.raw.player_reload_pistol);
    }

    public boolean shoot() {
        if (bullets > 0) {
            synchronized (marker) {
                animateBullets ++;
            }
            bullets--;
            soundManager.playSound(R.raw.player_shot_pistol);
            return true;
        } else {
            soundManager.playSound(R.raw.menu_sound_in);
            return false;
        }
    }

    public void saveState(Bundle map) {
        map.putInt(KEY_BULLETS, bullets);
    }

    public void restoreState(Bundle savedState) {
        bullets = savedState.getInt(KEY_BULLETS);
    }
}
