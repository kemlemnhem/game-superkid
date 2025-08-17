package dattran.game.superkid.config;

import com.badlogic.gdx.math.Vector2;

public final class GameConfig {


    private GameConfig() {}
    public static final float PPM = 100;

    public static final float WORLD_GRAVITY = 0f;

    public static final float WORLD_DELTA_TIME = 1/60f;

    public static final int WORLD_VELOCITY_ITERATION = 6;

    public static final int WORLD_POSITION_ITERATION = 2;

    public static final int VIEWPORT_WIDTH = 2304;
    public static final int VIEWPORT_HEIGHT = 832;

    public static final int LANE_START_ROW = 3;
    public static final int LANE_END_ROW = 13;


    public static final float WALK_SPEED = 0.8f;

    public static final  float KID_JUMP_VELOCITY = 3f;
    public static final float KID_JUMP_SPEED = 1.4f;
    public static final float KID_JUMP_GRAVITY_SCALE_SLOW = 0.5f;
    public static final float KID_JUMP_GRAVITY_SCALE_NORMAL = 1f;
    public static final float KID_JUMP_GRAVITY_SCALE_FAST = 5f;
    public static final float KID_JUMP_MAX_TIME = 0.4f;
    public static final float KID_WALK_SPEED = 0.8f;
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

    public static final float KID_WIDTH  = 30 / PPM ;
    public static final float KID_HEIGHT = 81 / PPM;
    public static final float KID_HURT_SHIFT_X = 0.1f;
    public static final float KID_HURT_SHIFT_Y = 0f;



    public static final float HOMELESS1_WIDTH  = 128 / PPM ;
    public static final float HOMELESS1_HEIGHT = 128 / PPM;
    public static final float HOMELESS1_HURT_SHIFT_X = 0.3f;
    public static final float HOMELESS1_HURT_SHIFT_Y = 0f;

    public static final float HOMELESS_1_WALK_SPEED = 0.4f;
    public static final float HOMELESS_1_RUN_SPEED = 0.8f;

    public static final float HOMELESS_1_ATTACK_RANGE = 0.3f;

    public static final float HOMELESS_1_VIEW_RANGE = 1.5f;
    public static final float HOMELESS_1_DETECT_RANGE = 0.4f;
    public static final float HOMELESS_1_ATTACK_DELAY  = 2f;




}
