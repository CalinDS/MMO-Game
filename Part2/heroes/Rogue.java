package heroes;

import abilities.Abilities;
import angels.Angel;
import utilities.Constants;

public class Rogue extends Hero {

    public Rogue(final int x, final int y) {
        super(x, y);
        this.setHp(Constants.ROGUE_HP);
        this.setLvlUpHpIncrease(Constants.ROGUE_LEVEL_HP);
        this.setInitHp(Constants.ROGUE_HP);
        this.setRace('R');
        this.setName("Rogue");
    }

    /* ROGUE e atacat de alt erou */
    public void getsAttackedBy(final Abilities ability) {
        ability.attacks(this);
    }

    /* rogue accepta vizita ingerului */
    public void accepts(final Angel angel) {
        angel.visits(this);
    }

}
