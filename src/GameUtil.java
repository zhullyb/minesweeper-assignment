package src;

import java.awt.*;

public class GameUtil {
    static int MINE_NUM = 10;
    static int RATE = 1;
    static int MAP_W = 9;
    static int MAP_H = 9;
    static int OFFSET = 45 * RATE;
    static int SQUARE_LENGTH = 50 * RATE;

    // 底层元素
    // -1 代表雷
    // 0 代表空
    // 1-8 代表周围雷的数量
    static int[][] DATA_BOTTOM = new int[MAP_W+2][MAP_H+2];

    // 载入图片
    static Image mine = Toolkit.getDefaultToolkit().getImage("resources/mine.png");

}
