package com.iwildwest.core;

import android.graphics.Bitmap;

public interface PictureManager {

	void init();
	
	Bitmap getPicture(int id);
	
	void releasePicture(int id);
}
