package biz.resource.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import biz.resource.dto.ProductSpec;
import biz.resource.dto.ProductSpecGroup;

/**
 * 资源规则排序
 * 
 * @author tanson lam
 * @creation 2016年1月5日
 */
public class ProductSpecComparatorUtil {

    /**
     * 排序
     * 
     * @param groupList
     */
    public static void sortResGroupList(List<ProductSpecGroup> groupList) {
        ProductSpecGroupComparator groupComparator = new ProductSpecGroupComparator();
        ResSpecComparator resSpecComparator = new ResSpecComparator();
        Collections.sort(groupList, groupComparator);
        for (ProductSpecGroup group : groupList) {
            Collections.sort(group.getChildSpec(), resSpecComparator);
        }
    }

    static public class ProductSpecGroupComparator implements Comparator<ProductSpecGroup> {

        @Override
        public int compare(ProductSpecGroup o1, ProductSpecGroup o2) {

            int diff = o1.getRootSpec().getRank() - o2.getRootSpec().getRank();
            if (diff > 0)
                return 1;
            if (diff < 0)
                return -1;
            else
                return 0;
        }

    }

    static public class ResSpecComparator implements Comparator<ProductSpec> {

        @Override
        public int compare(ProductSpec o1, ProductSpec o2) {

            int diff = o1.getRank() - o2.getRank();
            if (diff > 0)
                return 1;
            if (diff < 0)
                return -1;
            else
                return 0;
        }

    }
}
