package com.zhulinfeng.mine;

public enum  Level {
    LEVEL1(9, 9, 10, Level.UNIT_SIZE),
    LEVEL2(16, 16, 40, Level.UNIT_SIZE),
    LEVEL3(16, 30, 99, Level.UNIT_SIZE);

    public static final int UNIT_SIZE = 50;
    public static final int STATUSBAR_SIZE = 30;

    public final int width;
    public final int hight;
    public final int row;
    public final int col;
    public final int mineNumber;
    public final int unitSize;

    Level(int row, int col, int mineNumber, int unitSize) {
        this.row = row;
        this.col = col;
        this.mineNumber = mineNumber;
        this.width = col * UNIT_SIZE;
        this.hight = row * UNIT_SIZE + STATUSBAR_SIZE;
        this.unitSize = unitSize;
    }
}
