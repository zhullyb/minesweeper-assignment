package src;

import java.awt.*;

public class GameUtil {
    static int MINE_NUM = 10;
    static int RATE = 2;
    static int MAP_W = 9;
    static int MAP_H = 9;
    static int OFFSET = 20 * RATE;
    static int SQUARE_LENGTH = 25 * RATE;
    static int titleBarHeight = 0;

    static int FLAG_NUM = 0;

    static int MOUSE_X;
    static int MOUSE_Y;
    static boolean LEFT_CLICK = false;
    static boolean RIGHT_CLICK = false;
    static boolean pressing = false;

    // 游戏状态
    // -1 未开始
    // 0 游戏中
    // 1 胜利
    // 2 失败
    static int state = -1;

    static long START_TIME;
    static int USED_TIME;

    // 底层元素
    // -1 代表雷
    // -2 代表踩雷
    // 0 代表空
    // 1-8 代表周围雷的数量
    static int[][] DATA_BOTTOM;
    static void generate_DATA_BOTTOM(){
        DATA_BOTTOM = new int[MAP_W+2][MAP_H+2];
    }

    // 顶部元素
    // -2 按压中
    // -1 已翻开
    // 0 未翻开
    // 1 插旗
    // 2 插错旗
    static int[][] DATA_TOP;
    static void generate_DATA_TOP(){
        DATA_TOP = new int[MAP_W+2][MAP_H+2];
    }

    // 载入图片
    static Image mine = Toolkit.getDefaultToolkit().getImage("resources/mine.png");
    static Image closed = Toolkit.getDefaultToolkit().getImage("resources/closed.png");
    static Image pressed = Toolkit.getDefaultToolkit().getImage("resources/pressed.png");
    static Image flag = Toolkit.getDefaultToolkit().getImage("resources/flag.png");
    static Image mine_wrong = Toolkit.getDefaultToolkit().getImage("resources/mine_wrong.png");
    static Image mine_red = Toolkit.getDefaultToolkit().getImage("resources/mine_red.png");

    static Image face_unpressed = Toolkit.getDefaultToolkit().getImage("resources/face_unpressed.png");
    static Image face_lose = Toolkit.getDefaultToolkit().getImage("resources/face_lose.png");
    static Image face_win = Toolkit.getDefaultToolkit().getImage("resources/face_win.png");
    static Image face_active = Toolkit.getDefaultToolkit().getImage("resources/face_active.png");
    static Image face_pressed = Toolkit.getDefaultToolkit().getImage("resources/face_pressed.png");
    static Image nums_background = Toolkit.getDefaultToolkit().getImage("resources/nums_background.png");
    static Image border_hor_2x = Toolkit.getDefaultToolkit().getImage("resources/border_hor_2x.png");
    static Image border_vert_2x = Toolkit.getDefaultToolkit().getImage("resources/border_vert_2x.png");
    static Image corner_bottom_left_2x = Toolkit.getDefaultToolkit().getImage("resources/corner_bottom_left_2x.png");
    static Image corner_bottom_right_2x = Toolkit.getDefaultToolkit().getImage("resources/corner_bottom_right_2x.png");
    static Image corner_up_left_2x = Toolkit.getDefaultToolkit().getImage("resources/corner_up_left_2x.png");
    static Image corner_up_right_2x = Toolkit.getDefaultToolkit().getImage("resources/corner_up_right_2x.png");
    static Image t_left_2x = Toolkit.getDefaultToolkit().getImage("resources/t_left_2x.png");
    static Image t_right_2x = Toolkit.getDefaultToolkit().getImage("resources/t_right_2x.png");
    static Image[] min_num = new Image[9];
    static {
        for(int i = 0; i < 9; i++){
            min_num[i] = Toolkit.getDefaultToolkit().getImage("resources/type" + i + ".png");
        }
    }
    static Image[] numbers = new Image[10];
    static {
        for (int i = 0; i < 10; i++){
            numbers[i] = Toolkit.getDefaultToolkit().getImage("resources/d" + i + ".png");
        }
    }

}
