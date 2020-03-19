package heroes;

import abilities.Abilities;
import utilities.Constants;

public class Wizard extends Hero {

	public Wizard(final int x, final int y) {
		super(x, y);
		this.setHp(Constants.WIZARD_HP);
		this.setInitHp(Constants.WIZARD_HP);
		this.setLvlUpHpIncrease(Constants.WIZARD_LEVEL_HP);
		this.setRace('W');
	}

	/* wizard e atacat de alt erou */
	public void getsAttackedBy(final Abilities ability) {
		ability.attacks(this);
	}

}
