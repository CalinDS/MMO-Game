package angels;

import java.io.Writer;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import utilities.Constants;

public class Dracula extends Angel {

    public Dracula(final String type, final int x,
            final int y) {
        super(type, x, y);
    }

    /* afisam ca Dracula viziteaza */
    public void visitPrint(final Hero hero,
            final Writer out) throws Exception {
        out.write(super.getType() + " hit " + hero.getName()
        + " " + hero.getId() + "\n");
    }

    /* generic */
    public void visit(final Hero hero) {
    }

    /* viziteaza knight */
    public void visits(final Knight hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                - Constants.DRACULA_COEF_ON_K);
        genericDmg(Constants.DRACULA_HP_ON_K, hero);
    }

    /* viziteaza pyro */
    public void visits(final Pyromancer hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                - Constants.DRACULA_COEF_ON_P);
        genericDmg(Constants.DRACULA_HP_ON_P, hero);
    }

    /* viziteaza rogue */
    public void visits(final Rogue hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                - Constants.DRACULA_COEF_ON_R);
        genericDmg(Constants.DRACULA_HP_ON_R, hero);
    }

    /* viziteaza wizard */
    public void visits(final Wizard hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                - Constants.DRACULA_COEF_ON_W);
        genericDmg(Constants.DRACULA_HP_ON_W, hero);
    }

}
