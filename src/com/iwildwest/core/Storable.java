package com.iwildwest.core;

import android.os.Bundle;

public interface Storable {
	
	static final String KEY = "WildWest";
	
	void saveState(Bundle map);
	
	void restoreState(Bundle bundle);

}
