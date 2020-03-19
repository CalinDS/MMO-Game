package strategies;

import heroes.Hero;
import utilities.Constants;

public class BasicRogueStrategy extends Strategy {

    public BasicRogueStrategy(final Hero hero) {
        makeStrat(hero, Constants.R_DEF_HP_LIM, Constants.R_OFF_HP_LIM,
                Constants.R_DEF_COEF_MOD, Constants.R_DEF_HP_MOD,
                Constants.R_OFF_COEF_MOD, Constants.R_OFF_HP_MOD);
    }

}
