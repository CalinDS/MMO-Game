package heroes;

import abilities.Abilities;
import abilities.KnightAbilities;
import abilities.PyromancerAbilities;
import abilities.RogueAbilities;
import abilities.WizardAbilities;
import map.Map;
import utilities.Constants;

/* clasa care defienste eroii */
public class Hero {

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

	/* functia parinte pentru a fi atacat */
	public void getsAttackedBy(final Abilities ability) {
		ability.attacks(this);
	}

	/* functia de lupta, ea prinde doar cazul de hero1 ataca hero2 */
	public static void fight(final Hero hero1,
			final Hero hero2, final Map map, final int round) {
		Abilities ability;
		/* daca hero1 este de un anumit tip, se creeaza abilitatile
		 * tipului respectiv si hero2 e atacat cu acele abilitati
		 */
		if (hero1 instanceof Rogue) {
			ability = new RogueAbilities(map.getCell(hero1.x, hero1.y),
					hero1, round);
			hero2.getsAttackedBy(ability);
		}
		if (hero1 instanceof Wizard) {
			ability = new WizardAbilities(
					map.getCell(hero1.x, hero1.y),
					hero1, round);
			hero2.getsAttackedBy(ability);
		}
		if (hero1 instanceof Pyromancer) {
			ability = new PyromancerAbilities(
					map.getCell(hero1.x, hero1.y), hero1);
			hero2.getsAttackedBy(ability);
		}
		if (hero1 instanceof Knight) {
			ability = new KnightAbilities(
					map.getCell(hero1.x, hero1.y), hero1);
			hero2.getsAttackedBy(ability);
		}
	}

	/* daca unul din eroi castiga o lupta, el primeste xp dupa formula
	 * din enunt */
	public static void winner(final Hero hero1, final Hero hero2)  {
		if (hero1.hp <= 0 && hero2.hp > 0) {
			hero2.xp += Math.max(0, Constants.XP_GAINER
					- (hero2.lvl - hero1.lvl) * Constants.LVL_DIFF_XP);
		} else if (hero1.hp > 0 && hero2.hp <= 0) {
			hero1.xp += Math.max(0, Constants.XP_GAINER
					- (hero1.lvl - hero2.lvl) * Constants.LVL_DIFF_XP);
		}
	}

	/* in functie de cat xp are, eroul poate face level-up si sa isi
	 * refaca intreg hp-ul */
	public void increaseLvlAndRestoreHp() {
		int lvlUp = (this.xp - Constants.INITIAL_XP) / Constants.XP_LEVEL_STEP;
		if (lvlUp > 0) {
			this.lvl = lvlUp + 1;
			this.hp = this.initHp + this.lvl * lvlUpHpIncrease;
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

}
