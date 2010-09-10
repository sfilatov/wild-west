package com.iwildwest.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.iwildwest.R;

public final class SoundPoolSoundManager implements SoundManager{
	private static final String TAG = "SoundPoolSoundManager";

	private static final int SOUNDPOOL_STREAMS = 10;

	private boolean enabled = true;
	private Context context;
	private SoundPool soundPool;
	private Map<Integer, Integer> soundPoolMap;

	public SoundPoolSoundManager(Context context) {
		this.context = context;
		init();
	}

	public void init() {
		if (enabled) {
			release();
			soundPool = new SoundPool(SOUNDPOOL_STREAMS, AudioManager.STREAM_MUSIC, 100);
			soundPoolMap = loadRawConstants();
//			soundPoolMap.put(R.raw.enemy_dead1, soundPool.load(context, R.raw.enemy_dead1, 1));
//			soundPoolMap.put(R.raw.enemy_dead2, soundPool.load(context, R.raw.enemy_dead2, 1));
//			soundPoolMap.put(R.raw.enemy_dead3, soundPool.load(context, R.raw.enemy_dead3, 1));
//			soundPoolMap.put(R.raw.enemy_dead4, soundPool.load(context, R.raw.enemy_dead4, 1));
//			soundPoolMap.put(R.raw.enemy_dead5, soundPool.load(context, R.raw.enemy_dead5, 1));
//			soundPoolMap.put(R.raw.enemy_shot_pistol, soundPool.load(context, R.raw.enemy_shot_pistol, 1));
//			soundPoolMap.put(R.raw.enemy_shot_pistol2, soundPool.load(context, R.raw.enemy_shot_pistol2, 1));
//			soundPoolMap.put(R.raw.enemy_shot_pistol3, soundPool.load(context, R.raw.enemy_shot_pistol3, 1));
//			soundPoolMap.put(R.raw.menu_sound_in, soundPool.load(context, R.raw.menu_sound_in, 1));
//			soundPoolMap.put(R.raw.player_broke_glass1, soundPool.load(context, R.raw.player_broke_glass1, 1));
//			soundPoolMap.put(R.raw.player_reload_pistol, soundPool.load(context, R.raw.player_reload_pistol, 1));
//			soundPoolMap.put(R.raw.player_shot_pistol, soundPool.load(context, R.raw.player_shot_pistol, 1));
//			soundPoolMap.put(R.raw.youlose, soundPool.load(context, R.raw.youwon, 1));
			
			// TODO add all sounds loading there
		}
	}
	
	public synchronized void release() {
		if (soundPool != null) {
			soundPool.release();
			soundPool = null;
		}
	}

	public synchronized void playSound(int sound) {
		if (soundPool != null) {
			AudioManager mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
			int streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
			Integer soundId = soundPoolMap.get(sound);
			if (soundId != null) {
				soundPool.play(soundId, streamVolume, streamVolume, 1, 0, 1f);
			}
		}
	}
	
	public synchronized void stopSound(int sound) {
		if (soundPool != null) {
			Integer soundId = soundPoolMap.get(sound);
			if (soundId != null) {
				soundPool.pause(soundId);
			}
		}
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	private SortedMap<Integer, Integer> loadRawConstants() {
		SortedMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

		Field fields[] = R.raw.class.getFields();
		
		for (Field f : fields) {
		
			int modifiers = f.getModifiers();
			
			if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)
					&& f.getType().equals(Integer.TYPE)) {

				try {
					int value = f.getInt(null); 
					map.put(value, soundPool.load(context, value, 1));
				} catch (Exception error) {
					Log.e(TAG, "Error accesing constants");
				}
			}
		}

		return Collections.unmodifiableSortedMap(map);
	}

	public boolean isEnabled() {
		return enabled;
	}

}
