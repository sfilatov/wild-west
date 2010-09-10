package com.iwildwest.cowboys;

import com.iwildwest.R;
import com.iwildwest.core.PictureManager;

public class Cowboy1 extends AbstractCowboy {

	public Cowboy1(PictureManager pictureManager, long liveTime) {
		super(pictureManager);

		putStateAttributes(new StateAttributes(State.APPEAR_ANIMATION_STATE,
				new long[] { SHORT_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT },
				new Integer[] { null, null,	null, null, null },
				new Integer[] {
						R.drawable.cowboy1_light_animation_death_5,
						R.drawable.cowboy1_light_animation_death_4,
						R.drawable.cowboy1_light_animation_death_3,
						R.drawable.cowboy1_light_animation_death_2,
						R.drawable.cowboy1_light_animation_death_1 }));
		putStateAttributes(new StateAttributes(State.SHOOT_ANIMATION_STATE,
				new long[] { PREPARE_TIMEOUT, SHOOT_TIMEOUT, FADE_TIMEOUT }, 
				new Integer[] { null, R.raw.enemy_shot_pistol, null, null }, new Integer[] {
						R.drawable.cowboy1_light_animation_static,
						R.drawable.cowboy1_light_animation_shot_1,
						R.drawable.cowboy1_light_animation_shot_2 }));
		putStateAttributes(new StateAttributes(State.DEAD_ANIMATION_STATE,
				new long[] { PREPARE_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT },
				new Integer[] { R.raw.enemy_dead1, null, null, null, null },
				new Integer[] { R.drawable.cowboy1_light_animation_death_1,
						R.drawable.cowboy1_light_animation_death_2,
						R.drawable.cowboy1_light_animation_death_3,
						R.drawable.cowboy1_light_animation_death_4,
						R.drawable.cowboy1_light_animation_death_5 }));
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
