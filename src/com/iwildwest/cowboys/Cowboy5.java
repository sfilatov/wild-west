package com.iwildwest.cowboys;

import com.iwildwest.R;
import com.iwildwest.core.PictureManager;

public class Cowboy5 extends AbstractCowboy {
	public Cowboy5(PictureManager pictureManager, long liveTime) {
		super(pictureManager);
		putStateAttributes(new StateAttributes(State.APPEAR_ANIMATION_STATE,
				new long[] { SHORT_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT },
				new Integer[] { null, null,
						null, null }, new Integer[] {
						R.drawable.cowboy5_light_animation_death4,
						R.drawable.cowboy5_light_animation_death3,
						R.drawable.cowboy5_light_animation_death2,
						R.drawable.cowboy5_light_animation_death1 }));

		putStateAttributes(new StateAttributes(State.SHOOT_ANIMATION_STATE,
				new long[] { PREPARE_TIMEOUT, SHOOT_TIMEOUT, FADE_TIMEOUT },
				new Integer[] { null, R.raw.enemy_shot_pistol3, null },
				new Integer[] {
						R.drawable.cowboy5_light_animation_static,
						R.drawable.cowboy5_light_animation_shot1,
						R.drawable.cowboy5_light_animation_shot2 }));
		putStateAttributes(new StateAttributes(State.DEAD_ANIMATION_STATE,
				new long[] { PREPARE_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT }, 
				new Integer[] { R.raw.enemy_dead2, null, null, null }, new Integer[] {
						R.drawable.cowboy5_light_animation_death1,
						R.drawable.cowboy5_light_animation_death2,
						R.drawable.cowboy5_light_animation_death3,
						R.drawable.cowboy5_light_animation_death4 }));
	}

	@Override
	protected void substateIndexChanged(State state, int substate) {
		super.substateIndexChanged(state, substate);

		if (state == State.SHOOT_ANIMATION_STATE) {
			if (substate == 2) {
				getListener().shouted();
			}
		}
	}

}
