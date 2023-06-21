package src;

/**
 * 扫雷游戏的主要逻辑
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {

    // 计算程序的宽度和高度
    int width = GameUtil.OFFSET * 2 + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH;
    int height = GameUtil.OFFSET * 6 + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH;

    // 双缓冲
    Image offScreenImage = null;

    // 创建用于处理底部和顶部的对象
    MapBottom mapBottom = new MapBottom();
    MapTop mapTop = new MapTop();

    // 图形化的主要逻辑
    void launch() {
        this.setSize(width, height);
        // 居中
        this.setLocationRelativeTo(null);
        this.setTitle("扫雷");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        // 先设置为可见，用于获取标题栏高度
        this.setVisible(true);
        GameUtil.titleBarHeight = this.getInsets().top;
        // 再设置为不可见，用于设置窗口大小
        this.setVisible(false);
        this.setSize(width, height + GameUtil.titleBarHeight);
        this.setVisible(true);
        // 鼠标事件
        this.addMouseListener(new MouseAdapter() {

            // 鼠标按下时的事件处理，主要用于制作帧动画
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == 1 || e.getButton() == 3) {
                    GameUtil.MOUSE_X = e.getX();
                    GameUtil.MOUSE_Y = e.getY();
                    GameUtil.pressing = true;
                }
            }

            // 鼠标释放时的事件处理，是程序运行逻辑的基石
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                GameUtil.pressing = false;
                switch (GameUtil.state) {
                    case -1:
                    case 0:
                        if (e.getButton() == 1) {
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.LEFT_CLICK = true;
                        }
                        if (e.getButton() == 3) {
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.RIGHT_CLICK = true;
                        }
                    case 1:
                    case 2:
                        // 单击笑脸后的重开游戏事件处理
                        if(e.getButton() == 1){
                            if (e.getX() > (int)(GameUtil.MAP_W * GameUtil.SQUARE_LENGTH/2.0) &&
                                    e.getX() < (int)(GameUtil.MAP_W * GameUtil.SQUARE_LENGTH/2.0) + 2 * GameUtil.OFFSET &&
                                    e.getY() > (int) ( GameUtil.OFFSET * 1.5 ) + GameUtil.titleBarHeight &&
                                    e.getY() < (int) ( GameUtil.OFFSET * 1.5 ) + 2 * GameUtil.OFFSET + GameUtil.titleBarHeight) {
                                mapBottom.reGame();
                                mapTop.reGame();
                                GameUtil.FLAG_NUM = 0;
                                GameUtil.state = -1;
                            }
                        }
                }
            }
        });

        while(true){
            repaint();
            // try {
            //     Thread.sleep(40);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
        }
    }

    @Override
    public void paint(Graphics g) {
        // 双缓冲
        offScreenImage = this.createImage(width, height);
        Graphics gImage = offScreenImage.getGraphics();
        gImage.setColor(new Color(0xC0C0C0));
        gImage.fillRect(0, 0, width, height);
        mapBottom.paintSelf(gImage);
        mapTop.paintSelf(gImage);
        g.drawImage(offScreenImage, 0, GameUtil.titleBarHeight, null);
    }

    public static void main(String[] args) {
        // 从命令行读取扫雷的行数、列数、雷数以及缩放比例
        if (args.length == 4) {
            GameUtil.MAP_W = Integer.parseInt(args[0]);
            GameUtil.MAP_H = Integer.parseInt(args[1]);
            GameUtil.MINE_NUM = Integer.parseInt(args[2]);
            GameUtil.RATE = Integer.parseInt(args[3]);
        }

        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
