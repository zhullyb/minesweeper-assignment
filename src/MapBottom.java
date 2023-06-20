package src;

/* 底层类
 * 负责绘制游戏底部的图像
 * 包括地雷、数字、空白方块、笑脸、计时器、剩余地雷以及边框
 */

import java.awt.*;

public class MapBottom {
    // 初始化
    MineBottom mineBottom = new MineBottom();
    NumberBottom numberBottom = new NumberBottom();
    {
        // 生成底层数据数组
        GameUtil.generate_DATA_BOTTOM();
        mineBottom.newMine();
        numberBottom.newNumber();
    }

    // 重新开始游戏时的底部地图初始化
    void reGame(){
        for(int i=1; i <= GameUtil.MAP_W; i++){
            for(int j=1; j <= GameUtil.MAP_H; j++){
                GameUtil.DATA_BOTTOM[i][j] = 0;
                GameUtil.DATA_TOP[i][j] = 0;
            }
        }
        mineBottom.newMine();
        numberBottom.newNumber();
    }

    // 绘制方法
    void paintSelf(Graphics g){
        // 底部地雷、数字、空白方块的绘制
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                if(GameUtil.DATA_BOTTOM[i][j] == -1) {
                    // 地雷
                    g.drawImage(GameUtil.mine,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            5 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                } else if (GameUtil.DATA_BOTTOM[i][j] == -2) {
                    // 被踩到的地雷
                    g.drawImage(GameUtil.mine_red,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            5 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                } else if(GameUtil.DATA_BOTTOM[i][j] >= 0) {
                    // 数字
                    g.drawImage(GameUtil.min_num[GameUtil.DATA_BOTTOM[i][j]],
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            5 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                }
            }
        }

        // 顶部剩余雷数的绘制
        // 底板
        g.drawImage(GameUtil.nums_background,
                (int)( GameUtil.OFFSET * 1.5 ),
                (int)( GameUtil.OFFSET * 1.5 ),
                (int) (1.0 * 173 / 108 * GameUtil.OFFSET * 2),
                GameUtil.OFFSET * 2,
                null);
        // 第一位数字
        g.drawImage(GameUtil.numbers[(GameUtil.MINE_NUM-GameUtil.FLAG_NUM) / 100],
                (int)( GameUtil.OFFSET * 1.5 ) + 3 * GameUtil.RATE,
                (int)( GameUtil.OFFSET * 1.5 ) + 4 * GameUtil.RATE,
                (int) (1.0*52/99 * GameUtil.OFFSET * 2) - 4 * GameUtil.RATE,
                GameUtil.OFFSET * 2 - 8 * GameUtil.RATE,
                null);
        // 第二位数字
        g.drawImage(GameUtil.numbers[(GameUtil.MINE_NUM-GameUtil.FLAG_NUM) % 100 / 10],
                (int)( GameUtil.OFFSET * 1.5 ) + 2 * GameUtil.RATE + (int) (1.0*52/99 * GameUtil.OFFSET * 2),
                (int)( GameUtil.OFFSET * 1.5 ) + 4 * GameUtil.RATE,
                (int) (1.0*52/99 * GameUtil.OFFSET * 2) - 4 * GameUtil.RATE,
                GameUtil.OFFSET * 2 - 8 * GameUtil.RATE,
                null);
        // 第三位数字
        g.drawImage(GameUtil.numbers[(GameUtil.MINE_NUM-GameUtil.FLAG_NUM) % 10],
                (int)( GameUtil.OFFSET * 1.5 ) + 2 * GameUtil.RATE + (int) (1.0*52/99 * GameUtil.OFFSET * 4),
                (int)( GameUtil.OFFSET * 1.5 ) + 4 * GameUtil.RATE,
                (int) (1.0*52/99 * GameUtil.OFFSET * 2) - 4 * GameUtil.RATE,
                GameUtil.OFFSET * 2 - 8 * GameUtil.RATE,
                null);

        // 顶部计时器的绘制
        // 底板
        g.drawImage(GameUtil.nums_background,
                GameUtil.OFFSET * 2 + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH - (int) (1.0 * 173 / 108 * GameUtil.OFFSET * 2) - (int)( GameUtil.OFFSET * 1.5 ),
                (int)( GameUtil.OFFSET * 1.5 ),
                (int) (1.0 * 173 / 108 * GameUtil.OFFSET * 2),
                GameUtil.OFFSET * 2,
                null);
        // 第一位数字
        g.drawImage(GameUtil.numbers[GameUtil.USED_TIME / 100],
                GameUtil.OFFSET * 2 + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH - (int) (1.0 * 173 / 108 * GameUtil.OFFSET * 2) - (int)( GameUtil.OFFSET * 1.5 ) + 3 * GameUtil.RATE,
                (int)( GameUtil.OFFSET * 1.5 ) + 4 * GameUtil.RATE,
                (int) (1.0*52/99 * GameUtil.OFFSET * 2) - 4 * GameUtil.RATE,
                GameUtil.OFFSET * 2 - 8 * GameUtil.RATE,
                null);
        // 第二位数字
        g.drawImage(GameUtil.numbers[GameUtil.USED_TIME % 100 / 10],
                GameUtil.OFFSET * 2 + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH - (int) (1.0 * 173 / 108 * GameUtil.OFFSET * 2) - (int)( GameUtil.OFFSET * 1.5 ) + 2 * GameUtil.RATE + (int) (1.0*52/99 * GameUtil.OFFSET * 2),
                (int)( GameUtil.OFFSET * 1.5 ) + 4 * GameUtil.RATE,
                (int) (1.0*52/99 * GameUtil.OFFSET * 2) - 4 * GameUtil.RATE,
                GameUtil.OFFSET * 2 - 8 * GameUtil.RATE,
                null);
        // 第三位数字
        g.drawImage(GameUtil.numbers[GameUtil.USED_TIME % 10],
                GameUtil.OFFSET * 2 + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH - (int) (1.0 * 173 / 108 * GameUtil.OFFSET * 2) - (int)( GameUtil.OFFSET * 1.5 ) + 2 * GameUtil.RATE + (int) (1.0*52/99 * GameUtil.OFFSET * 4),
                (int)( GameUtil.OFFSET * 1.5 ) + 4 * GameUtil.RATE,
                (int) (1.0*52/99 * GameUtil.OFFSET * 2) - 4 * GameUtil.RATE,
                GameUtil.OFFSET * 2 - 8 * GameUtil.RATE,
                null);

        // 边框绘制
        // 左上角
        g.drawImage(GameUtil.corner_up_left_2x,
                0,
                0,
                GameUtil.OFFSET,
                GameUtil.OFFSET,
                null);
        // 右上角
        g.drawImage(GameUtil.corner_up_right_2x,
                GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                0,
                GameUtil.OFFSET,
                GameUtil.OFFSET,
                null);
        // 左下角
        g.drawImage(GameUtil.corner_bottom_left_2x,
                0,
                GameUtil.OFFSET * 5 + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH,
                GameUtil.OFFSET,
                GameUtil.OFFSET,
                null);
        // 右下角
        g.drawImage(GameUtil.corner_bottom_right_2x,
                GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                GameUtil.OFFSET * 5 + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH,
                GameUtil.OFFSET,
                GameUtil.OFFSET,
                null);
        // 左侧中部T型区域
        g.drawImage(GameUtil.t_left_2x,
                0,
                GameUtil.OFFSET * 4,
                GameUtil.OFFSET,
                GameUtil.OFFSET,
                null);
        // 右侧中部T型区域
        g.drawImage(GameUtil.t_right_2x,
                GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                GameUtil.OFFSET * 4,
                GameUtil.OFFSET,
                GameUtil.OFFSET,
                null);
        // 上侧横条
        g.drawImage(GameUtil.border_hor_2x,
                GameUtil.OFFSET,
                0,
                GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                GameUtil.OFFSET,
                null);
        // 中部横条
        g.drawImage(GameUtil.border_hor_2x,
                GameUtil.OFFSET,
                GameUtil.OFFSET * 4,
                GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                GameUtil.OFFSET,
                null);
        // 下侧横条
        g.drawImage(GameUtil.border_hor_2x,
                GameUtil.OFFSET,
                GameUtil.OFFSET * 5 + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH,
                GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                GameUtil.OFFSET,
                null);
        // 左上竖条
        g.drawImage(GameUtil.border_vert_2x,
                0,
                GameUtil.OFFSET,
                GameUtil.OFFSET,
                GameUtil.OFFSET * 3,
                null);
        // 右上竖条
        g.drawImage(GameUtil.border_vert_2x,
                GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                GameUtil.OFFSET,
                GameUtil.OFFSET,
                GameUtil.OFFSET * 3,
                null);
        // 左下竖条
        g.drawImage(GameUtil.border_vert_2x,
                0,
                GameUtil.OFFSET * 5,
                GameUtil.OFFSET,
                GameUtil.MAP_H * GameUtil.SQUARE_LENGTH,
                null);
        // 右下竖条
        g.drawImage(GameUtil.border_vert_2x,
                GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                GameUtil.OFFSET * 5,
                GameUtil.OFFSET,
                GameUtil.MAP_H * GameUtil.SQUARE_LENGTH,
                null);

        switch (GameUtil.state){
            case -1:
                // 游戏未开始，用时始终为0
                GameUtil.USED_TIME = 0;
                if(GameUtil.pressing){
                    if (GameUtil.MOUSE_X > (int)(GameUtil.MAP_W * GameUtil.SQUARE_LENGTH/2.0) &&
                            GameUtil.MOUSE_X < (int)(GameUtil.MAP_W * GameUtil.SQUARE_LENGTH/2.0) + 2 * GameUtil.OFFSET &&
                            GameUtil.MOUSE_Y > (int) ( GameUtil.OFFSET * 1.5 ) &&
                            GameUtil.MOUSE_Y < (int) ( GameUtil.OFFSET * 1.5 ) + 2 * GameUtil.OFFSET) {
                        // 鼠标按下且在笑脸上，笑脸变为按下状态
                        g.drawImage(GameUtil.face_pressed,
                                (int) (GameUtil.MAP_W * GameUtil.SQUARE_LENGTH / 2.0),
                                (int) (GameUtil.OFFSET * 1.5),
                                GameUtil.OFFSET * 2,
                                GameUtil.OFFSET * 2,
                                null);
                    } else {
                        // 鼠标按下但不在笑脸上，笑脸嘴部变为o型，展示帧动画
                        g.drawImage(GameUtil.face_active,
                                (int)(GameUtil.MAP_W * GameUtil.SQUARE_LENGTH/2.0),
                                (int) ( GameUtil.OFFSET * 1.5 ),
                                GameUtil.OFFSET * 2,
                                GameUtil.OFFSET * 2,
                                null);
                    }
                } else {
                    // 鼠标未按下，笑脸为正常状态
                    g.drawImage(GameUtil.face_unpressed,
                            (int) (GameUtil.MAP_W * GameUtil.SQUARE_LENGTH / 2.0),
                            (int) (GameUtil.OFFSET * 1.5),
                            GameUtil.OFFSET * 2,
                            GameUtil.OFFSET * 2,
                            null);
                }
                break;
            case 0:
                // 游戏进行中，用时为当前时间减去开始时间
                GameUtil.USED_TIME = (int) (
                        System.currentTimeMillis() / 1000 - GameUtil.START_TIME < 1000 ?
                                System.currentTimeMillis() / 1000 - GameUtil.START_TIME :
                                999);
                // 此处的代码和 case -1 一样
                if(GameUtil.pressing){
                    if (GameUtil.MOUSE_X > (int)(GameUtil.MAP_W * GameUtil.SQUARE_LENGTH/2.0) &&
                            GameUtil.MOUSE_X < (int)(GameUtil.MAP_W * GameUtil.SQUARE_LENGTH/2.0) + 2 * GameUtil.OFFSET &&
                            GameUtil.MOUSE_Y > (int) ( GameUtil.OFFSET * 1.5 ) &&
                            GameUtil.MOUSE_Y < (int) ( GameUtil.OFFSET * 1.5 ) + 2 * GameUtil.OFFSET) {
                        g.drawImage(GameUtil.face_pressed,
                                (int) (GameUtil.MAP_W * GameUtil.SQUARE_LENGTH / 2.0),
                                (int) (GameUtil.OFFSET * 1.5),
                                GameUtil.OFFSET * 2,
                                GameUtil.OFFSET * 2,
                                null);
                    } else {
                        g.drawImage(GameUtil.face_active,
                                (int)(GameUtil.MAP_W * GameUtil.SQUARE_LENGTH/2.0),
                                (int) ( GameUtil.OFFSET * 1.5 ),
                                GameUtil.OFFSET * 2,
                                GameUtil.OFFSET * 2,
                                null);
                    }
                } else {
                    g.drawImage(GameUtil.face_unpressed,
                            (int) (GameUtil.MAP_W * GameUtil.SQUARE_LENGTH / 2.0),
                            (int) (GameUtil.OFFSET * 1.5),
                            GameUtil.OFFSET * 2,
                            GameUtil.OFFSET * 2,
                            null);
                }
                break;
            case 1:
                // 游戏胜利，展示胜利笑脸和按压帧笑脸
                if(GameUtil.pressing &&
                        GameUtil.MOUSE_X > (int)(GameUtil.MAP_W * GameUtil.SQUARE_LENGTH/2.0) &&
                        GameUtil.MOUSE_X < (int)(GameUtil.MAP_W * GameUtil.SQUARE_LENGTH/2.0) + 2 * GameUtil.OFFSET &&
                        GameUtil.MOUSE_Y > (int) ( GameUtil.OFFSET * 1.5 ) &&
                        GameUtil.MOUSE_Y < (int) ( GameUtil.OFFSET * 1.5 ) + 2 * GameUtil.OFFSET) {
                    g.drawImage(GameUtil.face_pressed,
                                (int) (GameUtil.MAP_W * GameUtil.SQUARE_LENGTH / 2.0),
                                (int) (GameUtil.OFFSET * 1.5),
                                GameUtil.OFFSET * 2,
                                GameUtil.OFFSET * 2,
                                null);
                } else {
                    g.drawImage(GameUtil.face_win,
                            (int) (GameUtil.MAP_W * GameUtil.SQUARE_LENGTH / 2.0),
                            (int) (GameUtil.OFFSET * 1.5),
                            GameUtil.OFFSET * 2,
                            GameUtil.OFFSET * 2,
                            null);
                }
                break;
            case 2:
                // 游戏失败，展示失败笑脸和按压帧笑脸
                if(GameUtil.pressing &&
                        GameUtil.MOUSE_X > (int)(GameUtil.MAP_W * GameUtil.SQUARE_LENGTH/2.0) &&
                        GameUtil.MOUSE_X < (int)(GameUtil.MAP_W * GameUtil.SQUARE_LENGTH/2.0) + 2 * GameUtil.OFFSET &&
                        GameUtil.MOUSE_Y > (int) ( GameUtil.OFFSET * 1.5 ) &&
                        GameUtil.MOUSE_Y < (int) ( GameUtil.OFFSET * 1.5 ) + 2 * GameUtil.OFFSET) {
                    g.drawImage(GameUtil.face_pressed,
                            (int) (GameUtil.MAP_W * GameUtil.SQUARE_LENGTH / 2.0),
                            (int) (GameUtil.OFFSET * 1.5),
                            GameUtil.OFFSET * 2,
                            GameUtil.OFFSET * 2,
                            null);
                } else {
                    g.drawImage(GameUtil.face_lose,
                            (int) (GameUtil.MAP_W * GameUtil.SQUARE_LENGTH / 2.0),
                            (int) (GameUtil.OFFSET * 1.5),
                            GameUtil.OFFSET * 2,
                            GameUtil.OFFSET * 2,
                            null);
                }
                break;
        }
    }
}
