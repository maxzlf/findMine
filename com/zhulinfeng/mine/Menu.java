package com.zhulinfeng.mine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {
    private Window jFrame;

    public Menu (Window jFrame) {
        this.jFrame = jFrame;
    }

    public JMenuBar getMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(setMenu1());
        menuBar.add(setMenu2());

        return menuBar;
    }

    private JMenu setMenu1() {
        JMenu menu = new JMenu("game");
        Item11 item11 = new Item11("new game");
        Item12 item12 = new Item12("level 1 : 9x9");
        Item13 item13 = new Item13("level 2 : 16x16");
        Item14 item14 = new Item14("level 3 : 16x30");
        Item15 item15 = new Item15("exit");

        item11.addActionListener(this);
        item12.addActionListener(this);
        item13.addActionListener(this);
        item14.addActionListener(this);
        item15.addActionListener(this);

        menu.add(item11);
        menu.add(item12);
        menu.add(item13);
        menu.add(item14);
        menu.add(item15);

        return menu;
    }

    private JMenu setMenu2() {
        JMenu menu = new JMenu("info");

        Item21 item21 = new Item21("about");
        item21.addActionListener(this);

        menu.add(item21);

        return menu;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof ItemAction) {
            ItemAction itemAction = (ItemAction)e.getSource();
            itemAction.action();
        }
    }

    private interface ItemAction {
        public void action();
    }

    private class Item11 extends JMenuItem implements ItemAction {
        public Item11(String str) {
            super(str);
        }
        @Override
        public void action() {
            jFrame.dispose();
            jFrame = new Window(jFrame.getLevel());
        }
    }

    private class Item12 extends JMenuItem implements ItemAction {
        public Item12(String str) {
            super(str);
        }
        @Override
        public void action() {
            jFrame.dispose();
            jFrame = new Window(Level.LEVEL1);
        }
    }

    private class Item13 extends JMenuItem implements ItemAction {
        public Item13(String str) {
            super(str);
        }
        @Override
        public void action() {
            jFrame.dispose();
            jFrame = new Window(Level.LEVEL2);
        }
    }

    private class Item14 extends JMenuItem implements ItemAction {
        public Item14(String str) {
            super(str);
        }
        @Override
        public void action() {
            jFrame.dispose();
            jFrame = new Window(Level.LEVEL3);
        }
    }

    private class Item15 extends JMenuItem implements ItemAction {
        public Item15(String str) {
            super(str);
        }
        @Override
        public void action() {
            System.exit(0);
        }
    }

    private class Item21 extends JMenuItem implements ItemAction {
        public Item21(String str) {
            super(str);
        }
        @Override
        public void action() {
            StringBuilder msg = new StringBuilder();
            msg.append("mine.\n\n");
            msg.append("Zhu Linfeng, from Huzzhong University,China\n");
            msg.append("contact with me : 1453065649@qq.com\n");
            msg.append("                                    2013-3-25");
            JOptionPane.showMessageDialog(null, msg);
        }
    }
}
