package src;

/* 顶部类
 * 绘制顶部覆盖方格
 * 做成功失败判定
 */

import java.awt.*;

public class MapTop {
    {
        // 生成顶部数据数组
        GameUtil.generate_DATA_TOP();
    }

    // 临时变量，用于计算鼠标在哪个方格上
    static int temp_x,temp_y;

    // 重新开始游戏，清空顶部数据数组
    void reGame(){
        for(int i=1; i <= GameUtil.MAP_W; i++){
            for(int j=1; j <= GameUtil.MAP_H; j++){
                GameUtil.DATA_TOP[i][j] = 0;
            }
        }
    }

    // 每次图形重绘时调用的逻辑判断
    void logic(){
        // 计算鼠标在哪个方格上
        temp_x = 0;
        temp_y = 0;
        if( GameUtil.state <= 0 && GameUtil.MOUSE_X > GameUtil.OFFSET && GameUtil.MOUSE_Y > GameUtil.OFFSET * 5) {
            temp_x = (GameUtil.MOUSE_X - GameUtil.OFFSET) / GameUtil.SQUARE_LENGTH + 1;
            temp_y = (GameUtil.MOUSE_Y - 5 * GameUtil.OFFSET - GameUtil.titleBarHeight ) / GameUtil.SQUARE_LENGTH + 1;
        }

        // 鼠标左键按下时，如果鼠标在方格内，且该方格未翻开，则翻开该方格
        if(temp_x >= 1 && temp_x <= GameUtil.MAP_W && temp_y >=1 && temp_y <= GameUtil.MAP_H){
            if( !GameUtil.pressing && GameUtil.LEFT_CLICK && GameUtil.DATA_TOP[temp_x][temp_y] == 0){
                if (GameUtil.state == -1){
                    // 此时游戏开始，记录游戏开始时间
                    GameUtil.state = 0;
                    GameUtil.START_TIME = System.currentTimeMillis() / 1000;
                }
                // 翻开该方格，调用spaceOpen方法递归翻开周围方格
                GameUtil.DATA_TOP[temp_x][temp_y] = -1;
                spaceOpen(temp_x,temp_y);
            }
            // 鼠标右键按下时的处理逻辑
            if( !GameUtil.pressing && GameUtil.RIGHT_CLICK ){
                // 如果该方格未翻开，则插旗
                if(GameUtil.DATA_TOP[temp_x][temp_y] == 0) {
                    GameUtil.DATA_TOP[temp_x][temp_y] = 1;
                    GameUtil.FLAG_NUM++;
                // 如果该方格已插旗，则取消插旗
                } else if(GameUtil.DATA_TOP[temp_x][temp_y] == 1) {
                    GameUtil.DATA_TOP[temp_x][temp_y] = 0;
                    GameUtil.FLAG_NUM--;
                // 如果该方格已翻开，则计算周围方格的插旗数量，如果与该方格的数字相同，则翻开周围方格
                } else if(GameUtil.DATA_TOP[temp_x][temp_y] == -1) {
                    numOpen(temp_x,temp_y);
                }
            }
            // 鼠标按在数字上时，给周围的格子绘制被按压的效果动画
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
        // 释放鼠标左右键的状态
        GameUtil.LEFT_CLICK = false;
        GameUtil.RIGHT_CLICK = false;
        // 判断游戏是否胜利或失败
        boom();
        victory();
    }

    // 失败判定
    boolean boom(){
        // 当插旗数量等于地雷数量时，直接翻开所有未翻开的方格
        if(GameUtil.FLAG_NUM == GameUtil.MINE_NUM){
            for (int i = 1; i <= GameUtil.MAP_W; i++){
                for (int j = 1; j <= GameUtil.MAP_H; j++){
                    if(GameUtil.DATA_TOP[i][j] == 0){
                        GameUtil.DATA_TOP[i][j] = -1;
                    }
                }
            }
        }
        // 遍历所有方格，如果有被翻开的方格，且该方格为地雷，则游戏失败
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

    // 游戏失败后，将所有地雷翻开
    void seeBoom(){
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                // 失败后，将所有地雷的位置翻开
                if (GameUtil.DATA_BOTTOM[i][j] == -1 && GameUtil.DATA_TOP[i][j] != 1){
                    GameUtil.DATA_TOP[i][j] = -1;
                }
                // 展示错误插旗的位置
                if (GameUtil.DATA_BOTTOM[i][j] != -1 && GameUtil.DATA_TOP[i][j] == 1){
                    GameUtil.DATA_TOP[i][j] = 2;
                }
            }
        }
    }

    // 胜利判定
    boolean victory(){
        // 计算未翻开的方格数量，包括插旗和未插旗的
        int count = 0;
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                if(GameUtil.DATA_TOP[i][j] != -1){
                    count++;
                }
            }
        }
        // 当未翻开的方格数量等于地雷数量时，游戏胜利
        if(count == GameUtil.MINE_NUM){
            for(int i=1; i <= GameUtil.MAP_W; i++){
                for(int j=1; j <= GameUtil.MAP_H; j++){
                    if(GameUtil.DATA_TOP[i][j] == 0){
                        GameUtil.DATA_TOP[i][j] = 1;
                        GameUtil.FLAG_NUM++;
                    }
                }
            }
            GameUtil.state = 1;
            return true;
        }
        return false;
    }
    // 递归打开空白方格周围一个九宫格范围内的所有空白方格
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
    // 计算数字周围方格的插旗数量，如果与该方格的数字相同，则翻开周围方格
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
    // 顶部图像的绘制逻辑
    void paintSelf(Graphics g){
        // 调用游戏判断逻辑
        logic();

        // 游戏顶部方格的规制
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                // 绘制方格被按压时的帧动画
                if(GameUtil.DATA_TOP[i][j] == -2) {
                    g.drawImage(GameUtil.pressed,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            5 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                // 绘制未翻开方格
                } else if(GameUtil.DATA_TOP[i][j] == 0) {
                    g.drawImage(GameUtil.closed,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            5 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                // 绘制插旗方格
                } else if(GameUtil.DATA_TOP[i][j] == 1) {
                    g.drawImage(GameUtil.flag,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH,
                            5 * GameUtil.OFFSET + (j - 1) * GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            GameUtil.SQUARE_LENGTH,
                            null);
                // 绘制插错旗的方格
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

        // 绘制方块被按压时的帧动画
        if (GameUtil.pressing && temp_x >= 1 && temp_y >= 1 && temp_x <= GameUtil.MAP_W && temp_y <= GameUtil.MAP_H && GameUtil.DATA_TOP[temp_x][temp_y] == 0){
            g.drawImage(GameUtil.pressed,
                    GameUtil.OFFSET + (temp_x - 1) * GameUtil.SQUARE_LENGTH,
                    5 * GameUtil.OFFSET + (temp_y - 1) * GameUtil.SQUARE_LENGTH,
                    GameUtil.SQUARE_LENGTH,
                    GameUtil.SQUARE_LENGTH,
                    null);
        }
    }
}
