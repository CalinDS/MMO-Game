package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import utilities.Constants;

/* clasa care defineste abilitatile knight-ului */
public class KnightAbilities extends Abilities {

    private float executeDmg = Constants.EXECUTE;
    private float executeRaceAmplifier = 1f;
    private float hpLimit = Constants.SLAM_HP_LIMIT;
    private float slamDmg = Constants.SLAM;
    private float slamRaceAmplifier = 1f;
    private boolean noMove = true;
    private int noRounds = 1;
    private float fieldAmplifier = 1f;

    /* obtin legatura dintre stats-urile abilitatii si erou */
    public KnightAbilities(final char fieldType, final Hero hero) {
        /* aplic bonusul de teren */
        if (fieldType == 'L') {
            fieldAmplifier += Constants.FIELD_K_AMPLIFIER;
        }
        /* bonusurile de level */
        executeDmg += Constants.EXECUTE_LVL_INCREASE * hero.getLvl();
        if (hero.getLvl() > Constants.SLAM_HP_CAP) {
            hpLimit += Constants.SLAM_HPLIM_INCREASE *  Constants.SLAM_HP_CAP;
        } else {
            hpLimit += Constants.SLAM_HPLIM_INCREASE * hero.getLvl();
        }
        slamDmg += Constants.SLAM_LVL_INCREASE * hero.getLvl();
        coefModifier = hero.getCoefModifier();
        angelModifier = hero.getAngelModifier();
    }

    /* creez atacul indiferent de tipul eroului atacat */
    public void genericAttack(final Hero hero, final float raceExAmp,
            final float raceSlamAmp, final int heroHp,
            final int heroLvlHp) {
        executeRaceAmplifier += raceExAmp;
        if (executeRaceAmplifier != 1f) {
            executeRaceAmplifier += coefModifier;
            executeRaceAmplifier += angelModifier;
        }
        executeDmg = Math.round(executeDmg * fieldAmplifier);
        executeDmg *= executeRaceAmplifier;
        if (hero.getHp() < hpLimit * (heroHp
                + heroLvlHp * hero.getLvl())) {
            hero.setHp(0);
            return;
        }
        slamRaceAmplifier += raceSlamAmp;
        if (slamRaceAmplifier != 1f) {
            slamRaceAmplifier += coefModifier;
            slamRaceAmplifier += angelModifier;
        }
        slamDmg = Math.round(slamDmg * fieldAmplifier);
        slamDmg *= slamRaceAmplifier;
        /* aplic imobilizarea de knight, care anuleaza orice alt efect
         * overtime
         */
        hero.setCantMove(noMove);
        hero.setTimeBlocked(noRounds);
        hero.setDamageOT(0);
        hero.setNoRoundsOT(0);
        /* aplic damage */
        damage = Math.round(executeDmg) + Math.round(slamDmg);
        int hp = hero.getHp();
        hp -= Math.round(damage);
        hero.setHp(hp);
    }

    /* knight ataca rogue */
    public void attacks(final Rogue hero) {
        genericAttack(hero, Constants.ROGUE_EXECUTE_AMPLIFIER,
                Constants.ROGUE_SLAM_AMPLIFIER,
                Constants.ROGUE_HP, Constants.ROGUE_LEVEL_HP);
    }

    /* knight ataca knight */
    public void attacks(final Knight hero) {
        genericAttack(hero, Constants.KNIGHT_EXECUTE_AMPLIFIER,
                Constants.KNIGHT_SLAM_AMPLIFIER,
                Constants.KNIGHT_HP, Constants.KNIGHT_LEVEL_HP);
    }

    /* knight ataca pyro */
    public void attacks(final Pyromancer hero) {
        genericAttack(hero, Constants.PYRO_EXECUTE_AMPLIFIER,
                Constants.PYRO_SLAM_AMPLIFIER,
                Constants.PYROMANCER_HP, Constants.PYROMANCER_LEVEL_HP);
    }

    /* knight ataca wizard */
    public void attacks(final Wizard hero) {
        genericAttack(hero, Constants.WIZARD_EXECUTE_AMPLIFIER,
                Constants.WIZARD_SLAM_AMPLIFIER,
                Constants.WIZARD_HP, Constants.WIZARD_LEVEL_HP);
    }

    /* getters and setters */
    public float getDamage() {
        return damage;
    }

    /* getters and setters */
    public void setDamage(final float damage) {
        this.damage = damage;
    }

    /* getters and setters */
    public float getExecuteDmg() {
        return executeDmg;
    }

    /* getters and setters */
    public void setExecuteDmg(final float executeDmg) {
        this.executeDmg = executeDmg;
    }

    /* getters and setters */
    public float getExecuteRaceAmplifier() {
        return executeRaceAmplifier;
    }

    /* getters and setters */
    public void setExecuteRaceAmplifier(final float executeRaceAmplifier) {
        this.executeRaceAmplifier = executeRaceAmplifier;
    }

    /* getters and setters */
    public float getHpLimit() {
        return hpLimit;
    }

    /* getters and setters */
    public void setHpLimit(final float hpLimit) {
        this.hpLimit = hpLimit;
    }

    /* getters and setters */
    public float getSlamDmg() {
        return slamDmg;
    }

    /* getters and setters */
    public void setSlamDmg(final float slamDmg) {
        this.slamDmg = slamDmg;
    }

    /* getters and setters */
    public float getSlamRaceAmplifier() {
        return slamRaceAmplifier;
    }

    /* getters and setters */
    public void setSlamRaceAmplifier(final float slamRaceAmplifier) {
        this.slamRaceAmplifier = slamRaceAmplifier;
    }

    /* getters and setters */
    public boolean isNoMove() {
        return noMove;
    }

    /* getters and setters */
    public void setNoMove(final boolean noMove) {
        this.noMove = noMove;
    }

    /* getters and setters */
    public int getNoRounds() {
        return noRounds;
    }

    /* getters and setters */
    public void setNoRounds(final int noRounds) {
        this.noRounds = noRounds;
    }

    /* getters and setters */
    public float getFieldAmplifier() {
        return fieldAmplifier;
    }

    /* getters and setters */
    public void setFieldAmplifier(final float fieldAmplifier) {
        this.fieldAmplifier = fieldAmplifier;
    }

}
