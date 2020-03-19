package main;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import game.Game;
import map.Map;
import heroes.Hero;
import magician.GreatMagician;

public final class Main {

    private Main() {
    }

    public static void main(final String[] args) throws Exception {

        Scanner in = new Scanner(new File(args[0]));
        Writer out = new FileWriter(args[1]);

        ArrayList<String> commands = new ArrayList<>();
        ArrayList<String> allAngels = new ArrayList<>();
        int mapRows = in.nextInt();
        int mapCols = in.nextInt();
        in.nextLine();

        /* se creeaza o instanta singleton a marelui magician */
        GreatMagician magician = GreatMagician.getInstance();
        ArrayList<String> mapInfo = new ArrayList<>(mapRows);
        for (int i = 0; i < mapRows; i++) {
            String str = in.nextLine();
            mapInfo.add(str);
        }
        /* crearea terenului de joc pe baza inputului */
        Map map = Map.getInstance(mapRows, mapCols, mapInfo);

        /* crearea eroilor pe baza inputului */
        int noHeroes = in.nextInt();
        in.nextLine();
        ArrayList<Hero> heroes = new ArrayList<>(noHeroes);
        Game.createHeroes(heroes, in, noHeroes);

        int noRounds = in.nextInt();
        in.nextLine();

        /* trec prin tot input-ul ca sa obtin comenzile
         * si ingerii, pe care ii voi lua apoi separat
         * runda cu runda
         */
        for (int i = 0; i < noRounds; i++) {
            String str = in.nextLine();
            commands.add(str);
        }

        for (int i = 0; i < noRounds; i++) {
            String str = in.nextLine();
            allAngels.add(str);
        }

        /* se joaca fiecare runda */
        for (int i = 0; i < noRounds; i++) {
            Game.playRound(out, magician, heroes,
                    allAngels, commands, i, map);
        }

        /* afisarea clasamentului */
        out.write("~~ Results ~~");
        out.write("\n");
        for (Hero h : heroes) {
            out.write(h.toString() + "\n");
        }

        in.close();
        out.close();

    }
}
