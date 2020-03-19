package map;

import java.util.ArrayList;

import utilities.Constants;

/* clasa pentru harta */
public final class Map {

    private char[][] cellsType = new char[Constants.MAX_MAP][Constants.MAX_MAP];
    private static Map instance = null;

    /* constructorul primeste ca parametrii partea de input a problemei
     * corespunzatoare hartii si creeaza o harta pe baza lui.
     */
    private Map(final int noRows, final int noCols,
            final ArrayList<String> mapInfo) {
        for (int i = 0; i < noRows; i++) {
            for (int j = 0; j < noCols; j++) {
                cellsType[i][j] = mapInfo.get(i).charAt(j);
            }
        }
    }

    /* map e singleton */
    public static Map getInstance(final int noRows, final int noCols,
            final ArrayList<String> mapInfo) {
        if (instance == null) {
            instance = new Map(noRows, noCols, mapInfo);
        }
        return instance;
    }

    /* returneaza caracterul de la o pozitie data */
    public char getCell(final int x, final int y) {
        return cellsType[x][y];
    }

    /* getters and setters */
    public char[][] getCellsType() {
        return cellsType;
    }

    /* getters and setters */
    public void setCellsType(final char[][] cellsType) {
        this.cellsType = cellsType;
    }
}

