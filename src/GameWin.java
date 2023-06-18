package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {

    int width = GameUtil.OFFSET * 2 + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH;
    int height = GameUtil.OFFSET * 6 + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH;

    Image offScreenImage = null;

    MapBottom mapBottom = new MapBottom();
    MapTop mapTop = new MapTop();

    void launch() {
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setTitle("扫雷");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        // 鼠标事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == 1 || e.getButton() == 3) {
                    GameUtil.MOUSE_X = e.getX();
                    GameUtil.MOUSE_Y = e.getY();
                    GameUtil.pressing = true;
                }
            }

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
                        if(e.getButton() == 1){
                            if (e.getX() > (int)(GameUtil.MAP_W * GameUtil.SQUARE_LENGTH/2.0) &&
                                    e.getX() < (int)(GameUtil.MAP_W * GameUtil.SQUARE_LENGTH/2.0) + 2 * GameUtil.OFFSET &&
                                    e.getY() > (int) ( GameUtil.OFFSET * 1.5 ) &&
                                    e.getY() < (int) ( GameUtil.OFFSET * 1.5 ) + 2 * GameUtil.OFFSET){
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
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        offScreenImage = this.createImage(width, height);
        Graphics gImage = offScreenImage.getGraphics();
        gImage.setColor(new Color(0xC0C0C0));
        gImage.fillRect(0, 0, width, height);
        mapBottom.paintSelf(gImage);
        mapTop.paintSelf(gImage);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
