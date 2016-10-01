package com.zhulinfeng.mine;

import javax.swing.*;

public class Mine extends JButton {
    private MineState mineState;
    private final MineValue value;
    private final Position position;

    public Mine(MineValue value, Position position) {
        this.value = value;
        this.position = position;
        setState(MineState.COVERED);
    }

    public MineState getMineState() {
        return mineState;
    }

    public Position getPosition() {
        return position;
    }

    public void rightClick() {
        if (MineState.COVERED == mineState) {
            setState(MineState.MARKED);
        } else if (MineState.MARKED == mineState) {
            setState(MineState.COVERED);
        }
    }

    public void leftClick() throws BombException, ZeroMine {
        if (MineState.COVERED == mineState || MineState.DETECTED == mineState) {
            spot();
            if (value.isBomb()) {
                throw new BombException();
            } else if (value.getValue() == MineValue.MIN_VALUE) {
                throw new ZeroMine();
            }
        }
    }

    public void press() throws DetectSurround {
        if (MineState.SPOTTED == mineState) {
            throw new DetectSurround();
        }
    }

    public void release() throws BordDetectSurround {
        if (MineState.SPOTTED == mineState) {
            throw new BordDetectSurround();
        }
    }

    public boolean detected() throws BombException {
        if (MineState.MARKED == mineState && !value.isBomb()) {
            throw new BombException();
        } else if (MineState.COVERED == mineState) {
            setState(MineState.DETECTED);
        }

        return value.isBomb() && MineState.DETECTED == mineState;
    }

    public void bordDetect() {
        if (MineState.DETECTED == mineState) {
            setState(MineState.COVERED);
        }
    }

    public void spot() {
        if (value.isBomb()) {
            super.setText("<html><font color=red size = 10>" + value.getView() + "</font></html>");
        } else {
            super.setText("<html><font color=blue size = 6>" + value.getView() + "</font></html>");
        }
        setState(MineState.SPOTTED);
    }

    private void setState(MineState state) {
        this.mineState = state;
        super.setBackground(mineState.getColor());
    }
}
