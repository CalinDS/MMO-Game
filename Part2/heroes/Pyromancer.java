package heroes;

import abilities.Abilities;
import angels.Angel;
import utilities.Constants;

public class Pyromancer extends Hero {

    public Pyromancer(final int x, final int y) {
        super(x, y);
        this.setHp(Constants.PYROMANCER_HP);
        this.setLvlUpHpIncrease(Constants.PYROMANCER_LEVEL_HP);
        this.setInitHp(Constants.PYROMANCER_HP);
        this.setRace('P');
        this.setName("Pyromancer");
    }

    /* pyro e atacat de alt erou */
    public void getsAttackedBy(final Abilities ability) {
        ability.attacks(this);
    }

    /* pyro accepta vizita ingerului */
    public void accepts(final Angel angel) {
        angel.visits(this);
    }

}
