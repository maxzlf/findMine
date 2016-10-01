package com.zhulinfeng.mine;

public class Position {
    public final int row;
    public final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position position = (Position)o;
            return (this.row == position.row) && (this.col == position.col);
        } else {
            return false;
        }
    }

    public boolean isArround(Position position) {
        return Math.abs(this.row - position.row) <= 1 &&
                Math.abs(this.col - position.col) <= 1 &&
                !equals(position);
    }
}
