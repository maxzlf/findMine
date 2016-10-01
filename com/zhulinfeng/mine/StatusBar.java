package com.zhulinfeng.mine;

import javax.swing.*;

public class StatusBar {
    private JLabel remainsLable = new JLabel();
    private int remains;

    public StatusBar(int remains) {
        this.remains = remains;
    }

    public JLabel getRemainsLable() {
        setText();
        return remainsLable;
    }

    public void increse() {
        remains++;
        setText();
    }

    public void decrease() {
        remains--;
        setText();
    }

    private void setText() {
        this.remainsLable.setText("remain mine : " + remains);
    }
}
