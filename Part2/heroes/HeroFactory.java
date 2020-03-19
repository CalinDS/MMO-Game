package heroes;

public final class HeroFactory {

    private HeroFactory() {
    }

    /* functia care creeaza un erou pe baza tipului dat de input */
    public static Hero createHero(final int x, final int y,
            final char type) {
        if (type == 'W') {
            return new Wizard(x, y);
        } else if (type == 'R') {
            return new Rogue(x, y);
        } else if (type == 'P') {
            return new Pyromancer(x, y);
        }
        return new Knight(x, y);
    }

}
