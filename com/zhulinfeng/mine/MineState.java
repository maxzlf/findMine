package com.zhulinfeng.mine;

import java.awt.*;

public enum MineState {
    COVERED(Color.LIGHT_GRAY),
    MARKED(Color.RED),
    SPOTTED(Color.WHITE),
    DETECTED(Color.WHITE),
    BOMBED(Color.WHITE);

    private final Color color;
    private MineState(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
