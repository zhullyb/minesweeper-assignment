package in.zhul.minesweeper;

/*
 * 生成地雷
 */

public class MineBottom {
    // 新建一个数组，用于存储雷的坐标
    int[] mines = new int[GameUtil.MINE_NUM * 2];
    int x,y;

    // 随机生成地雷
    void newMine() {
        for(int i = 0; i < GameUtil.MINE_NUM; i++){
            x = (int) (Math.random() * GameUtil.MAP_W + 1);
            y = (int) (Math.random() * GameUtil.MAP_H + 1);
            mines[i*2] = x;
            mines[i*2+1] = y;
            // 如果生成的地雷坐标与已有的雷坐标重复，则重新生成
            for(int j = 0; j < i; j++){
                if(mines[j*2] == x && mines[j*2+1] == y){
                    i--;
                    break;
                }
            }
        }
        //将地雷坐标导入到底部数据中
        for(int i=0; i < GameUtil.MINE_NUM; i++){
            GameUtil.DATA_BOTTOM[mines[i*2]][mines[i*2+1]] = -1;
        }
    }
}
