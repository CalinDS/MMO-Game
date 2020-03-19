package angels;

import java.io.Writer;

import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public abstract class Angel {

    private int x;
    private int y;
    private String type;


    public Angel(final String type, final int x, final int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    /* acest tip de heal e folosit de mai multi ingeri asa ca am preferat
     * sa il pun in clasa parinte
     */
    public void genericHeal(final int heal, final Hero hero) {
        int maxHp = hero.getInitHp() + hero.getLvl() * hero.getLvlUpHpIncrease();
        int hp = hero.getHp();
        hp += heal;
        hero.setHp(hp);
        if (hero.getHp() > maxHp) {
            hero.setHp(maxHp);
        }
    }

    /* toti ingerii care dau damage dau damage la fel */
    public void genericDmg(final int dmg, final Hero hero) {
        hero.setHp(hero.getHp() - dmg);
    }

    /* functia din clasa abstracta parinte care presupune ce
     * se va afisa in urma unui visit
     */
    public void visitPrint(final Hero hero,
    final Writer out) throws Exception {
    }

    /* vizitarea unui erou (generic) */
    public void visits(final Hero hero) {
    }

    /* vizitare knight*/
    public void visits(final Knight hero) {
    }

    /* vizitare pyro */
    public void visits(final Pyromancer hero) {
    }

    /* vizitare rogue */
    public void visits(final Rogue hero) {
    }

    /* vizitare wizard */
    public void visits(final Wizard hero) {
    }

    /* @Override*/
    public String toString() {
        return "Angel " + type + " was spawned at " + x + " " + y;
    }

    /* getters and setters */
    public String getType() {
        return type;
    }

    /* getters and setters */
    public void setType(final String type) {
        this.type = type;
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



}
