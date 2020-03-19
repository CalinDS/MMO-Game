package strategies;

import heroes.Hero;
import utilities.Constants;


public class BasicWizardStrategy extends Strategy {

    /* crearea strategiei de wizard */
    public BasicWizardStrategy(final Hero hero) {
        makeStrat(hero, Constants.W_DEF_HP_LIM, Constants.W_OFF_HP_LIM,
                Constants.W_DEF_COEF_MOD, Constants.W_DEF_HP_MOD,
                Constants.W_OFF_COEF_MOD, Constants.W_OFF_HP_MOD);
    }
}
