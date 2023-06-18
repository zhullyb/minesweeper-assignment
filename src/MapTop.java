package src;

import java.awt.*;

public class MapTop {
    int temp_x,temp_y;
    void logic(){
        temp_x = 0;
        temp_y = 0;
        if(GameUtil.MOUSE_X > GameUtil.OFFSET && GameUtil.MOUSE_Y > GameUtil.OFFSET * 3) {
            temp_x = (GameUtil.MOUSE_X - GameUtil.OFFSET) / GameUtil.SQUARE_LENGTH + 1;
            temp_y = (GameUtil.MOUSE_Y - 3 * GameUtil.OFFSET) / GameUtil.SQUARE_LENGTH + 1;
        }

        if(temp_x >= 1 && temp_x <= GameUtil.MAP_W && temp_y >=1 && temp_y <= GameUtil.MAP_H){
            if(GameUtil.LEFT_CLICK && GameUtil.DATA_TOP[temp_x][temp_y] == 0){
                GameUtil.DATA_TOP[temp_x][temp_y] = -1;
                spaceOpen(temp_x,temp_y);
                GameUtil.LEFT_CLICK = false;
            }
            if(GameUtil.RIGHT_CLICK){
                GameUtil.DATA_TOP[temp_x][temp_y] = 1;
                GameUtil.RIGHT_CLICK = false;
            }
        }
    }

    void spaceOpen(int x, int y){
        if (GameUtil.DATA_BOTTOM[x][y] == 0){
            for(int i = x-1; i <= x+1; i++){
                for(int j = y-1; j <= y+1; j++){
                    if(GameUtil.DATA_TOP[i][j] != -1){
                        GameUtil.DATA_TOP[i][j] = -1;
                        if(i >= 1 && j >=1 && i <= GameUtil.MAP_W && j <= GameUtil.MAP_H) {
                            spaceOpen(i, j);
                        }
                    }
                }
            }
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
