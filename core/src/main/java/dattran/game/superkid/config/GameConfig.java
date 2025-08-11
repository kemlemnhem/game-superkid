package dattran.game.superkid.config;

import com.badlogic.gdx.math.Vector2;

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

    public static final Vector2 KID_KICK_OFFSET_RIGHT = new Vector2(0.3f,0f);
    public static final Vector2 KID_KICK_OFFSET_LEFT = new Vector2(-0.3f,0f);
    public static final float KID_KICK_WIDTH = 0.1f;
    public static final float KID_KICK_HEIGHT = 0.1f;
    public static final float KID_KICK_HIT_BOX_MAX_ALLOW_DISTANCE = 0.13f;
    public static final float KID_KICK_BODY_MAX_ALLOW_DISTANCE = 0.15f;

    public static final Vector2 KID_PUNCH_OFFSET = new Vector2(0.3f,0.15f);
    public static final float KID_PUNCH_WIDTH = 0.2f;
    public static final float KID_PUNCH_HEIGHT = 0.2f;


    public static final Vector2 KID_THUMP_OFFSET = new Vector2(0.3f,0.15f);
    public static final float KID_THUMP_WIDTH = 0.2f;
    public static final float KID_THUMP_HEIGHT = 0.2f;

    public static final float KID_WIDTH  = 128 / PPM ;
    public static final float KID_HEIGHT = 128 / PPM;


    public static final float SEAPORT_MAP_WIDTH = 2304 /*72 tile * 32 size*/ / PPM;
    public static final float SEAPORT_MAP_HEIGHT = 640 /*20 tile * 32 size*/ / PPM;


    public static final short ENEMY_BIT = 1;
    public static  final short KID_ATTACK_BIT = 2;
    public static final short GROUND_BIT = 4;
    public static final short KID_BIT = 8;

}
