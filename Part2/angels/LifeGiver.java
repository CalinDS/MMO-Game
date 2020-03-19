package angels;

import java.io.Writer;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import utilities.Constants;

public class LifeGiver extends Angel {

    public LifeGiver(final String type, final int x, final int y) {
        super(type, x, y);
    }

    /* generic */
    public void visits(final Hero hero) {
    }

    /* afisarea */
    public void visitPrint(final Hero hero,
            final Writer out) throws Exception {
        out.write(super.getType() + " helped " + hero.getName()
        + " " + hero.getId());
        out.write("\n");
    }

    /* viziteaza knight */
    public void visits(final Knight hero) {
        genericHeal(Constants.LIFE_GIVER_ON_K, hero);
    }

    /* viziteaza pyro */
    public void visits(final Pyromancer hero) {
        genericHeal(Constants.LIFE_GIVER_ON_P, hero);
    }

    /* viziteaza rogue */
    public void visits(final Rogue hero) {
        genericHeal(Constants.LIFE_GIVER_ON_R, hero);
    }

    /* viziteaza wizard */
    public void visits(final Wizard hero) {
        genericHeal(Constants.LIFE_GIVER_ON_W, hero);
    }

}
