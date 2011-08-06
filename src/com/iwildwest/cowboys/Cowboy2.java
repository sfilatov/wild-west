package com.iwildwest.cowboys;

import com.iwildwest.R;
import com.iwildwest.core.PictureManager;

public class Cowboy2 extends AbstractCowboy {
	public Cowboy2(PictureManager pictureManager, long liveTime) {
		super(pictureManager);

		putStateAttributes(new StateAttributes(State.APPEAR_ANIMATION_STATE,
				new long[] { SHORT_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT },
				new Integer[] { null, null, null, null },
				new Integer[] {
						R.drawable.cowboy2_animation_death4,
						R.drawable.cowboy2_animation_death3,
						R.drawable.cowboy2_animation_death2,
						R.drawable.cowboy2_animation_death1, }));

		putStateAttributes(new StateAttributes(State.SHOOT_ANIMATION_STATE,
				new long[] { PREPARE_TIMEOUT, SHOOT_TIMEOUT, SHOOT_TIMEOUT, FADE_TIMEOUT },
				new Integer[] { 
						null,
						R.raw.enemy_shot_pistol,
						R.raw.enemy_shot_pistol2,
						null},
				new Integer[] {
						R.drawable.cowboy2_animation_static,
						R.drawable.cowboy2_animation_shot1,
						R.drawable.cowboy2_animation_shot2,
						R.drawable.cowboy2_animation_shot3
				}));
		putStateAttributes(new StateAttributes(State.DEAD_ANIMATION_STATE,
				new long[] { PREPARE_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT, SHORT_TIMEOUT }, 
				new Integer[] { R.raw.enemy_dead2, null, null, null },
				new Integer[] {
						R.drawable.cowboy2_animation_death1,
						R.drawable.cowboy2_animation_death2,
						R.drawable.cowboy2_animation_death3,
						R.drawable.cowboy2_animation_death4
				}));
	}

	@Override
	protected void substateIndexChanged(State state, int substate) {
		super.substateIndexChanged(state, substate);

		if (state == State.SHOOT_ANIMATION_STATE) {
			if (substate == 2 || substate == 3) {
				getListener().shouted();
			}
		}
	}

}
