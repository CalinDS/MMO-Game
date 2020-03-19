package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import utilities.Constants;

public class RogueAbilities extends Abilities {

    private float backstabDmg = Constants.BACKSTAB;
    private float backstabRaceAmplifier;
    private float paralysisDmg = Constants.PARALYSIS;
    private float paralysisRaceAmplifier;
    private float fieldAmplifier = 1f;
    private float overtimeDmg;
    private int overtimeDuration = Constants.PARALYISIS_DURATION;

    /* trebuie sa dau in constructor si numarul rundei, pentru a sti daca sunt in cazul
     * in care backstab ar da crit
     */
    public RogueAbilities(final char fieldType, final Hero hero,
            final int noRound) {
        backstabDmg += Constants.BACKSTAB_LVL_INCREASE * hero.getLvl();
        paralysisDmg += Constants.PARALYSIS_LVL_INCREASE * hero.getLvl();
        if (fieldType == 'W') {
            fieldAmplifier += Constants.FIELD_R_AMPLIFIER;
            overtimeDuration += Constants.PARALYISIS_DURATION;
            if (noRound % Constants.CRIT_PERIOD == 0) {
                backstabDmg *= Constants.CRIT;
            }
        }
        coefModifier = hero.getCoefModifier();
        angelModifier = hero.getAngelModifier();
    }

    /* creez atacul indiferent de tipul eroului atacat */
    public void genericAttack(final Hero hero, final float backAmp,
            final float paraAmp) {
        /* adaug race-amplifier pentru fiecare abilitate */
        backstabRaceAmplifier = backAmp;
        /* adaug modificatorii de coeficienti in functie de strategie
         * si de ce inger viziteaza eroul
         */
        backstabRaceAmplifier += coefModifier;
        backstabRaceAmplifier += angelModifier;
        backstabDmg = Math.round(backstabDmg * fieldAmplifier);
        backstabDmg *= backstabRaceAmplifier;
        paralysisRaceAmplifier = paraAmp;
        paralysisRaceAmplifier += coefModifier;
        paralysisRaceAmplifier += angelModifier;
        paralysisDmg = Math.round(paralysisDmg * fieldAmplifier);
        paralysisDmg *= paralysisRaceAmplifier;
        damage = Math.round(backstabDmg) + Math.round(paralysisDmg);
        int hp = hero.getHp();
        hp -= Math.round(damage);
        hero.setHp(hp);
        /* paralysis da damage si imobilizeaza pentru 3 sau 6 runde;
         * celelalte efecte overtime sunt "suprascrise"
         */
        hero.setDamageOT(paralysisDmg);
        hero.setNoRoundsOT(overtimeDuration);
        hero.setCantMove(true);
        hero.setTimeBlocked(overtimeDuration);
    }

    /* rogue ataca wizard */
    public void attacks(final Wizard hero) {
        /* adaug race-amplifier pentru fiecare abilitate */
        genericAttack(hero, Constants.WIZARD_BACKSTAB_AMPLIFIER,
                Constants.WIZARD_PARALYSIS_AMPLIFIER);
    }

    /* rogue ataca knight */
    public void attacks(final Knight hero) {
        genericAttack(hero, Constants.KNIGHT_BACKSTAB_AMPLIFIER,
                Constants.KNIGHT_PARALYSIS_AMPLIFIER);
    }

    /* rogue ataca pyro */
    public void attacks(final Pyromancer hero) {
        genericAttack(hero, Constants.PYRO_BACKSTAB_AMPLIFIER,
                Constants.PYRO_PARALYSIS_AMPLIFIER);
    }

    /* rogue ataca rogue */
    public void attacks(final Rogue hero) {
        genericAttack(hero, Constants.ROGUE_BACKSTAB_AMPLIFIER,
                Constants.ROGUE_PARALYSIS_AMPLIFIER);
    }

    /* getters and setters */
    public float getBackstabDmg() {
        return backstabDmg;
    }

    /* getters and setters */
    public void setBackstabDmg(final float backstabDmg) {
        this.backstabDmg = backstabDmg;
    }

    /* getters and setters */
    public float getBackstabRaceAmplifier() {
        return backstabRaceAmplifier;
    }

    /* getters and setters */
    public void setBackstabRaceAmplifier(final float backstabRaceAmplifier) {
        this.backstabRaceAmplifier = backstabRaceAmplifier;
    }

    /* getters and setters */
    public float getParalysisDmg() {
        return paralysisDmg;
    }

    /* getters and setters */
    public void setParalysisDmg(final float paralysisDmg) {
        this.paralysisDmg = paralysisDmg;
    }

    /* getters and setters */
    public float getParalysisRaceAmplifier() {
        return paralysisRaceAmplifier;
    }

    /* getters and setters */
    public void setParalysisRaceAmplifier(final float paralysisRaceAmplifier) {
        this.paralysisRaceAmplifier = paralysisRaceAmplifier;
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
    public float getOvertimeDmg() {
        return overtimeDmg;
    }

    /* getters and setters */
    public void setOvertimeDmg(final float overtimeDmg) {
        this.overtimeDmg = overtimeDmg;
    }

    /* getters and setters */
    public int getOvertimeDuration() {
        return overtimeDuration;
    }

    /* getters and setters */
    public void setOvertimeDuration(final int overtimeDuration) {
        this.overtimeDuration = overtimeDuration;
    }

}
