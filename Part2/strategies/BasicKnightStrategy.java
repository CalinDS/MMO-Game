package strategies;

import heroes.Hero;
import utilities.Constants;

public class BasicKnightStrategy extends Strategy {

    public BasicKnightStrategy(final Hero hero) {
        makeStrat(hero, Constants.K_DEF_HP_LIM, Constants.K_OFF_HP_LIM,
                Constants.K_DEF_COEF_MOD, Constants.K_DEF_HP_MOD,
                Constants.K_OFF_COEF_MOD, Constants.K_OFF_HP_MOD);
    }

}
