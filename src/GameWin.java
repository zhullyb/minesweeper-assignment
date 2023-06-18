package src;

import javax.swing.*;
import java.awt.*;

public class GameWin extends JFrame {

    int width = GameUtil.OFFSET * 2 + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH;
    int height = GameUtil.OFFSET * 4 + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH;

    Image offScreenImage = null;

    MapBottom mapBottom = new MapBottom();
    MapTop mapTop = new MapTop();

    void launch() {
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setTitle("扫雷");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

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
