package com.geekbang.game.draw;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// 1、定义成画图框架
public class Drawacircle extends JFrame{
    private MyPanel mp = null;
    public static void main(String[] args) {
        new Drawacircle();
    }

    public Drawacircle() {
        // 2、初始化面板，并放入框架中
        mp = new MyPanel();
        this.add(mp);
        // 3、设置窗口大小
        this.setSize(400, 300);
        // 4、关闭窗口时，自动退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class MyPanel extends JPanel {
    // paint调用时机：1、窗口最大化、最小化、大小发生变化 2、repaint函数被调用时
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(13,13, 100, 100);

//        g.drawLine(20, 20, 100, 200);
//        g.drawRect(10,10,100,100);
//        g.setColor(Color.blue);
//        g.fillRect(10,10,100,200);
//        g.fillOval(30, 40, 50,80);
//        Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/copy.png"));
//        g.drawImage(image, 10, 10, 175, 200, this);
        g.setColor(Color.red);
        g.setFont(new Font("隶书", Font.BOLD, 50));
        g.drawString("北京你好", 60, 60);
    }
}
