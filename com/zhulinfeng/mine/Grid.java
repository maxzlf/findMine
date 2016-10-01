package com.zhulinfeng.mine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

class Grid implements MouseListener {
    private Mine[][] mineTable;
    private Level level;
    private Random random = new Random();

    public Grid(Level level) {
        this.level = level;
        mineTable = new Mine[level.row][level.col];
        initMap();
    }

    public Mine getMine(Position position) {
        return mineTable[position.row][position.col];
    }

    private void initMap() {
        ArrayList<Position> bombs = initBomb();

        for (int row=0; row<level.row; row++) {
            for (int col=0; col<level.col; col++) {
                mineTable[row][col] = createMine(bombs, new Position(row,col));
            }
        }
    }

    private Mine createMine(ArrayList<Position> bomns, Position position) {
        int count = 0;
        boolean isBomb = false;

        for (Position pt : bomns) {
            if (pt.isArround(position)) {
                count++;
            } else if (pt.equals(position)) {
                isBomb = true;
                break;
            }
        }

        Mine mine;
        if (isBomb)
            mine = new Mine(new MineValue(MineValue.MAX_VALUE), position);
        else {
            mine = new Mine(new MineValue(count), position);
        }
        mine.setSize(level.unitSize, level.unitSize);
        mine.addMouseListener(this);
        return mine;
    }

    private ArrayList<Position> initBomb() {
        ArrayList<Position> bombs = new ArrayList<>();

        for (int i=0; i<level.mineNumber; i++) {
            Position tmp = getNextRandom();
            while (bombs.contains(tmp)) {
                tmp = getNextRandom();
            }
            bombs.add(tmp);
        }

        return bombs;
    }

    private Position getNextRandom() {
        return new Position(random.nextInt(level.row), random.nextInt(level.col));
    }

    private void bomb() {
        for (int row=0; row<level.row; row++) {
            for (int col=0; col<level.col; col++) {
                mineTable[row][col].spot();
            }
        }
    }

    private void zeroMineSurround(Mine mine) {
        ArrayList<Mine> surround = getSurround(mine.getPosition());

        for (Mine m : surround) {
            leftClick(m);
        }
    }

    private void detectSurround(Mine mine) {
        ArrayList<Mine> surround = getSurround(mine.getPosition());
        int count = 0;

        try {
            for (Mine m : surround) {
                if (m.detected()) {
                    count++;
                }
            }
        } catch (BombException e) {
            bomb();
        }

        if (0 == count) {
            for (Mine m : surround) {
                leftClick(m);
            }
        }
    }

    private void bordDetectSurround(Mine mine) {
        ArrayList<Mine> surround = getSurround(mine.getPosition());

        for (Mine m : surround) {
            m.bordDetect();
        }
    }

    private void leftClick(Mine mine) {
        try {
            mine.leftClick();
        } catch (BombException e) {
            bomb();
        } catch (ZeroMine e) {
            zeroMineSurround(mine);
        }
    }

    private void rightClick(Mine mine) {
        mine.rightClick();
    }

    private void press(Mine mine) {
        try {
            mine.press();
        } catch (DetectSurround detectSurround) {
            detectSurround(mine);
        }
    }

    private ArrayList<Mine> getSurround(Position position) {
        ArrayList<Mine> surrounded = new ArrayList<>();

        int top = position.row<=0? position.row : position.row - 1;
        int left = position.col<=0? position.col : position.col - 1;
        int bottom = position.row+1 >= level.row? position.row : position.row + 1;
        int right  = position.col+1 >= level.col? position.col : position.col + 1;

        for (int row=top; row<=bottom; row++) {
            for (int col=left; col<=right; col++) {
                if (!position.equals(new Position(row, col)) && mineTable[row][col].getMineState() != MineState.SPOTTED) {
                    surrounded.add(mineTable[row][col]);
                }
            }
        }
        return surrounded;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof Mine) {
            Mine mine = (Mine) e.getSource();
            if (e.getButton() == MouseEvent.BUTTON1) {
                leftClick(mine);
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                rightClick(mine);
            }
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof Mine) {
            Mine mine = (Mine) e.getSource();
            if (e.getButton() == MouseEvent.BUTTON3) {
                press(mine);
            }
        }
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() instanceof Mine) {
            Mine mine = (Mine) e.getSource();
            if (e.getButton() == MouseEvent.BUTTON3) {
                try {
                    mine.release();
                } catch (BordDetectSurround bordDetectSurround) {
                    bordDetectSurround(mine);
                }
            }
        }
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
