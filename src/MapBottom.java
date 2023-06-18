package src;

// 底层地图
// 负责绘制游戏底部的图像

import java.awt.*;

public class MapBottom {

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

    }
}
