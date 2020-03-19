package abilities;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

/* clasa parinte pentru abilitatile
 * cu care vor fi atacati eroii */
public abstract class Abilities {

	protected float damage;

	public Abilities() {
	}

	/* functie parinte */
	public void attacks(final Hero hero) {
	}

	/* functie parinte */
	public void attacks(final Wizard hero) {
	}

	/* functie parinte */
	public void attacks(final Rogue hero) {
	}

	/* functie parinte */
	public void attacks(final Pyromancer hero) {
	}

	/* functie parinte */
	public void attacks(final Knight hero) {
	}

	/* getters and setters */
	public float getDamage() {
		return damage;
	}

	/* getters and setters */
	public void setDamage(final float damage) {
		this.damage = damage;
	}

}
