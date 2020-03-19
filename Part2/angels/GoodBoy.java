package angels;

import java.io.Writer;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import utilities.Constants;

public class GoodBoy extends Angel {

    public GoodBoy(final String type, final int x, final int y) {
        super(type, x, y);
    }

    /* afisam visit-ul lui goodboy */
    public void visitPrint(final Hero hero,
            final Writer out) throws Exception {
        out.write(super.getType() + " helped " + hero.getName()
        + " " + hero.getId() + "\n");
    }

    /* goodboy viziteaza erou */
    public void visit(final Hero hero) {
    }

    /* goodboy viziteaza knight */
    public void visits(final Knight hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.GOODBOY_COEF_ON_K);
        genericHeal(Constants.GOODBOY_HP_ON_K, hero);
    }

    /* goodboy viziteaza pyro */
    public void visits(final Pyromancer hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.GOODBOY_COEF_ON_P);
        genericHeal(Constants.GOODBOY_HP_ON_P, hero);
    }

    /* goodboy viziteaza rogue */
    public void visits(final Rogue hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.GOODBOY_COEF_ON_R);
        genericHeal(Constants.GOODBOY_HP_ON_R, hero);
    }

    /* goodboy viziteaza wizard */
    public void visits(final Wizard hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.GOODBOY_COEF_ON_W);
        genericHeal(Constants.GOODBOY_HP_ON_W, hero);
    }

}
