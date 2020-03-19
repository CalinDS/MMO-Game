package utilities;

public final class Constants {

    private Constants() {
    }

    public static final int KNIGHT_HP = 900;
    public static final int ROGUE_HP = 600;
    public static final int WIZARD_HP = 400;
    public static final int PYROMANCER_HP = 500;

    public static final int KNIGHT_LEVEL_HP = 80;
    public static final int ROGUE_LEVEL_HP = 40;
    public static final int WIZARD_LEVEL_HP = 30;
    public static final int PYROMANCER_LEVEL_HP = 50;

    public static final int INITIAL_XP = 250;
    public static final int XP_LEVEL_STEP = 50;
    public static final int LVL_DIFF_XP = 40;
    public static final int XP_GAINER = 200;

    public static final float EXECUTE = 200f;
    public static final float SLAM = 100f;
    public static final float FIELD_K_AMPLIFIER = 0.15f;
    public static final float EXECUTE_LVL_INCREASE = 30f;
    public static final float SLAM_LVL_INCREASE = 40f;
    public static final float SLAM_HP_LIMIT = 0.2f;
    public static final float SLAM_HPLIM_INCREASE = 0.01f;
    public static final float SLAM_HP_CAP = 20f;
    public static final float ROGUE_EXECUTE_AMPLIFIER = 0.15f;
    public static final float ROGUE_SLAM_AMPLIFIER = -0.2f;
    public static final float KNIGHT_EXECUTE_AMPLIFIER = 0f;
    public static final float KNIGHT_SLAM_AMPLIFIER = +0.2f;
    public static final float PYRO_EXECUTE_AMPLIFIER = 0.1f;
    public static final float PYRO_SLAM_AMPLIFIER = -0.1f;
    public static final float WIZARD_EXECUTE_AMPLIFIER = -0.2f;
    public static final float WIZARD_SLAM_AMPLIFIER = 0.05f;

    public static final float FIREBLAST = 350f;
    public static final float IGNITE = 150f;
    public static final float IGNITE_OT = 50f;
    public static final int ROUNDS_IGNITE = 2;
    public static final float FIELD_P_AMPLIFIER = 0.25f;
    public static final float FIREBLAST_LVL_INCREASE = 50f;
    public static final float IGNITE_LVL_INCREASE = 20f;
    public static final float IGNITEOT_LVL_INCREASE = 30f;
    public static final float ROGUE_FIRE_AMPLIFIER = -0.2f;
    public static final float WIZARD_FIRE_AMPLIFIER = 0.05f;
    public static final float PYRO_FIRE_AMPLIFIER = -0.1f;
    public static final float KNIGHT_FIRE_AMPLIFIER = 0.2f;

    public static final float BACKSTAB = 200f;
    public static final float PARALYSIS = 40f;
    public static final int PARALYISIS_DURATION = 3;
    public static final float FIELD_R_AMPLIFIER = 0.15f;
    public static final float BACKSTAB_LVL_INCREASE = 20f;
    public static final float PARALYSIS_LVL_INCREASE = 10f;
    public static final int CRIT_PERIOD = 3;
    public static final float CRIT = 1.5f;
    public static final float WIZARD_BACKSTAB_AMPLIFIER = 1.25f;
    public static final float WIZARD_PARALYSIS_AMPLIFIER = 1.25f;
    public static final float KNIGHT_BACKSTAB_AMPLIFIER = 0.9f;
    public static final float KNIGHT_PARALYSIS_AMPLIFIER = 0.8f;
    public static final float PYRO_BACKSTAB_AMPLIFIER = 1.25f;
    public static final float PYRO_PARALYSIS_AMPLIFIER = 1.2f;
    public static final float ROGUE_BACKSTAB_AMPLIFIER = 1.2f;
    public static final float ROGUE_PARALYSIS_AMPLIFIER = 0.9f;

    public static final float DRAIN = 0.2f;
    public static final float DEFLECT = 0.35f;
    public static final float FIELD_W_AMPLIFIER = 0.1f;
    public static final float DRAIN_LVL_INCREASE = 0.05f;
    public static final float DEFLECT_LVL_INCREASE = 0.02f;
    public static final float ROGUE_DRAIN_AMPLIFIER = 0.8f;
    public static final float ROGUE_DEFLECT_AMPLIFIER = 1.2f;
    public static final float PYRO_DRAIN_AMPLIFIER = 0.9f;
    public static final float PYRO_DEFLECT_AMPLIFIER = 1.3f;
    public static final float DRAIN_COEF = 0.3f;
    public static final float WIZARD_DRAIN_AMPLIFIER = 1.05f;
    public static final float KNIGHT_DRAIN_AMPLIFIER = 1.2f;
    public static final float KNIGHT_DEFLECT_AMPLIFIER = 1.4f;

    public static final int W_DEF_HP_LIM = 4;
    public static final int W_OFF_HP_LIM = 2;
    public static final int W_DEF_HP_MOD = 5;
    public static final float W_DEF_COEF_MOD = 0.2f;
    public static final int W_OFF_HP_MOD = 10;
    public static final float W_OFF_COEF_MOD = 0.6f;

    public static final int R_DEF_HP_LIM = 7;
    public static final int R_OFF_HP_LIM = 5;
    public static final int R_DEF_HP_MOD = 2;
    public static final float R_DEF_COEF_MOD = 0.1f;
    public static final int R_OFF_HP_MOD = 7;
    public static final float R_OFF_COEF_MOD = 0.4f;

    public static final int K_DEF_HP_LIM = 3;
    public static final int K_OFF_HP_LIM = 2;
    public static final int K_DEF_HP_MOD = 4;
    public static final float K_DEF_COEF_MOD = 0.2f;
    public static final int K_OFF_HP_MOD = 5;
    public static final float K_OFF_COEF_MOD = 0.5f;

    public static final int P_DEF_HP_LIM = 4;
    public static final int P_OFF_HP_LIM = 3;
    public static final int P_DEF_HP_MOD = 3;
    public static final float P_DEF_COEF_MOD = 0.3f;
    public static final int P_OFF_HP_MOD = 4;
    public static final float P_OFF_COEF_MOD = 0.7f;

    public static final float DMG_ANG_ON_K = 0.15f;
    public static final float DMG_ANG_ON_P = 0.2f;
    public static final float DMG_ANG_ON_R = 0.3f - 0.0001f;
    public static final float DMG_ANG_ON_W = 0.4f;

    public static final int DARK_ANG_ON_K = 40;
    public static final int DARK_ANG_ON_P = 30;
    public static final int DARK_ANG_ON_R = 10;
    public static final int DARK_ANG_ON_W = 20;

    public static final float DRACULA_COEF_ON_K = 0.2f;
    public static final int DRACULA_HP_ON_K = 60;
    public static final float DRACULA_COEF_ON_P = 0.3f;
    public static final int DRACULA_HP_ON_P = 40;
    public static final float DRACULA_COEF_ON_R = 0.1f + 0.0001f;
    public static final int DRACULA_HP_ON_R = 35;
    public static final float DRACULA_COEF_ON_W = 0.4f;
    public static final int DRACULA_HP_ON_W = 20;

    public static final float GOODBOY_COEF_ON_K = 0.4f;
    public static final int GOODBOY_HP_ON_K = 20;
    public static final float GOODBOY_COEF_ON_P = 0.5f;
    public static final int GOODBOY_HP_ON_P = 30;
    public static final float GOODBOY_COEF_ON_R = 0.4f - 0.0001f;
    public static final int GOODBOY_HP_ON_R = 40;
    public static final float GOODBOY_COEF_ON_W = 0.3f;
    public static final int GOODBOY_HP_ON_W = 50;

    public static final float L_UP_ANG_ON_K = 0.1f;
    public static final float L_UP_ANG_ON_P = 0.2f;
    public static final float L_UP_ANG_ON_R = 0.15f - 0.0001f;
    public static final float L_UP_ANG_ON_W = 0.25f;

    public static final int LIFE_GIVER_ON_K = 100;
    public static final int LIFE_GIVER_ON_P = 80;
    public static final int LIFE_GIVER_ON_R = 90;
    public static final int LIFE_GIVER_ON_W = 120;

    public static final float SMALL_ANG_COEF_ON_K = 0.1f;
    public static final int SMALL_ANG_HP_ON_K = 10;
    public static final float SMALL_ANG_COEF_ON_P = 0.15f;
    public static final int SMALL_ANG_HP_ON_P = 15;
    public static final float SMALL_ANG_COEF_ON_R = 0.05f - 0.0001f;
    public static final int SMALL_ANG_HP_ON_R = 20;
    public static final float SMALL_ANG_COEF_ON_W = 0.1f;
    public static final int SMALL_ANG_HP_ON_W = 25;

    public static final int SPAWNER_ON_K = 200;
    public static final int SPAWNER_ON_P = 150;
    public static final int SPAWNER_ON_R = 180;
    public static final int SPAWNER_ON_W = 120;

    public static final int XP_ANG_ON_K = 45;
    public static final int XP_ANG_ON_P = 50;
    public static final int XP_ANG_ON_R = 40;
    public static final int XP_ANG_ON_W = 60;

    public static final int MAX_MAP = 100;

}
