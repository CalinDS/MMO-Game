package angels;

import java.io.Writer;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import utilities.Constants;

public class SmallAngel extends Angel {

    public SmallAngel(final String type, final int x, final int y) {
        super(type, x, y);
    }

    /* print */
    public void visitPrint(final Hero hero, final Writer out) throws Exception {
        out.write(super.getType() + " helped " + hero.getName()
        + " " + hero.getId() + "\n");
    }

    /* visit */
    public void visit(final Hero hero) {
    }

    /* visit knight */
    public void visits(final Knight hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.SMALL_ANG_COEF_ON_K);
        genericHeal(Constants.SMALL_ANG_HP_ON_K, hero);
    }

    /* visit pyro */
    public void visits(final Pyromancer hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.SMALL_ANG_COEF_ON_P);
        genericHeal(Constants.SMALL_ANG_HP_ON_P, hero);
    }

    /* visit rogue */
    public void visits(final Rogue hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.SMALL_ANG_COEF_ON_R);
        genericHeal(Constants.SMALL_ANG_HP_ON_R, hero);
    }

    /* visit wizard */
    public void visits(final Wizard hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.SMALL_ANG_COEF_ON_W);
        genericHeal(Constants.SMALL_ANG_HP_ON_W, hero);
    }

}
