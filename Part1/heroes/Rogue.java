package heroes;

import abilities.Abilities;
import utilities.Constants;

public class Rogue extends Hero {

	public Rogue(final int x, final int y) {
		super(x, y);
		this.setHp(Constants.ROGUE_HP);
		this.setInitHp(Constants.ROGUE_HP);
		this.setLvlUpHpIncrease(Constants.ROGUE_LEVEL_HP);
		this.setRace('R');
	}

	/* rogue e atacat de alt erou */
	public void getsAttackedBy(final Abilities ability) {
		ability.attacks(this);
	}

}
