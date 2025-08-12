package dattran.game.superkid.config;

public enum Flag {
    KID((short) (1)),
    KID_ATTACK((short) (1 << 1)),
    ENEMY((short) (1 << 2)),
    ENEMY_ATTACK((short) (1 << 3)),
    GROUND( (short) (1 << 4));
    private final short mask;

    Flag (short mask) {
        this.mask = mask;
    }

    public short getMask() {
        return mask;
    }

    public static short combine(Flag ... flags) {
        short result = 0;
        for (Flag flag : flags) {
            result |= flag.getMask();
        }
        return result;
    }
}
