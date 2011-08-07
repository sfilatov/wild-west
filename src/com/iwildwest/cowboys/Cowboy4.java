package com.iwildwest.cowboys;

import com.iwildwest.R;
import com.iwildwest.core.PictureManager;

public class Cowboy4 extends AbstractCowboy {
	public Cowboy4(PictureManager pictureManager, long liveTime) {
		super(pictureManager);

		putStateAttributes(new StateAttributes(State.APPEAR_ANIMATION_STATE,
				new long[] { SHORT_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT },
				new Integer[] { null, null,	null, null },
				new Integer[] {
						R.drawable.cowboy4_light_animation_death4,
						R.drawable.cowboy4_light_animation_death3,
						R.drawable.cowboy4_light_animation_death2,
						R.drawable.cowboy4_light_animation_death1 }));

		putStateAttributes(new StateAttributes(State.SHOOT_ANIMATION_STATE,
				new long[] { PREPARE_TIMEOUT, FADE_TIMEOUT, FADE_TIMEOUT, FADE_TIMEOUT },
				new Integer[] { null, null, null, R.raw.enemy_shot_pistol3, }, new Integer[] {
						R.drawable.cowboy4_light_animation_static,
						R.drawable.cowboy4_light_animation_throw1,
						R.drawable.cowboy4_light_animation_throw2,
						R.drawable.cowboy4_light_animation_throw3 }));
		putStateAttributes(new StateAttributes(State.DEAD_ANIMATION_STATE,
				new long[] { PREPARE_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT }, 
				new Integer[] { R.raw.enemy_dead2, null, null, null },
				new Integer[] {
						R.drawable.cowboy4_light_animation_death1,
						R.drawable.cowboy4_light_animation_death2,
						R.drawable.cowboy4_light_animation_death3,
						R.drawable.cowboy4_light_animation_death4 }));
	}

	@Override
	protected void substateIndexChanged(State state, int substate) {
		super.substateIndexChanged(state, substate);

		if (state == State.SHOOT_ANIMATION_STATE) {
			if (substate == 3) {
				getListener().shouted();
			}
		}
	}

}
