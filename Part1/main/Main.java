package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import map.Map;
import heroes.Hero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Main {

	private Main() {
	}

	public static void main(final String[] args) throws Exception {

		Scanner in = new Scanner(new File(args[0]));
		Writer out = new FileWriter(args[1]);

		int mapRows = in.nextInt();
		int mapCols = in.nextInt();
		in.nextLine();

		ArrayList<String> mapInfo = new ArrayList<>(mapRows);
		for (int i = 0; i < mapRows; i++) {
			String str = in.nextLine();
			mapInfo.add(str);
		}
		/* crearea terenului de joc pe baza inputului */
		Map map = new Map(mapRows, mapCols, mapInfo);

		/* crearea eroilor pe baza inputului */
		int noHeroes = in.nextInt();
		in.nextLine();
		ArrayList<Hero> heroes = new ArrayList<>(noHeroes);
		Hero hero;
		for (int i = 0; i < noHeroes; i++) {
			String str = in.nextLine();
			String[] getNum = str.split(" ");
			int x = Integer.valueOf(getNum[1]);
			int y = Integer.valueOf(getNum[2]);
			if (str.charAt(0) == 'W') {
				hero = new Wizard(x, y);
			} else if (str.charAt(0) == 'R') {
				hero = new Rogue(x, y);
			} else if (str.charAt(0) == 'P') {
				hero = new Pyromancer(x, y);
			} else {
				hero = new Knight(x, y);
			}
			heroes.add(hero);
		}

		int noRounds = in.nextInt();
		in.nextLine();

		for (int i = 0; i < noRounds; i++) {
			String str = in.nextLine();

			/* inainte de fiecare runda se aplica pentru fiecare
			 * erou, eventualul damage overtime
			 */
			for (Hero h: heroes) {
				h.processHp();
				/* daca moare in urma acestui damage, el este scos
				 * din joc si de pe harta
				 */
				if (h.getHp() <= 0) {
					 h.setX(-1);
				}
			}

			for (int j = 0; j < noHeroes; j++) {
				/* conform input-ului, fiecare erou primeste comanda de move,
				 * care, asa cum e implementata in Hero, prevede si cazul in
				 * care avem eroi imobilizati
				 */
				if (heroes.get(j).getHp() > 0) {
					heroes.get(j).move(str.charAt(j));
				}
			}

			/* daca, dupa mutari, doi eroi ajung pe aceeasi casuta, si au
			 * hp mai mare ca 0, ei se lupta
			 */
			for (int j = 0; j < noHeroes - 1; j++) {
				for (int k = j + 1; k < noHeroes; k++) {
					if (heroes.get(j).getX() == heroes.get(k).getX()
							&& heroes.get(j).getY()
							== heroes.get(k).getY()
							&& heroes.get(j).getX() >= 0) {
						/* primul il ataca pe al doilea */
						Hero.fight(heroes.get(j), heroes.get(k), map, i);
						/* apoi invers; nu am instructiuni intre aceste doua
						 * actiuni deci e ca si cum s-ar executa simultan;
						 * deci nu conteaza daca primul il omoara
						 * pe al doilea,are si al doilea timp sa-l
						 * atace pe primul
						 */
						Hero.fight(heroes.get(k), heroes.get(j), map, i);
						/* daca unul l-a omorat pe celalalt,
						 * el va primi xp */
						Hero.winner(heroes.get(j), heroes.get(k));
					}
				}
			}

			/* daca, in urma luptei, un erou a murit, e scos de pe harta;
			 * am considerat ca asa e cea mai facila metoda de a scapa
			 * de eroii fara hp care altfel ar fi putut ocupa casute chiar
			 * daca nu mai puteau face nimic
			 */
			for (Hero h : heroes) {
				if (h.getHp() <= 0) {
					 h.setX(-1);
				}
				/* daca, in urma luptei, un erou a obtinut suficient xp ca
				 * sa faca lvl-up, o va face acum
				 */
				h.increaseLvlAndRestoreHp();
			}
		}

		/* dupa ce se termina toate rundele, se obtine output-ul */
		heroes.forEach(e -> {
			try {
				out.write(e.toString());
				out.write("\n");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		out.write("\n");
		in.close();
		out.close();

	}
}
