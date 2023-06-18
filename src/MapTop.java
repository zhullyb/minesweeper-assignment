package src;

import java.awt.*;

public class MapTop {
    static int temp_x,temp_y;

    void reGame(){
        for(int i=1; i <= GameUtil.MAP_W; i++){
            for(int j=1; j <= GameUtil.MAP_H; j++){
                GameUtil.DATA_TOP[i][j] = 0;
            }
        }
    }
    void logic(){
        temp_x = 0;
        temp_y = 0;
        if( GameUtil.state <= 0 && GameUtil.MOUSE_X > GameUtil.OFFSET && GameUtil.MOUSE_Y > GameUtil.OFFSET * 5) {
            temp_x = (GameUtil.MOUSE_X - GameUtil.OFFSET) / GameUtil.SQUARE_LENGTH + 1;
            temp_y = (GameUtil.MOUSE_Y - 5 * GameUtil.OFFSET) / GameUtil.SQUARE_LENGTH + 1;
        }

        if(temp_x >= 1 && temp_x <= GameUtil.MAP_W && temp_y >=1 && temp_y <= GameUtil.MAP_H){
            if( !GameUtil.pressing && GameUtil.LEFT_CLICK && GameUtil.DATA_TOP[temp_x][temp_y] == 0){
                if (GameUtil.state == -1){
                    GameUtil.state = 0;
                    GameUtil.START_TIME = System.currentTimeMillis() / 1000;
                }
                GameUtil.DATA_TOP[temp_x][temp_y] = -1;
                spaceOpen(temp_x,temp_y);
                GameUtil.LEFT_CLICK = false;
            }
            if( !GameUtil.pressing && GameUtil.RIGHT_CLICK ){
                if(GameUtil.DATA_TOP[temp_x][temp_y] == 0) {
                    GameUtil.DATA_TOP[temp_x][temp_y] = 1;
                    GameUtil.FLAG_NUM++;
                } else if(GameUtil.DATA_TOP[temp_x][temp_y] == 1) {
                    GameUtil.DATA_TOP[temp_x][temp_y] = 0;
                    GameUtil.FLAG_NUM--;
                } else if(GameUtil.DATA_TOP[temp_x][temp_y] == -1) {
                    numOpen(temp_x,temp_y);
                }
                GameUtil.RIGHT_CLICK = false;
            }
            if( GameUtil.pressing && GameUtil.DATA_TOP[temp_x][temp_y] == -1) {
                for (int i = temp_x - 1; i <= temp_x + 1; i++) {
                    for (int j = temp_y - 1; j <= temp_y + 1; j++) {
                        if (GameUtil.DATA_TOP[i][j] == 0) {
                            GameUtil.DATA_TOP[i][j] = -2;
                        }
                    }
                }
            } else {
                for (int i = temp_x - 1; i <= temp_x + 1; i++) {
                    for (int j = temp_y - 1; j <= temp_y + 1; j++) {
                        if (GameUtil.DATA_TOP[i][j] == -2) {
                            GameUtil.DATA_TOP[i][j] = 0;
                        }
                    }
                }
            }
        }
        boom();
        victory();
    }

    boolean boom(){
        if(GameUtil.FLAG_NUM == GameUtil.MINE_NUM){
            for (int i = 1; i <= GameUtil.MAP_W; i++){
                for (int j = 1; j <= GameUtil.MAP_H; j++){
                    if(GameUtil.DATA_TOP[i][j] == 0){
                        GameUtil.DATA_TOP[i][j] = -1;
                    }
                }
            }
        }
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                if(GameUtil.DATA_BOTTOM[i][j] == -1 && GameUtil.DATA_TOP[i][j] == -1){
                    GameUtil.state = 2;
                    seeBoom();
                    GameUtil.DATA_BOTTOM[temp_x][temp_y] = -2;
                    return true;
                }
            }
        }
        return false;
    }

    void seeBoom(){
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                if (GameUtil.DATA_BOTTOM[i][j] == -1 && GameUtil.DATA_TOP[i][j] != 1){
                    GameUtil.DATA_TOP[i][j] = -1;
                }
                if (GameUtil.DATA_BOTTOM[i][j] != -1 && GameUtil.DATA_TOP[i][j] == 1){
                    GameUtil.DATA_TOP[i][j] = 2;
                }
            }
        }
    }

    boolean victory(){
        int count = 0;
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                if(GameUtil.DATA_TOP[i][j] != -1){
                    count++;
                }
            }
        }
        if(count == GameUtil.MINE_NUM){
            for(int i=1; i <= GameUtil.MAP_W; i++){
                for(int j=1; j <= GameUtil.MAP_H; j++){
                    if(GameUtil.DATA_TOP[i][j] == 0){
                        GameUtil.DATA_TOP[i][j] = 1;
                    }
                }
            }
            GameUtil.state = 1;
            return true;
        }
        return false;
    }
    void spaceOpen(int x, int y){
        if (GameUtil.DATA_BOTTOM[x][y] == 0){
            for(int i = x-1; i <= x+1; i++){
                for(int j = y-1; j <= y+1; j++){
                    if(GameUtil.DATA_TOP[i][j] != -1){
                        if(GameUtil.DATA_TOP[i][j] == 1){
                            GameUtil.FLAG_NUM--;
                        }
                        GameUtil.DATA_TOP[i][j] = -1;
                        if(i >= 1 && j >=1 && i <= GameUtil.MAP_W && j <= GameUtil.MAP_H) {
                            spaceOpen(i, j);
                        }
                    }
                }
            }
        }
    }

    void numOpen(int x, int y){
        int count = 0;
        if(GameUtil.DATA_BOTTOM[x][y] > 0){
            for(int i = x-1; i <= x+1; i++){
                for(int j = y-1; j <= y+1; j++){
                    if(GameUtil.DATA_TOP[i][j] == 1){
                        count++;
                    }
                }
            }
            if (count == GameUtil.DATA_BOTTOM[x][y]){
                for(int i = x-1; i <= x+1; i++){
                    for(int j = y-1; j <= y+1; j++){
                        if(GameUtil.DATA_TOP[i][j] != 1){
                            GameUtil.DATA_TOP[i][j] = -1;
                            if(i >= 1 && j >=1 && i <= GameUtil.MAP_W && j <= GameUtil.MAP_H) {
                                spaceOpen(i, j);
                            }
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
                if(GameUtil.DATA_TOP[i][j] == -2) {
                    g.drawImage(GameUtil.pressed,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            5 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                } else if(GameUtil.DATA_TOP[i][j] == 0) {
                    g.drawImage(GameUtil.closed,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            5 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                } else if(GameUtil.DATA_TOP[i][j] == 1) {
                    g.drawImage(GameUtil.flag,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            5 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                } else if(GameUtil.DATA_TOP[i][j] == 2) {
                    g.drawImage(GameUtil.mine_wrong,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            5 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                }
            }
        }

        if (GameUtil.pressing && GameUtil.MOUSE_X >= GameUtil.OFFSET && GameUtil.MOUSE_X <= GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH && GameUtil.MOUSE_Y >= 5 * GameUtil.OFFSET && GameUtil.MOUSE_Y <= 5 * GameUtil.OFFSET + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH){
            g.drawImage(GameUtil.pressed,
                    GameUtil.OFFSET + (GameUtil.MOUSE_X - GameUtil.OFFSET) / GameUtil.SQUARE_LENGTH * GameUtil.SQUARE_LENGTH,
                    5 * GameUtil.OFFSET + (GameUtil.MOUSE_Y - 5 * GameUtil.OFFSET) / GameUtil.SQUARE_LENGTH * GameUtil.SQUARE_LENGTH,
                    GameUtil.SQUARE_LENGTH,
                    GameUtil.SQUARE_LENGTH,
                    null);
        }
    }
}
