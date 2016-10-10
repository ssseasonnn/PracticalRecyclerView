package zlc.season.demo;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/22
 * Time: 10:01
 * FIXME
 */
public enum RecyclerItemType {
    NORMAL(0), TYPE1(1), TYPE2(2), TYPE3(3);

    // 定义私有变量
    private int nCode;

    RecyclerItemType(int _nCode) {
        this.nCode = _nCode;
    }

    public int getValue() {
        return this.nCode;
    }
}

