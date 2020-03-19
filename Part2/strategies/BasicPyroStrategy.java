package strategies;

import heroes.Hero;
import utilities.Constants;

public class BasicPyroStrategy extends Strategy {

    public BasicPyroStrategy(final Hero hero) {
        makeStrat(hero, Constants.P_DEF_HP_LIM, Constants.P_OFF_HP_LIM,
                Constants.P_DEF_COEF_MOD, Constants.P_DEF_HP_MOD,
                Constants.P_OFF_COEF_MOD, Constants.P_OFF_HP_MOD);
    }

}
