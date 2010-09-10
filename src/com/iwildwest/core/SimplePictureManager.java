package com.iwildwest.core;

import java.util.HashMap;
import java.util.Map;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public final class SimplePictureManager implements PictureManager {
	
	private static String TAG = "SimplePictureManager";

	private Map<Integer, Bitmap> pictureMap;
	private Resources resources;

	public SimplePictureManager(Resources resources) {
		super();
		this.resources = resources;
		init();
	}

	public void init() {
		pictureMap = new HashMap<Integer, Bitmap>();
//		pictureMap = loadDrawableConstants();
	}

	public Bitmap getPicture(int id) {
		if (pictureMap != null) {
			if (!pictureMap.containsKey(id))
				pictureMap.put(id, BitmapFactory.decodeResource(resources, id));
			return pictureMap.get(id);
		}
		return null;
	}

	public void releasePicture(int id) {
		if (pictureMap != null && pictureMap.containsKey(id)) {
			Bitmap bitmap = pictureMap.remove(id);
			bitmap.recycle();
		}
	}

/*
 * 	private SortedMap<Integer, Bitmap> loadDrawableConstants() {
		SortedMap<Integer, Bitmap> map = new TreeMap<Integer, Bitmap>();


		Field fields[] = R.drawable.class.getFields();
		
		for (Field f : fields) {
		
			int modifiers = f.getModifiers();
			
			if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)
					&& f.getType().equals(Integer.TYPE)) {

				try {
					int value = f.getInt(null); 
					map.put(value, BitmapFactory.decodeResource(resources, value));
				} catch (Exception error) {
					Log.e(TAG, "Error accesing constants");
				}
			}
		}

		return Collections.unmodifiableSortedMap(map);
	}
	*/
}
