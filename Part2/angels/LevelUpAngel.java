package angels;

import java.io.Writer;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import utilities.Constants;

public class LevelUpAngel extends Angel {

    public LevelUpAngel(final String type, final int x, final int y) {
        super(type, x, y);
    }

    /* generic visit */
    public void visits(final Hero hero) {
    }

    /* afisarea */
    public void visitPrint(final Hero hero,
            final Writer out) throws Exception {
        out.write(super.getType() + " helped " + hero.getName()
        + " " + hero.getId());
        out.write("\n");
    }

    /* functia care calculeaza xp-ul necesar pentru lvl up */
    public void genericLvlUp(final Hero hero) {
        int xp = 0;
        xp = Constants.INITIAL_XP
                + hero.getLvl() * Constants.XP_LEVEL_STEP;
        hero.setXp(xp);
    }

    /* levelupangel viziteaza knight */
    public void visits(final Knight hero) {
        genericLvlUp(hero);
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.L_UP_ANG_ON_K);
    }

    /* levelupangel viziteaza pyro */
    public void visits(final Pyromancer hero) {
        genericLvlUp(hero);
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.L_UP_ANG_ON_P);
    }

    /* levelupangel viziteaza rogue */
    public void visits(final Rogue hero) {
        genericLvlUp(hero);
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.L_UP_ANG_ON_R);
    }

    /* levelupangel viziteaza wizard */
    public void visits(final Wizard hero) {
        genericLvlUp(hero);
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.L_UP_ANG_ON_W);
    }

}
