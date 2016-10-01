package com.zhulinfeng.mine;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private Level level;

    public Window(Level level) {
        this.level = level;
        init();
    }

    public Level getLevel() {
        return level;
    }

    private void init() {
        setLookAndFeel();

        this.setTitle("find mine");
        this.setIconImage(Toolkit.getDefaultToolkit().createImage("bomb.png"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(level.width, level.hight);
        this.setJMenuBar(initMenu());

        initContent();
        initMenu();
        initStatusBar();

        this.setVisible(true);
        this.setResizable(false);
        this.setLocation(100, 50);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initContent() {
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        container.add(initGrid(), "Center");
        initMenu();
        initStatusBar();
    }

    private JPanel initGrid() {
        JPanel panel = new JPanel();
        panel.setSize(level.width, level.hight);
        panel.setLayout(new GridLayout(level.row, level.col));

        Grid grid = new Grid(level);

        for (int i = 0; i < level.row; i++) {
            for (int j = 0; j < level.col; j++) {
                panel.add(grid.getMine(new Position(i, j)));
            }
        }

        return panel;
    }

    private JMenuBar initMenu() {
        return new Menu(this).getMenuBar();
    }

    private void initStatusBar() {

    }
}
