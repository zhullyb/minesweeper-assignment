package src;

public class MineBottom {
    int[] mines = new int[GameUtil.MINE_NUM * 2];
    int x,y;
    boolean isPlace = true;

    void newMine() {
        for(int i = 0; i < GameUtil.MINE_NUM; i++){
            x = (int) (Math.random() * GameUtil.MAP_W + 1);
            y = (int) (Math.random() * GameUtil.MAP_H + 1);
            mines[i*2] = x;
            mines[i*2+1] = y;

            for(int j = 0; j < i; j++){
                if(mines[j*2] == x && mines[j*2+1] == y){
                    i--;
                    break;
                }
            }
        }

        for(int i=0; i < GameUtil.MINE_NUM; i++){
            GameUtil.DATA_BOTTOM[mines[i*2]][mines[i*2+1]] = -1;
        }
    }
}
