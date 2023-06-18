package src;

// 底层地图
// 负责绘制游戏底部的图像

import java.awt.*;

public class MapBottom {

    // 绘制方法
    void paintSelf(Graphics g){
        for(int i = 0; i < 500; i+=50){
            g.setColor(Color.GRAY);
            g.drawLine(0, i, 500, i);
            g.drawLine(i, 0, i, 500);
        }
    }
}
