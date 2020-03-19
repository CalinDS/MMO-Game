package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import utilities.Constants;

public class PyromancerAbilities extends Abilities {

    private float fireblastDmg = Constants.FIREBLAST;
    private float igniteDmg = Constants.IGNITE;
    private float igniteOT = Constants.IGNITE_OT;
    private int roundsOT = Constants.ROUNDS_IGNITE;
    /* initial nu am aplificari */
    private float fieldAmplifier = 1f;
    private float raceAmplifier = 1f;

    public PyromancerAbilities(final char fieldType, final Hero hero) {
        /* asemanator cu knight, aplic bonusuri de level si teren */
        if (fieldType == 'V') {
            fieldAmplifier += Constants.FIELD_P_AMPLIFIER;
        }
        fireblastDmg += Constants.FIREBLAST_LVL_INCREASE * hero.getLvl();
        igniteDmg += Constants.IGNITE_LVL_INCREASE * hero.getLvl();
        igniteOT += Constants.IGNITEOT_LVL_INCREASE * hero.getLvl();
        coefModifier = hero.getCoefModifier();
        angelModifier = hero.getAngelModifier();
    }

    /* creez atacul indiferent de tipul eroului atacat */
    public void genericAttack(final Hero hero, final float amp) {
        raceAmplifier += amp;
        fireblastDmg  *= fieldAmplifier;
        fireblastDmg = Math.round(fireblastDmg);
        raceAmplifier += coefModifier;
        raceAmplifier += angelModifier;
        fireblastDmg *= raceAmplifier;
        igniteDmg *= fieldAmplifier;
        igniteDmg = Math.round(igniteDmg);
        igniteDmg *= raceAmplifier;
        igniteOT *= fieldAmplifier;
        igniteOT = Math.round(igniteOT);
        igniteOT *= raceAmplifier;
        damage = Math.round(fireblastDmg) + Math.round(igniteDmg);
        /* calculez damage-ul final si scad din hp-ul adversarului */
        int hp = hero.getHp();
        hp -= Math.round(damage);
        hero.setHp(hp);
        /* ignite elimina alte efecte overtime */
        hero.setDamageOT(Math.round(igniteOT));
        hero.setNoRoundsOT(roundsOT);
        hero.setTimeBlocked(0);
        hero.setCantMove(false);
    }

    /* pyro attacks rogue */
    public void attacks(final Rogue hero) {
        /* am acelasi race-amplifier la ambele abilitati */
        genericAttack(hero, Constants.ROGUE_FIRE_AMPLIFIER);
    }

    /* pyro attacks wizard */
    public void attacks(final Wizard hero) {
        genericAttack(hero, Constants.WIZARD_FIRE_AMPLIFIER);
    }

    /* pyro attacks pyro */
    public void attacks(final Pyromancer hero) {
        genericAttack(hero, Constants.PYRO_FIRE_AMPLIFIER);
    }

    /* pyro attacks knight */
    public void attacks(final Knight hero) {
        genericAttack(hero, Constants.KNIGHT_FIRE_AMPLIFIER);
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
    public float getFireblastDmg() {
        return fireblastDmg;
    }

    /* getters and setters */
    public void setFireblastDmg(final float fireblastDmg) {
        this.fireblastDmg = fireblastDmg;
    }

    /* getters and setters */
    public float getIgniteDmg() {
        return igniteDmg;
    }

    /* getters and setters */
    public void setIgniteDmg(final float igniteDmg) {
        this.igniteDmg = igniteDmg;
    }

    /* getters and setters */
    public float getIgniteOT() {
        return igniteOT;
    }

    /* getters and setters */
    public void setIgniteOT(final float igniteOT) {
        this.igniteOT = igniteOT;
    }

    /* getters and setters */
    public int getRoundsOT() {
        return roundsOT;
    }

    /* getters and setters */
    public void setRoundsOT(final int roundsOT) {
        this.roundsOT = roundsOT;
    }

    /* getters and setters */
    public float getFieldAmplifier() {
        return fieldAmplifier;
    }

    /* getters and setters */
    public void setFieldAmplifier(final float fieldAmplifier) {
        this.fieldAmplifier = fieldAmplifier;
    }

    /* getters and setters */
    public float getRaceAmplifier() {
        return raceAmplifier;
    }

    /* getters and setters */
    public void setRaceAmplifier(final float raceAmplifier) {
        this.raceAmplifier = raceAmplifier;
    }

}
