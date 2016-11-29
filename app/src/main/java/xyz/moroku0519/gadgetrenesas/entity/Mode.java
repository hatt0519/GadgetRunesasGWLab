package xyz.moroku0519.gadgetrenesas.entity;

/**
 * Created by kazuki on 2016/11/16.
 */

public enum Mode {
    OFF("O"), RADIO("R"), ECHO("S");

    public final String id;

    private Mode(String key) {
        id = key;
    }
}
