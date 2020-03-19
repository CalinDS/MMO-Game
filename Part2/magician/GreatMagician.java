package magician;

import java.io.IOException;
import java.io.Writer;

import angels.Angel;
import heroes.Hero;

public final class GreatMagician {

    private static GreatMagician instance = null;

    private GreatMagician() {
    }

    /* magicianul e singleton */
    public static GreatMagician getInstance() {
        if (instance == null) {
            return new GreatMagician();
        }
        return instance;
    }

    /* magicianul e notificat ca a aparut un nou inger */
    public void getNotifiedByNewAngel(final Angel angel,
            final Writer out) throws IOException {
        out.write(angel.toString());
        out.write("\n");
    }

    /* magicianul e notificat ca ingerul a actionat asupra
     * unui erou
     */
    public void getNotifiedByAngelAction(final Angel angel,
            final Hero hero, final Writer out) throws Exception {
        angel.visitPrint(hero, out);
    }

    /* magicianul e notificat ca un inger a omorat un erou */
    public void getNotifiedByAngelKill(final Hero hero,
            final Writer out) throws Exception {
        out.write("Player " +  hero.getName() + " "
    + hero.getId() + " was killed by an angel" + "\n");
    }

    /* magicianul e notificat ca un inger a inviat un erou */
    public void getNotifiedByAngelRevive(final Hero hero,
            final Writer out) throws Exception {
        out.write("Player " +  hero.getName() + " "
    + hero.getId() + " was brought to life by an angel" + "\n");
    }

    /* magicianul e notificat ca un erou a facut level-up */
    public void getNotifiedByLvlUp(final Hero hero, final Writer out,
            final int lvl) throws Exception {
        out.write(hero.getName() + " " + hero.getId()
        + " reached level " + lvl + "\n");
    }

    /* magicianul e notificat ca un erou a omorat un altul, dar am decis
     * sa mai schimb si sa pastrez afisarea in functia de winner. Aici
     * deci momentan nu fac nimic
     */
    public void getNotifiedByKill(final Hero hero1, final Hero hero2) {
    }

}
