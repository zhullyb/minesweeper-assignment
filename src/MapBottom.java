package src;

// 底层地图
// 负责绘制游戏底部的图像

import java.awt.*;

public class MapBottom {

    MineBottom mineBottom = new MineBottom();
    NumberBottom numberBottom = new NumberBottom();
    {
        mineBottom.newMine();
        numberBottom.newNumber();
    }

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
        Graphics2D g2d = (Graphics2D) g;
        Stroke stroke = new BasicStroke(5);
        g2d.setStroke(stroke);
        g2d.setColor(Color.GRAY);
        // 绘制最后一条竖线
            g2d.drawLine(GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                    5 * GameUtil.OFFSET,
                    GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                    5 * GameUtil.OFFSET + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH);

        // 绘制最后一条横线
            g2d.drawLine(GameUtil.OFFSET,
                    5 * GameUtil.OFFSET + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH,
                    GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                    5 * GameUtil.OFFSET + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH);

        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                if(GameUtil.DATA_BOTTOM[i][j] == -1) {
                    g.drawImage(GameUtil.mine,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            5 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                } else if (GameUtil.DATA_BOTTOM[i][j] == -2) {
                    g.drawImage(GameUtil.mine_red,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            5 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                } else if(GameUtil.DATA_BOTTOM[i][j] >= 0) {
                    g.drawImage(GameUtil.min_num[GameUtil.DATA_BOTTOM[i][j]],
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            5 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                }
            }
        }

        g.drawImage(GameUtil.nums_background,
                (int)( GameUtil.OFFSET * 1.5 ),
                (int)( GameUtil.OFFSET * 1.5 ),
                (int) (1.0 * 173 / 108 * GameUtil.OFFSET * 2),
                GameUtil.OFFSET * 2,
                null);
        g.drawImage(GameUtil.numbers[(GameUtil.MINE_NUM-GameUtil.FLAG_NUM) / 100],
                (int)( GameUtil.OFFSET * 1.5 ) + 3 * GameUtil.RATE,
                (int)( GameUtil.OFFSET * 1.5 ) + 4 * GameUtil.RATE,
                (int) (1.0*52/99 * GameUtil.OFFSET * 2) - 4 * GameUtil.RATE,
                GameUtil.OFFSET * 2 - 8 * GameUtil.RATE,
                null);
        g.drawImage(GameUtil.numbers[(GameUtil.MINE_NUM-GameUtil.FLAG_NUM) % 100 / 10],
                (int)( GameUtil.OFFSET * 1.5 ) + 2 * GameUtil.RATE + (int) (1.0*52/99 * GameUtil.OFFSET * 2),
                (int)( GameUtil.OFFSET * 1.5 ) + 4 * GameUtil.RATE,
                (int) (1.0*52/99 * GameUtil.OFFSET * 2) - 4 * GameUtil.RATE,
                GameUtil.OFFSET * 2 - 8 * GameUtil.RATE,
                null);
        g.drawImage(GameUtil.numbers[(GameUtil.MINE_NUM-GameUtil.FLAG_NUM) % 10],
                (int)( GameUtil.OFFSET * 1.5 ) + 2 * GameUtil.RATE + (int) (1.0*52/99 * GameUtil.OFFSET * 4),
                (int)( GameUtil.OFFSET * 1.5 ) + 4 * GameUtil.RATE,
                (int) (1.0*52/99 * GameUtil.OFFSET * 2) - 4 * GameUtil.RATE,
                GameUtil.OFFSET * 2 - 8 * GameUtil.RATE,
                null);

        g.drawImage(GameUtil.nums_background,
                GameUtil.OFFSET * 2 + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH - (int) (1.0 * 173 / 108 * GameUtil.OFFSET * 2) - (int)( GameUtil.OFFSET * 1.5 ),
                (int)( GameUtil.OFFSET * 1.5 ),
                (int) (1.0 * 173 / 108 * GameUtil.OFFSET * 2),
                GameUtil.OFFSET * 2,
                null);
        g.drawImage(GameUtil.numbers[GameUtil.USED_TIME / 100],
                GameUtil.OFFSET * 2 + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH - (int) (1.0 * 173 / 108 * GameUtil.OFFSET * 2) - (int)( GameUtil.OFFSET * 1.5 ) + 3 * GameUtil.RATE,
                (int)( GameUtil.OFFSET * 1.5 ) + 4 * GameUtil.RATE,
                (int) (1.0*52/99 * GameUtil.OFFSET * 2) - 4 * GameUtil.RATE,
                GameUtil.OFFSET * 2 - 8 * GameUtil.RATE,
                null);
        g.drawImage(GameUtil.numbers[GameUtil.USED_TIME % 100 / 10],
                GameUtil.OFFSET * 2 + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH - (int) (1.0 * 173 / 108 * GameUtil.OFFSET * 2) - (int)( GameUtil.OFFSET * 1.5 ) + 2 * GameUtil.RATE + (int) (1.0*52/99 * GameUtil.OFFSET * 2),
                (int)( GameUtil.OFFSET * 1.5 ) + 4 * GameUtil.RATE,
                (int) (1.0*52/99 * GameUtil.OFFSET * 2) - 4 * GameUtil.RATE,
                GameUtil.OFFSET * 2 - 8 * GameUtil.RATE,
                null);
        g.drawImage(GameUtil.numbers[GameUtil.USED_TIME % 10],
                GameUtil.OFFSET * 2 + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH - (int) (1.0 * 173 / 108 * GameUtil.OFFSET * 2) - (int)( GameUtil.OFFSET * 1.5 ) + 2 * GameUtil.RATE + (int) (1.0*52/99 * GameUtil.OFFSET * 4),
                (int)( GameUtil.OFFSET * 1.5 ) + 4 * GameUtil.RATE,
                (int) (1.0*52/99 * GameUtil.OFFSET * 2) - 4 * GameUtil.RATE,
                GameUtil.OFFSET * 2 - 8 * GameUtil.RATE,
                null);

        // 边框绘制
        g.drawImage(GameUtil.corner_up_left_2x,
                0,
                GameUtil.titleBarHeight,
                GameUtil.OFFSET,
                GameUtil.OFFSET,
                null);
        g.drawImage(GameUtil.corner_up_right_2x,
                GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                GameUtil.titleBarHeight,
                GameUtil.OFFSET,
                GameUtil.OFFSET,
                null);
        g.drawImage(GameUtil.corner_bottom_left_2x,
                0,
                GameUtil.titleBarHeight + GameUtil.OFFSET * 5 + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH,
                GameUtil.OFFSET,
                GameUtil.OFFSET,
                null);
        g.drawImage(GameUtil.corner_bottom_right_2x,
                GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                GameUtil.titleBarHeight + GameUtil.OFFSET * 5 + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH,
                GameUtil.OFFSET,
                GameUtil.OFFSET,
                null);
        g.drawImage(GameUtil.t_left_2x,
                0,
                GameUtil.titleBarHeight + GameUtil.OFFSET * 4,
                GameUtil.OFFSET,
                GameUtil.OFFSET,
                null);
        g.drawImage(GameUtil.t_right_2x,
                GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                GameUtil.titleBarHeight + GameUtil.OFFSET * 4,
                GameUtil.OFFSET,
                GameUtil.OFFSET,
                null);

        g.drawImage(GameUtil.border_hor_2x,
                GameUtil.OFFSET,
                GameUtil.titleBarHeight,
                GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                GameUtil.OFFSET,
                null);
        g.drawImage(GameUtil.border_hor_2x,
                GameUtil.OFFSET,
                GameUtil.titleBarHeight + GameUtil.OFFSET * 4,
                GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                GameUtil.OFFSET,
                null);
        g.drawImage(GameUtil.border_hor_2x,
                GameUtil.OFFSET,
                GameUtil.titleBarHeight + GameUtil.OFFSET * 5 + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH,
                GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                GameUtil.OFFSET,
                null);
        g.drawImage(GameUtil.border_vert_2x,
                0,
                GameUtil.titleBarHeight + GameUtil.OFFSET,
                GameUtil.OFFSET,
                GameUtil.OFFSET * 3,
                null);
        g.drawImage(GameUtil.border_vert_2x,
                GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                GameUtil.titleBarHeight + GameUtil.OFFSET,
                GameUtil.OFFSET,
                GameUtil.OFFSET * 3,
                null);
        g.drawImage(GameUtil.border_vert_2x,
                0,
                GameUtil.titleBarHeight + GameUtil.OFFSET * 5,
                GameUtil.OFFSET,
                GameUtil.MAP_H * GameUtil.SQUARE_LENGTH,
                null);
        g.drawImage(GameUtil.border_vert_2x,
                GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                GameUtil.titleBarHeight + GameUtil.OFFSET * 5,
                GameUtil.OFFSET,
                GameUtil.MAP_H * GameUtil.SQUARE_LENGTH,
                null);

        switch (GameUtil.state){
            case -1:
                GameUtil.USED_TIME = 0;
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
                            (int) (GameUtil.OFFSET * 1.5), //TODO: 我们还没有搞清楚这一个 y 坐标使用 1.5 的效果为什么不好
                            GameUtil.OFFSET * 2,
                            GameUtil.OFFSET * 2,
                            null);
                }
                break;
            case 0:
                GameUtil.USED_TIME = (int) (
                        System.currentTimeMillis() / 1000 - GameUtil.START_TIME < 1000 ?
                                System.currentTimeMillis() / 1000 - GameUtil.START_TIME :
                                999);
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
                            (int) (GameUtil.OFFSET * 1.5), //TODO: 我们还没有搞清楚这一个 y 坐标使用 1.5 的效果为什么不好
                            GameUtil.OFFSET * 2,
                            GameUtil.OFFSET * 2,
                            null);
                }
                break;
            case 1:
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
