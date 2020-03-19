package heroes;

import java.io.IOException;
import java.io.Writer;

import abilities.Abilities;
import abilities.KnightAbilities;
import abilities.PyromancerAbilities;
import abilities.RogueAbilities;
import abilities.WizardAbilities;
import angels.Angel;
import magician.GreatMagician;
import map.Map;
import strategies.BasicKnightStrategy;
import strategies.BasicPyroStrategy;
import strategies.BasicRogueStrategy;
import strategies.BasicWizardStrategy;
import strategies.Strategy;
import utilities.Constants;

/* clasa care defienste eroii */
public abstract class Hero {

    private int id;
    private int hp;
    private int xp;
    private int x;
    private int y;
    private int lvl;
    private int lvlUpHpIncrease;
    private int initHp;
    private char race;
    /* atributele pentru efectele overtime */
    private float damageOT;
    private boolean cantMove;
    private int noRoundsOT;
    private int timeBlocked;
    private String name;
    private float angelModifier;
    private float coefModifier;

    public Hero() {
    }

    public Hero(final int x, final int y) {
        this.xp = 0;
        this.x = x;
        this.y = y;
        this.lvl = 0;
    }

    /* functia care muta un erou */
    public void move(final char instruction) {
        /* eroul blocat nu se poate muta, dar durata de imobilizare
         * scade
         */
        if (timeBlocked > 0) {
            timeBlocked--;
            return;
        }
        if (instruction == '_') {
            return;
        } else if (instruction == 'U') {
            x--;
            return;
        } else if (instruction == 'D') {
            x++;
            return;
        } else if (instruction == 'L') {
            y--;
            return;
        } else if (instruction == 'R') {
            y++;
            return;
        }
    }

    /* aplica damage-ul overtime */
    public void processHp() {
        if (noRoundsOT != 0) {
            hp -= damageOT;
            noRoundsOT--;
        }
    }

    /* functia care modifica hp-ul si coeficientii in functie
     * de strategia aleasa. Strategia se bazeaza pe hp-ul eroului
     * curent, pe care il primeste ca parametru in constructor
     */
    public void applyStrat() {
        Strategy strat;
        if (this instanceof Pyromancer) {
            strat = new BasicPyroStrategy(this);
            this.hp += strat.getHpModifier();
            this.coefModifier += strat.getCoefModifier();
        } else if (this instanceof Knight) {
            strat = new BasicKnightStrategy(this);
            this.hp += strat.getHpModifier();
            this.coefModifier += strat.getCoefModifier();
        } else if (this instanceof Rogue) {
            strat = new BasicRogueStrategy(this);
            this.hp += strat.getHpModifier();
            this.coefModifier += strat.getCoefModifier();
        } else if (this instanceof Wizard) {
            strat = new BasicWizardStrategy(this);
            this.hp += strat.getHpModifier();
            this.coefModifier += strat.getCoefModifier();
        }
    }

    /* functia parinte pentru a fi atacat */
    public void getsAttackedBy(final Abilities ability) {
        ability.attacks(this);
    }

    /* functia parinte pentru a accepta actiunile ingerilor */
    public void accepts(final Angel angel) {
        angel.visits(this);
    }

    /* crearea abilitatii cu care va ataca, functie necesare pentru a
     * ne asigura ca atacurile se petrect in acelasi timp
     */
    public Abilities createAbilities(final Map map, final int round) {
        Abilities ability = null;
        if (this instanceof Rogue) {
            ability = new RogueAbilities(map.getCell(this.x, this.y),
                    this, round);
        }
        if (this instanceof Wizard) {
            ability = new WizardAbilities(
                    map.getCell(this.x, this.y),
                    this, round);
        }
        if (this instanceof Pyromancer) {
            ability = new PyromancerAbilities(
                    map.getCell(this.x, this.y), this);
        }
        if (this instanceof Knight) {
            ability = new KnightAbilities(
                    map.getCell(this.x, this.y), this);
        }
        return ability;
    }

    /* functia de lupta */
    public static void fight(final Hero hero1,
            final Hero hero2, final Map map, final int round) {
        Abilities ability1 = hero1.createAbilities(map, round);
        Abilities ability2 = hero2.createAbilities(map, round);
        hero1.getsAttackedBy(ability2);
        hero2.getsAttackedBy(ability1);

    }

    /* daca unul din eroi castiga o lupta, el primeste xp dupa formula
     * din enunt, totodata magicianul va fi notificat. Puteam alege sa
     * notific magicianul tot in clasa Game, Game fiind observable si
     * GreatMagician fiind observator, dar am ales, pentru varietate si
     * flexbilitate si varietate, sa fac si GreatMagician observator pentru
     * clasa Hero, mai ales ca functia aceasta e statica si magicianul e singleton */
    public static void winner(final Hero hero1, final Hero hero2,
            final GreatMagician magician, final Writer out) throws IOException  {
        if (hero1.hp <= 0 && hero2.hp > 0) {
            hero2.xp += Math.max(0, Constants.XP_GAINER
                    - (hero2.lvl - hero1.lvl) * Constants.LVL_DIFF_XP);
            out.write("Player " + hero1.getName() + " " + hero1.getId()
            + " was killed by " + hero2.getName() + " " + hero2.getId() + "\n");
        } else if (hero1.hp > 0 && hero2.hp <= 0) {
            hero1.xp += Math.max(0, Constants.XP_GAINER
                    - (hero1.lvl - hero2.lvl) * Constants.LVL_DIFF_XP);
            out.write("Player " + hero2.getName() + " " + hero2.getId()
            + " was killed by " + hero1.getName() + " " + hero1.getId() + "\n");
        } else if (hero1.hp <= 0 && hero2.hp <= 0) {
            out.write("Player " + hero2.getName() + " " + hero2.getId()
            + " was killed by " + hero1.getName() + " " + hero1.getId() + "\n");
            out.write("Player " + hero1.getName() + " " + hero1.getId()
            + " was killed by " + hero2.getName() + " " + hero2.getId() + "\n");
        }
        magician.getNotifiedByKill(hero1, hero2);
    }

    /* in functie de cat xp are, eroul poate face level-up si sa isi
     * refaca intreg hp-ul */
    public void increaseLvlAndRestoreHp(final GreatMagician magician,
            final Writer out) throws Exception {
        int lvlUp = (this.xp - Constants.INITIAL_XP) / Constants.XP_LEVEL_STEP;
        int oldLvl = this.lvl;
        /* conditii suplimentare pentru a ma asigura ca un erou nu isi reface
         * hp-ul de la o valoare negativa, sau daca doar si-a luat un damage
         * overtime (mai ales pentru cazul in care xp-ul jucatorului este exact
         * la limita dintre doua nivele, de exemplu 250)
         */
        if (lvlUp >= 0 && this.xp - Constants.INITIAL_XP >= 0
                && lvlUp + 1 != this.lvl) {
            this.lvl = lvlUp + 1;
            if (this.hp <= this.initHp + lvlUp * lvlUpHpIncrease
                    && this.hp > 0) {
                this.hp = this.initHp + this.lvl * lvlUpHpIncrease;
            }
        }
        if (this.lvl != oldLvl) {
            for (int i = oldLvl + 1; i <= this.lvl; i++) {
                magician.getNotifiedByLvlUp(this, out, i);
            }
        }
    }

    /* afisare */
    @Override
    public String toString() {
        if (hp <= 0) {
            return (race) + " dead";
        }
        return (race) + " " + (lvl) + " " + (xp) + " " + (hp) + " " + (x) + " " + (y);
    }

    /* getters and setters */
    public int getHp() {
        return hp;
    }

    /* getters and setters */
    public void setHp(final int hp) {
        this.hp = hp;
    }

    /* getters and setters */
    public int getXp() {
        return xp;
    }

    /* getters and setters */
    public void setXp(final int xp) {
        this.xp = xp;
    }

    /* getters and setters */
    public int getX() {
        return x;
    }

    /* getters and setters */
    public void setX(final int x) {
        this.x = x;
    }

    /* getters and setters */
    public int getY() {
        return y;
    }

    /* getters and setters */
    public void setY(final int y) {
        this.y = y;
    }

    /* getters and setters */
    public int getLvl() {
        return lvl;
    }

    /* getters and setters */
    public void setLvl(final int lvl) {
        this.lvl = lvl;
    }

    /* getters and setters */
    public char getRace() {
        return race;
    }

    /* getters and setters */
    public void setRace(final char race) {
        this.race = race;
    }

    /* getters and setters */
    public float getDamageOT() {
        return damageOT;
    }

    /* getters and setters */
    public void setDamageOT(final float damageOT) {
        this.damageOT = damageOT;
    }

    /* getters and setters */
    public boolean isCantMove() {
        return cantMove;
    }

    /* getters and setters */
    public void setCantMove(final boolean cantMove) {
        this.cantMove = cantMove;
    }

    /* getters and setters */
    public int getNoRoundsOT() {
        return noRoundsOT;
    }

    /* getters and setters */
    public void setNoRoundsOT(final int noRoundsOT) {
        this.noRoundsOT = noRoundsOT;
    }

    /* getters and setters */
    public int getTimeBlocked() {
        return timeBlocked;
    }

    /* getters and setters */
    public void setTimeBlocked(final int timeBlocked) {
        this.timeBlocked = timeBlocked;
    }

    /* getters and setters */
    public int getLvlUpHpIncrease() {
        return lvlUpHpIncrease;
    }

    /* getters and setters */
    public void setLvlUpHpIncrease(final int lvlUpHpIncrease) {
        this.lvlUpHpIncrease = lvlUpHpIncrease;
    }

    /* getters and setters */
    public int getInitHp() {
        return initHp;
    }

    /* getters and setters */
    public void setInitHp(final int initHp) {
        this.initHp = initHp;
    }

    /* getters and setters */
    public int getId() {
        return id;
    }

    /* getters and setters */
    public void setId(final int id) {
        this.id = id;
    }

    /* getters and setters */
    public String getName() {
        return name;
    }

    /* getters and setters */
    public void setName(final String name) {
        this.name = name;
    }

    /* getters and setters */
    public float getAngelModifier() {
        return angelModifier;
    }

    /* getters and setters */
    public void setAngelModifier(final float angelModifier) {
        this.angelModifier = angelModifier;
    }

    /* getters and setters */
    public float getCoefModifier() {
        return coefModifier;
    }

    /* getters and setters */
    public void setCoefModifier(final float coefModifier) {
        this.coefModifier = coefModifier;
    }

}
