package game;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import angels.Angel;
import angels.AngelFactory;
import angels.Spawner;
import heroes.Hero;
import heroes.HeroFactory;
import magician.GreatMagician;
import map.Map;

public final class Game {

    private Game() {
    }

    /* crearea eroilor pe baza inputului */
    public static void createHeroes(final ArrayList<Hero> heroes, final Scanner in,
            final int noHeroes) {
        Hero hero;
        for (int i = 0; i < noHeroes; i++) {
            String str = in.nextLine();
            String[] getNum = str.split(" ");
            int x = Integer.valueOf(getNum[1]);
            int y = Integer.valueOf(getNum[2]);
            /* folosire factory */
            hero = HeroFactory.createHero(x, y, str.charAt(0));
            hero.setId(i);
            heroes.add(hero);
        }
    }

    /* implementarea pentru o runda */
    public static void playRound(final Writer out,
            final GreatMagician magician, final ArrayList<Hero> heroes,
            final ArrayList<String> allAngels, final ArrayList<String> commands,
            final int i, final Map map) throws Exception {
        out.write("~~ Round " + (i + 1) + " ~~");
        out.write("\n");
        /* obtin ingerii si comenzile pentru runda curenta */
        ArrayList<Angel> roundAngels = new ArrayList<>();
        String command = commands.get(i);

        String angelsLine = allAngels.get(i);
        String[] getAngels = angelsLine.split(" ");
        int noAngels = Integer.valueOf(getAngels[0]);
        for (int a = 1; a <= noAngels; a++) {
            String[] oneAngel = getAngels[a].split(",");
            Angel angel;
            int x = Integer.valueOf(oneAngel[1]);
            int y = Integer.valueOf(oneAngel[2]);
            angel = AngelFactory.createAngel(oneAngel[0], x, y);
            roundAngels.add(angel);
        }

        /* iau in calcul eventualul damage over time si
         * apoi, pentru eroii in viata, aplic una din strategii
         */
        for (Hero h: heroes) {
            h.processHp();
            if (h.getHp() > 0) {
                h.applyStrat();
            }
        }

        for (int j = 0; j < heroes.size(); j++) {
            /* conform input-ului, fiecare erou primeste comanda de move,
             * care, asa cum e implementata in Hero, prevede si cazul in
             * care avem eroi imobilizati
             */
            if (heroes.get(j).getHp() > 0) {
                heroes.get(j).move(command.charAt(j));
            }
        }

        /* daca, dupa mutari, doi eroi ajung pe aceeasi casuta, si au
         * hp mai mare ca 0, ei se lupta
         */
        for (int j = 0; j < heroes.size() - 1; j++) {
            for (int k = j + 1; k < heroes.size(); k++) {
                if (heroes.get(j).getX() == heroes.get(k).getX()
                        && heroes.get(j).getY()
                        == heroes.get(k).getY()
                        && heroes.get(j).getHp() > 0 && heroes.get(k).getHp() > 0) {
                    Hero.fight(heroes.get(j), heroes.get(k), map, i);
                    Hero.winner(heroes.get(j), heroes.get(k), magician, out);
                }
            }
        }

        /* se verifica daca vreun jucator a castigat suficienta experienta
         * in urma luptelor pentru a face level-up */
        for (Hero h : heroes) {
            if (h.getHp() > 0) {
                h.increaseLvlAndRestoreHp(magician, out);
            }
        }

        /* ingerii viziteaza fiecare erou care se afla in casuta lor */
        for (Angel a : roundAngels) {
            /* voi notifica marele magician pentru fiecare actiune ceruta
             * de cerinta
             */
            magician.getNotifiedByNewAngel(a, out);
            for (Hero h : heroes) {
                /* tratez special faptul ca doar Spawner viziteaza eroii
                 * cu hp mai mic sau egal cu 0
                 */
                if (h.getHp() > 0 || a instanceof Spawner) {
                    if (a.getX() == h.getX() && a.getY() == h.getY()) {
                        int oldHp = h.getHp();
                        if ((oldHp <= 0 && a instanceof Spawner)
                                || (oldHp > 0 && !(a instanceof Spawner))) {
                            /* eroii accepta actiunea ingerilor */
                            h.accepts(a);
                            magician.getNotifiedByAngelAction(a, h, out);
                        }
                        if (h.getHp() <= 0) {
                            magician.getNotifiedByAngelKill(h, out);
                        }
                        /* pentru cazul meu, in care doar Spawner da revive
                         * si doar Spawner actioneaza asupra eroilor cu hp
                         * nepozitiv, si am tratat acest lucru in if-ul mare,
                         * e conditie suficienta pentru notificarea de revive
                         */
                        if (oldHp <= 0) {
                            magician.getNotifiedByAngelRevive(h, out);
                        }
                    }
                }
                /* fac level up daca vreun erou a fost vizitat de un inger
                 * care sa ii dea xp
                 */
                h.increaseLvlAndRestoreHp(magician, out);
            }
        }
        out.write("\n");
    }

}
