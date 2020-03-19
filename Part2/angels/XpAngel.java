package angels;

import java.io.Writer;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import utilities.Constants;

public class XpAngel extends Angel {

    public XpAngel(final String type, final int x, final int y) {
        super(type, x, y);
    }

    /* printarea */
    public void visitPrint(final Hero hero, final Writer out) throws Exception {
        out.write(super.getType() + " helped " + hero.getName()
        + " " + hero.getId() + "\n");
    }

    /* vizitare erou */
    public void visit(final Hero hero) {
    }

    /* vizitare knight */
    public void visits(final Knight hero) {
        hero.setXp(hero.getXp()
                + Constants.XP_ANG_ON_K);
    }

    /* vizitare pyro */
    public void visits(final Pyromancer hero) {
        hero.setXp(hero.getXp()
                + Constants.XP_ANG_ON_P);
    }

    /* vizitare rogue */
    public void visits(final Rogue hero) {
        hero.setXp(hero.getXp()
                + Constants.XP_ANG_ON_R);
    }

    /* vizitare wizard */
    public void visits(final Wizard hero) {
        hero.setXp(hero.getXp()
                + Constants.XP_ANG_ON_W);
    }

}
