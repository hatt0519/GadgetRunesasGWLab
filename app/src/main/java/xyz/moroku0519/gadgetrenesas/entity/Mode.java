package xyz.moroku0519.gadgetrenesas.entity;

/**
 * Created by kazuki on 2016/11/16.
 */

public enum Mode {
    RADIO(0), ECHO(1), LINE(2);

    public final int id;

    private Mode(int key) {
        id = key;
    }
}
