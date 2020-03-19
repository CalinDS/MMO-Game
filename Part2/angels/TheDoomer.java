package angels;

import java.io.Writer;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public class TheDoomer extends Angel {

    public TheDoomer(final String type, final int x, final int y) {
        super(type, x, y);
    }

    /* functia care omoara un erou */
    public void dooms(final Hero hero) {
        hero.setHp(0);
    }

    /* visit */
    public void visits(final Hero hero) {
        dooms(hero);
    }

    /* functia de print */
    public void visitPrint(final Hero hero, final Writer out) throws Exception {
        out.write(super.getType() + " hit " + hero.getName()
        + " " + hero.getId() + "\n");
    }

    /* viziteaza fiecare tip */
    public void visits(final Knight hero) {
        dooms(hero);
    }

    /* pyro */
    public void visits(final Pyromancer hero) {
        dooms(hero);
    }

    /* rogue */
    public void visits(final Rogue hero) {
        dooms(hero);
    }

    /* wizard */
    public void visits(final Wizard hero) {
        dooms(hero);
    }

}
