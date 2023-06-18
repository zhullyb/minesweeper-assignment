package src;

import javax.swing.*;
import java.awt.*;

public class GameWin extends JFrame {

    void launch() {
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("扫雷");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
