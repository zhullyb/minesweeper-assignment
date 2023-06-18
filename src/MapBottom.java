package src;

// 底层地图
// 负责绘制游戏底部的图像

import java.awt.*;

public class MapBottom {

    MineBottom mineBottom = new MineBottom();
    NumberBottom numberBottom = new NumberBottom();

    // 绘制方法
    void paintSelf(Graphics g){
        g.setColor(Color.GRAY);
        // 绘制方格的竖线
        for(int i = 0;i <= GameUtil.MAP_W;i++) {
            g.drawLine(GameUtil.OFFSET + i * GameUtil.SQUARE_LENGTH,
                    3 * GameUtil.OFFSET,
                    GameUtil.OFFSET + i * GameUtil.SQUARE_LENGTH,
                    3 * GameUtil.OFFSET + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH);
        }
        // 绘制方格的横线
        for(int i = 0;i <= GameUtil.MAP_H;i++) {
            g.drawLine(GameUtil.OFFSET,
                    3 * GameUtil.OFFSET + i * GameUtil.SQUARE_LENGTH,
                    GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH,
                    3 * GameUtil.OFFSET + i * GameUtil.SQUARE_LENGTH);
        }

        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                if(GameUtil.DATA_BOTTOM[i][j] == -1) {
                    g.drawImage(GameUtil.mine,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            3 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                }
            }
        }

        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                if(GameUtil.DATA_BOTTOM[i][j] >= 0) {
                    g.drawImage(GameUtil.numbers[GameUtil.DATA_BOTTOM[i][j]],
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            3 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                }
            }
        }

        switch (GameUtil.state){
            case 0:
                g.drawImage(GameUtil.face_unpressed,
                        GameUtil.OFFSET + (GameUtil.MAP_W / 2 ) * GameUtil.SQUARE_LENGTH,
                        GameUtil.OFFSET,
                        GameUtil.OFFSET,
                        GameUtil.OFFSET,
                        null);
                break;
            case 1:
                    g.drawImage(GameUtil.face_win,
                            GameUtil.OFFSET + (GameUtil.MAP_W / 2 ) * GameUtil.SQUARE_LENGTH,
                            GameUtil.OFFSET,
                            GameUtil.OFFSET,
                            GameUtil.OFFSET,
                            null);
                break;
            case 2:
                    g.drawImage(GameUtil.face_lose,
                            GameUtil.OFFSET + (GameUtil.MAP_W / 2 ) * GameUtil.SQUARE_LENGTH,
                            GameUtil.OFFSET,
                            GameUtil.OFFSET,
                            GameUtil.OFFSET,
                            null);
        }
    }
}
