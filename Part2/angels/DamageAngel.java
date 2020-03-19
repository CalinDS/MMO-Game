package angels;

import java.io.Writer;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import utilities.Constants;

public class DamageAngel extends Angel {

    public DamageAngel(final String type, final int x,
            final int y) {
        super(type, x, y);
    }

    /* afiseaza mesajul specific */
    public void visitPrint(final Hero hero,
            final Writer out) throws Exception {
        out.write(super.getType() + " helped " + hero.getName()
        + " " + hero.getId() + "\n");
    }

    /* viziteaza erou generic */
    public void visit(final Hero hero) {
    }

    /* viziteaza knight, ii creste damage-ul cu cat scrie in enunt */
    public void visits(final Knight hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.DMG_ANG_ON_K);
    }

    /* la fel pentru pyro */
    public void visits(final Pyromancer hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.DMG_ANG_ON_P);
    }

    /* si pentru rogue */
    public void visits(final Rogue hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.DMG_ANG_ON_R);
    }

    /* si pentru wizard */
    public void visits(final Wizard hero) {
        hero.setAngelModifier(hero.getAngelModifier()
                + Constants.DMG_ANG_ON_W);
    }

}
