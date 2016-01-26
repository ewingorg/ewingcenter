package jdbc;

import jws.dal.annotation.Column;
import jws.dal.common.DbType;

/**
 * @author tanson lam 2014年9月2日
 *
 */
public class Count {
    @Column(name = "count", type = DbType.Int)
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
