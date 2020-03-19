package heroes;

import abilities.Abilities;
import utilities.Constants;

public class Pyromancer extends Hero {

	public Pyromancer(final int x, final int y) {
		super(x, y);
		this.setHp(Constants.PYROMANCER_HP);
		this.setLvlUpHpIncrease(Constants.PYROMANCER_LEVEL_HP);
		this.setInitHp(Constants.PYROMANCER_HP);
		this.setRace('P');
	}

	/* pyro e atacat de alt erou */
	public void getsAttackedBy(final Abilities ability) {
		ability.attacks(this);
	}

}
