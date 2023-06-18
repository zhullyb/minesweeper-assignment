package src;

import java.awt.*;

public class MapTop {
    void logic(){
        if(GameUtil.LEFT_CLICK){
            System.out.println(GameUtil.MOUSE_X + " " + GameUtil.MOUSE_Y);
            GameUtil.LEFT_CLICK = false;
        }
        if(GameUtil.RIGHT_CLICK){
            System.out.println(GameUtil.MOUSE_X + " " + GameUtil.MOUSE_Y);
            GameUtil.RIGHT_CLICK = false;
        }
    }
    void paintSelf(Graphics g){
        logic();
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                if(GameUtil.DATA_TOP[i][j] == 0) {
                    g.drawImage(GameUtil.closed,
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
                if(GameUtil.DATA_TOP[i][j] == 1) {
                    g.drawImage(GameUtil.flag,
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
                if(GameUtil.DATA_TOP[i][j] == 2) {
                    g.drawImage(GameUtil.mine_wrong,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            3 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                }
            }
        }
    }
}
