package angels;

public final class AngelFactory {

    private AngelFactory() {
    }

    /* crearea unui tip specific de inger */
    public static Angel createAngel(final String type,
            final int x, final int y) {
        if (type.equals("LifeGiver")) {
            return new LifeGiver(type, x, y);
        } else if (type.equals("LevelUpAngel")) {
            return new LevelUpAngel(type, x, y);
        } else if (type.equals("DarkAngel")) {
            return new DarkAngel(type, x, y);
        } else if (type.equals("Dracula")) {
            return new Dracula(type, x, y);
        } else if (type.equals("GoodBoy")) {
            return new GoodBoy(type, x, y);
        } else if (type.equals("SmallAngel")) {
            return new SmallAngel(type, x, y);
        } else if (type.equals("Spawner")) {
            return new Spawner(type, x, y);
        } else if (type.equals("TheDoomer")) {
            return new TheDoomer(type, x, y);
        } else if (type.equals("XPAngel")) {
            return new XpAngel(type, x, y);
        } else if (type.equals("DamageAngel")) {
            return new DamageAngel(type, x, y);
        }
        return null;
    }

}
