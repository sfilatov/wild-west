package com.iwildwest.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import com.iwildwest.R;

public final class SoundManager {

    private static final String TAG = SoundManager.class.toString();

    private Context context;
    private SoundPool soundPool;
    private Map<Integer, Integer> soundPoolMap;
    private HashMap<Integer, MediaPlayer> mediaPlayerMap;

    private boolean enabled = true;

    public SoundManager(Context context) {
        this.context = context;
        soundPoolMap = loadRawConstants(getSoundPool());
        mediaPlayerMap = new HashMap<Integer, MediaPlayer>();
    }

    private SoundPool getSoundPool(){
        if (soundPool == null) soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 100);
        return soundPool;
    }

    public void playSound(int sound) {
        if (!enabled) return;

        AudioManager mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);

        Integer soundId = soundPoolMap.get(sound);
        if (soundId != null) getSoundPool().play(soundId, streamVolume, streamVolume, 1, 0, 1f);
    }

    public void stopSound(int sound) {
        Integer soundId = soundPoolMap.get(sound);
        if (soundId != null) getSoundPool().pause(soundId);
    }

    public void playLoopSound(int resourceId) {
        stopLoopSound(resourceId);

        MediaPlayer mediaPlayer = MediaPlayer.create(context, resourceId);
        mediaPlayerMap.put(resourceId, mediaPlayer);
        mediaPlayer.setLooping(true);

        if (enabled) mediaPlayer.start();
    }

    public void stopLoopSound(int resourceId) {
        if (mediaPlayerMap.containsKey(resourceId)) {
            MediaPlayer mediaPlayer = mediaPlayerMap.remove(resourceId);
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
    }

    public void on(){
        this.enabled = true;
        for (MediaPlayer player : mediaPlayerMap.values()) {
            player.start();
        }
    }

    public void off(){
        this.enabled = false;
        for (MediaPlayer player : mediaPlayerMap.values()) {
            player.pause();
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    private SortedMap<Integer, Integer> loadRawConstants(SoundPool soundPool) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (Field f : R.raw.class.getFields()) {
            int modifiers = f.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && f.getType().equals(Integer.TYPE)) {
                try {
                    int value = f.getInt(null);
                    map.put(value, soundPool.load(context, value, 1));
                } catch (Exception error) {
                    Log.e(TAG, "Error accessing constants");
                }
            }
        }
        return Collections.unmodifiableSortedMap(map);
    }

}
