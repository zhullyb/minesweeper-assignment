package src;

/**
 * 工具类，储存游戏中的常量，并读入游戏素材
 */

import java.awt.*;

public class GameUtil {
    static int MINE_NUM = 10;
    // 缩放比例，需要使用整数
    static int RATE = 1;
    static int MAP_W = 9;
    static int MAP_H = 9;
    // 边框宽度(偏移量)
    static int OFFSET = 20 * RATE;
    static int SQUARE_LENGTH = 25 * RATE;
    // 标题栏高度，需要在程序图像第一次绘制后获取
    static int titleBarHeight = 0;

    static int FLAG_NUM = 0;

    // 鼠标的绝对位置
    static int MOUSE_X;
    static int MOUSE_Y;
    // 鼠标的左右键状态
    static boolean LEFT_CLICK = false;
    static boolean RIGHT_CLICK = false;
    // 鼠标是否按压，用于制作长按效果
    static boolean pressing = false;

    // 游戏状态
    // -1 未开始
    // 0 游戏中
    // 1 胜利
    // 2 失败
    static int state = -1;

    // 游戏开始时间（unix时间戳，秒）
    static long START_TIME;
    // 游戏开始后所用的时间（秒）
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
