package zlc.season.demo;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/22
 * Time: 10:01
 * FIXME
 */
public enum RecyclerItemType {
    NORMAL(0), TEST1(1), TEST2(2), TEST3(3);

    // 定义私有变量
    private int nCode;

    RecyclerItemType(int _nCode) {
        this.nCode = _nCode;
    }

    public int getValue() {
        return this.nCode;
    }
}

