package src;

import javax.swing.*;
import java.awt.*;

public class GameWin extends JFrame {

    MapBottom mapBottom = new MapBottom();

    void launch() {
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("扫雷");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        mapBottom.paintSelf(g);
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
