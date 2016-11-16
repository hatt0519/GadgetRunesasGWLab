package xyz.moroku0519.gadgetrenesas.entity;

/**
 * Created by kazuki on 2016/11/16.
 */

public enum Command {
    UP(0), DOWN(1), RIGHT(2), LEFT(3), STOP(4);

    public final int id;

    private Command(int key) {
        id = key;
    }
}
