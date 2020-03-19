package angels;

import java.io.Writer;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import utilities.Constants;

public class DarkAngel extends Angel {

    public DarkAngel(final String type, final int x,
            final int y) {
        super(type, x, y);
    }

    /* printam ca damageAngel a vizitat un erou */
    public void visitPrint(final Hero hero,
            final Writer out) throws Exception {
        out.write(super.getType() + " hit " + hero.getName()
        + " " + hero.getId() + "\n");
    }

    /* functia generica */
    public void visit(final Hero hero) {
    }

    /* darkAngel viziteaza knight */
    public void visits(final Knight hero) {
        hero.setHp(hero.getHp() - Constants.DARK_ANG_ON_K);
    }

    /* darkAngel viziteaza pyro */
    public void visits(final Pyromancer hero) {
        hero.setHp(hero.getHp() - Constants.DARK_ANG_ON_P);
    }

    /* darkAngel viziteaza rogue */
    public void visits(final Rogue hero) {
        hero.setHp(hero.getHp() - Constants.DARK_ANG_ON_R);

    }

    /* darkAngel viziteaza wizard */
    public void visits(final Wizard hero) {
        hero.setHp(hero.getHp() - Constants.DARK_ANG_ON_W);
    }

}
