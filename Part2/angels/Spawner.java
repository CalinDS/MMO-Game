package angels;

import java.io.Writer;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import utilities.Constants;

public class Spawner extends Angel {

    public Spawner(final String type, final int x, final int y) {
        super(type, x, y);
    }

    /* printam */
    public void visitPrint(final Hero hero, final Writer out) throws Exception {
        out.write(super.getType() + " helped " + hero.getName()
        + " " + hero.getId() + "\n");
    }

    /* vizitam */
    public void visit(final Hero hero) {
    }

    /* vizitam knight */
    public void visits(final Knight hero) {
        hero.setHp(Constants.SPAWNER_ON_K);
    }

    /* vizitam pyro */
    public void visits(final Pyromancer hero) {
        hero.setHp(Constants.SPAWNER_ON_P);
    }

    /* vizitam rogue */
    public void visits(final Rogue hero) {
        hero.setHp(Constants.SPAWNER_ON_R);
    }

    /* vizitam wizard */
    public void visits(final Wizard hero) {
        hero.setHp(Constants.SPAWNER_ON_W);
    }

}
