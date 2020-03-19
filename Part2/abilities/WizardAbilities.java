package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import utilities.Constants;

/* aceasta clasa are campuri in plus, necesare pentru abilitatea deflect,
 * avand in vedere ca, in felul in care am gandid implementarea, celelalte
 * abilitati se aplica indiferent de damage-ul primit de erou
 */
public class WizardAbilities extends Abilities {

    /* lucrez cu procente pentru aceste abilitati */
    private float damage;
    private float drainPercent = Constants.DRAIN;
    private float deflectPercent = Constants.DEFLECT;
    private float fieldAmplifier = 1f;
    /* atribute folosite pentru a calcula cat damage fara racemodifier
     * primeste Wizard in batalie, pentru a sti cat deflect da
     */
    private boolean buffedKnight = false;
    private boolean buffedRogue = false;
    private boolean buffedPyromancer = false;
    private int round;
    private float coefModifier = 0f;

    public WizardAbilities(final char fieldType, final Hero hero,
            final int round) {
        drainPercent += Constants.DRAIN_LVL_INCREASE
                * hero.getLvl();
        deflectPercent += Constants.DEFLECT_LVL_INCREASE
                * hero.getLvl();
        if (fieldType == 'D') {
            fieldAmplifier += Constants.FIELD_W_AMPLIFIER;
        }
        if (fieldType == 'L') {
            buffedKnight = true;
        }
        if (fieldType == 'W') {
            buffedRogue = true;
        }
        if (fieldType == 'V') {
            buffedPyromancer = true;
        }
        /* cu round o sa aflu daca Rogue ar da crit */
        this.round = round;
        coefModifier = hero.getCoefModifier();
        angelModifier = hero.getAngelModifier();
    }

    /* wizard ataca rogue */
    public void attacks(final Rogue hero) {
        float drainAmp = Constants.ROGUE_DRAIN_AMPLIFIER;
        drainAmp += coefModifier;
        drainAmp += angelModifier;
        drainPercent *= drainAmp;
        int maxHp = Constants.ROGUE_HP + Constants.ROGUE_LEVEL_HP * hero.getLvl();
        float drainDmg = drainPercent
                * Math.min(Constants.DRAIN_COEF * maxHp, hero.getHp());
        drainDmg *= fieldAmplifier;
        float receivedDmg = Constants.BACKSTAB + Constants.BACKSTAB_LVL_INCREASE
                * hero.getLvl() + Constants.PARALYSIS
                + Constants.PARALYSIS_LVL_INCREASE * hero.getLvl();
        /* calculez damage-ul primit daca am fi pe teren de woods
         * si am avea si crit */
        if (buffedRogue && round % Constants.CRIT_PERIOD == 0) {
            receivedDmg = (Constants.CRIT
                    * (Constants.BACKSTAB + Constants.BACKSTAB_LVL_INCREASE
                            * hero.getLvl())) + Constants.PARALYSIS
                    + Constants.PARALYSIS_LVL_INCREASE * hero.getLvl();
            receivedDmg *= (1 + Constants.FIELD_R_AMPLIFIER);
            /* iar aici e doar pe teren de woods dar nu da crit */
        } else if (buffedRogue && round % Constants.CRIT_PERIOD != 0) {
            receivedDmg *= (1 + Constants.FIELD_R_AMPLIFIER);
        }
        /* calculez damage-ul de deflect in functie de damage-ul primit */
        float deflectAmp = Constants.ROGUE_DEFLECT_AMPLIFIER;
        deflectAmp += coefModifier;
        deflectAmp += angelModifier;
        deflectPercent *= deflectAmp;
        float deflectDmg = deflectPercent * receivedDmg;
        deflectDmg *= fieldAmplifier;
        damage = Math.round(drainDmg) + Math.round(deflectDmg);
        int hp = hero.getHp();
        hp -= Math.round(damage);
        hero.setHp(hp);
    }

    /* wizard ataca pyro */
    public void attacks(final Pyromancer hero) {
        float deflectDmg = 0f;
        float drainAmp = Constants.PYRO_DRAIN_AMPLIFIER;
        drainAmp += coefModifier;
        drainAmp += angelModifier;
        drainPercent *= drainAmp;
        int maxHp = Constants.PYROMANCER_HP
                + Constants.PYROMANCER_LEVEL_HP * hero.getLvl();
        float drainDmg = drainPercent
                * Math.min(Constants.DRAIN_COEF * maxHp, hero.getHp());
        drainDmg *= fieldAmplifier;
        float receivedDmg = Constants.FIREBLAST
                + Constants.FIREBLAST_LVL_INCREASE
                * hero.getLvl() + Constants.IGNITE
                + Constants.IGNITE_LVL_INCREASE * hero.getLvl();
        /* similar cu mai sus, dar pentru cazul de buffed
         * trebuie sa schimb ordinea operatiilor pentru a da
         * corect dupa aproximari
         */
        if (buffedPyromancer) {
            receivedDmg *= (1 + Constants.FIELD_P_AMPLIFIER);
            receivedDmg *= (Constants.PYRO_DEFLECT_AMPLIFIER
                    + coefModifier + angelModifier);
            deflectDmg = deflectPercent * Math.round(receivedDmg);
        } else {
            float deflectAmp = Constants.PYRO_DEFLECT_AMPLIFIER;
            deflectAmp += coefModifier;
            deflectAmp += angelModifier;
            deflectPercent *= deflectAmp;
            deflectDmg = deflectPercent * receivedDmg;
        }
        deflectDmg *= fieldAmplifier;
        damage = Math.round(drainDmg) + Math.round(deflectDmg);
        int hp = hero.getHp();
        hp -= Math.round(damage);
        hero.setHp(hp);
    }

    /* wizard ataca wizard */
    public void attacks(final Wizard hero) {
        float drainAmp = Constants.WIZARD_DRAIN_AMPLIFIER;
        drainAmp += coefModifier;
        drainAmp += angelModifier;
        drainPercent *= drainAmp;
        int maxHp = Constants.WIZARD_HP
                + Constants.WIZARD_LEVEL_HP * hero.getLvl();
        float drainDmg = drainPercent
                * Math.min(Constants.DRAIN_COEF * maxHp, hero.getHp());
        drainDmg *= fieldAmplifier;
        /* nu avem deflect in cazul acesta */
        damage = Math.round(drainDmg);
        int hp = hero.getHp();
        hp -= Math.round(damage);
        hero.setHp(hp);
    }

    /* wizard ataca knight */
    public void attacks(final Knight hero) {
        float deflectDmg;
        float drainAmp = Constants.KNIGHT_DRAIN_AMPLIFIER;
        drainAmp += coefModifier;
        drainAmp += angelModifier;
        drainPercent *= drainAmp;
        int maxHp = Constants.KNIGHT_HP
                + Constants.KNIGHT_LEVEL_HP * hero.getLvl();
        float drainDmg = drainPercent
                * Math.min(Constants.DRAIN_COEF * maxHp, hero.getHp());
        drainDmg *= fieldAmplifier;
        float receivedDmg = Constants.EXECUTE
                + Constants.EXECUTE_LVL_INCREASE
                * hero.getLvl() + Constants.SLAM
                + Constants.SLAM_LVL_INCREASE * hero.getLvl();
        if (buffedKnight) {
            receivedDmg *= (1 + Constants.FIELD_K_AMPLIFIER);
            receivedDmg *= (Constants.KNIGHT_DEFLECT_AMPLIFIER
                    + coefModifier + angelModifier);
            deflectDmg = deflectPercent * Math.round(receivedDmg);
        } else {
            float deflectAmp = Constants.KNIGHT_DEFLECT_AMPLIFIER;
            deflectAmp += coefModifier;
            deflectAmp += angelModifier;
            deflectPercent *= deflectAmp;
            deflectDmg = deflectPercent * receivedDmg;
        }
        deflectDmg *= fieldAmplifier;
        damage = Math.round(drainDmg) + Math.round(deflectDmg);
        int hp = hero.getHp();
        hp -= Math.round(damage);
        hero.setHp(hp);
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
    public float getDrainPercent() {
        return drainPercent;
    }

    /* getters and setters */
    public void setDrainPercent(final float drainPercent) {
        this.drainPercent = drainPercent;
    }

    /* getters and setters */
    public float getDeflectPercent() {
        return deflectPercent;
    }

    /* getters and setters */
    public void setDeflectPercent(final float deflectPercent) {
        this.deflectPercent = deflectPercent;
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
    public boolean isBuffedKnight() {
        return buffedKnight;
    }

    /* getters and setters */
    public void setBuffedKnight(final boolean buffedKnight) {
        this.buffedKnight = buffedKnight;
    }

    /* getters and setters */
    public boolean isBuffedRogue() {
        return buffedRogue;
    }

    /* getters and setters */
    public void setBuffedRogue(final boolean buffedRogue) {
        this.buffedRogue = buffedRogue;
    }

    /* getters and setters */
    public boolean isBuffedPyromancer() {
        return buffedPyromancer;
    }

    /* getters and setters */
    public void setBuffedPyromancer(final boolean buffedPyromancer) {
        this.buffedPyromancer = buffedPyromancer;
    }

    /* getters and setters */
    public int getRound() {
        return round;
    }

    /* getters and setters */
    public void setRound(final int round) {
        this.round = round;
    }

}
