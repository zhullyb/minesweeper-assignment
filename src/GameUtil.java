package src;

import java.awt.*;

public class GameUtil {
    static int MINE_NUM = 10;
    static int RATE = 1;
    static int MAP_W = 9;
    static int MAP_H = 9;
    static int OFFSET = 45 * RATE;
    static int SQUARE_LENGTH = 50 * RATE;

    static int MOUSE_X;
    static int MOUSE_Y;
    static boolean LEFT_CLICK = false;
    static boolean RIGHT_CLICK = false;

    // 游戏状态
    // 0 游戏中
    // 1 胜利
    // 2 失败
    static int state = 0;

    // 底层元素
    // -1 代表雷
    // -2 代表踩雷
    // 0 代表空
    // 1-8 代表周围雷的数量
    static int[][] DATA_BOTTOM = new int[MAP_W+2][MAP_H+2];

    // 顶部元素
    // -1 已翻开
    // 0 未翻开
    // 1 插旗
    // 2 插错旗
    static int[][] DATA_TOP = new int[MAP_W+2][MAP_H+2];

    // 载入图片
    static Image mine = Toolkit.getDefaultToolkit().getImage("resources/mine.png");
    static Image closed = Toolkit.getDefaultToolkit().getImage("resources/closed.png");
    static Image flag = Toolkit.getDefaultToolkit().getImage("resources/flag.png");
    static Image mine_wrong = Toolkit.getDefaultToolkit().getImage("resources/mine_wrong.png");

    static Image face_unpressed = Toolkit.getDefaultToolkit().getImage("resources/face_unpressed.png");
    static Image face_lose = Toolkit.getDefaultToolkit().getImage("resources/face_lose.png");
    static Image face_win = Toolkit.getDefaultToolkit().getImage("resources/face_win.png");
    static Image[] numbers = new Image[9];
    static {
        for(int i = 0; i < 9; i++){
            numbers[i] = Toolkit.getDefaultToolkit().getImage("resources/type" + i + ".png");
        }
    }

}
