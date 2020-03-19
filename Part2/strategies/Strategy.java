package strategies;

import heroes.Hero;

public abstract class Strategy {

    /* cei doi modificatori cu care lucreaza strategiile */
    private float coefModifier = 0f;
    private float hpModifier = 0f;

    /* functie care creeaza strategiile indiferent de tipul eroului */
    public void makeStrat(final Hero hero, final int lowLim, final int highLim,
            final float defCoef, final int defHp, final float offCoef,
            final int offHp) {
        int maxLvlHp = 0;
        maxLvlHp += hero.getInitHp();
        maxLvlHp += hero.getLvl() * hero.getLvlUpHpIncrease();

        if (hero.getTimeBlocked() > 0) {
            setCoefModifier(0f);
            setHpModifier(0f);
        } else {
            if (hero.getHp() < (maxLvlHp / lowLim)) {
                setCoefModifier(-defCoef);
                setHpModifier(hero.getHp() / defHp);
            } else if (hero.getHp() > (maxLvlHp / lowLim) && hero.getHp()
                    < (maxLvlHp / highLim)) {
                setCoefModifier(offCoef);
                setHpModifier(-hero.getHp() / offHp);
            } else {
                setCoefModifier(0f);
                setHpModifier(0f);
            }
        }
    }

    /* getters and setters */
    public float getCoefModifier() {
        return coefModifier;
    }

    /* getters and setters */
    public void setCoefModifier(final float coefModifier) {
        this.coefModifier = coefModifier;
    }

    /* getters and setters */
    public float getHpModifier() {
        return hpModifier;
    }

    /* getters and setters */
    public void setHpModifier(final float hpModifier) {
        this.hpModifier = hpModifier;
    }
}
