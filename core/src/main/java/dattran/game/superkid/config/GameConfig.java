package dattran.game.superkid.config;

public final class GameConfig {


    private GameConfig() {}
    public static final float PPM = 100;

    public static final float WORLD_GRAVITY = -9.8f;

    public static final float WORLD_DELTA_TIME = 1/60f;

    public static final int WORLD_VELOCITY_ITERATION = 6;

    public static final int WORLD_POSITION_ITERATION = 2;

    public static final int VIEWPORT_WIDTH = 800;
    public static final int VIEWPORT_HEIGHT = 600;


    public static final  float KID_JUMP_VELOCITY = 3f;
    public static final float KID_JUMP_SPEED = 1.4f;
    public static final float KID_JUMP_GRAVITY_SCALE_SLOW = 0.5f;
    public static final float KID_JUMP_GRAVITY_SCALE_NORMAL = 1f;
    public static final float KID_JUMP_GRAVITY_SCALE_FAST = 5f;
    public static final float KID_JUMP_MAX_TIME = 0.4f;
    public static final float KID_WALK_SPEED = 1f;
    public static final float KID_RUN_SPEED = 1.2f;
    public static final float KID_WALK_IDLE_DIFFERENCE = 0.1f;

    public static final float KID_WIDTH  = 128 / PPM ;
    public static final float KID_HEIGHT = 128 / PPM;


    public static final float SEAPORT_MAP_WIDTH = 2304 /*72 tile * 32 size*/ / PPM;
    public static final float SEAPORT_MAP_HEIGHT = 640 /*20 tile * 32 size*/ / PPM;

}
