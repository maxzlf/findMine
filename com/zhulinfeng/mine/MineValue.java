package com.zhulinfeng.mine;

public class MineValue {
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 9;
    private final int value;

    public MineValue(int value) {
        Preconditions.checkState(value >= MIN_VALUE && value <= MAX_VALUE);
        this.value = value;
    }

    public int getValue() throws BombException {
        if (value == MAX_VALUE) {
            throw new BombException();
        }
        return value;
    }

    public char getView() {
        if (MAX_VALUE == value) {
            return '*';
        } else if (MIN_VALUE == value) {
            return ' ';
        } else {
            return (char)('0' + value);
        }
    }

    public boolean isBomb() {
        return MAX_VALUE == value;
    }
}
