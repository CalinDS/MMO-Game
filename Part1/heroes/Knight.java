package heroes;

import abilities.Abilities;
import utilities.Constants;

/* clase pentru fiecare tip de erou */
public class Knight extends Hero {

	public Knight(final int x, final int y) {
		super(x, y);
		this.setHp(Constants.KNIGHT_HP);
		this.setLvlUpHpIncrease(Constants.KNIGHT_LEVEL_HP);
		this.setInitHp(Constants.KNIGHT_HP);
		this.setRace('K');
	}

	/* knight e atacat de alt erou */
	public void getsAttackedBy(final Abilities ability) {
		ability.attacks(this);
	}

}
