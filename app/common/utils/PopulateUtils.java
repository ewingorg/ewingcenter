package common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jws.dal.annotation.Column;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 通用工具类
 * 
 * @author cairf
 * 
 * @date 2014-7-15
 * 
 */
public class PopulateUtils {

    private static Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 组装字段列表
     * 
     * @param clazz 类
     * @return string contains fields names.
     */
    public static String populateFields(Class clazz) {
        return populateFields(clazz, null);
    }

    /**
     * 组装字段列表
     * 
     * @param clazz 类
     * @param excludes 排除字段
     * 
     * @return string contains fields names.
     */
    public static String populateFields(Class clazz, List<String> excludes) {

        Field[] fields = clazz.getDeclaredFields();

        if (fields == null || fields.length == 0) {
            return null;
        }

        List<String> nameList = new ArrayList<String>();

        for (Field field : fields) {
            Column c = field.getAnnotation(Column.class);
            if (c != null)
                nameList.add(clazz.getSimpleName() + "." + field.getName());
        }

        if (CollectionUtils.isNotEmpty(excludes)) {
            for (int i = 0; i < excludes.size(); i++) {
                excludes.set(i, clazz.getSimpleName() + "." + excludes.get(i));
            }
            nameList = ListUtils.removeAll(nameList, excludes);
        }

        if (CollectionUtils.isEmpty(nameList)) {
            return null;
        }

        String result = nameList.toString();
        return StringUtils.substring(result, 1, result.length() - 1);
    }

    /**
     * 判断字符串中是否包含中文
     */
    public static boolean isContainsChinese(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }

        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    /**
     * 梅花间竹地合并两个list
     * 
     * @param list1
     * @param list2
     * @return
     */
    public static <T> List<T> crossMerge(List<T> list1, List<T> list2) {
        if (list1 == null || list2 == null)
            throw new IllegalArgumentException("list can not be null");
        int cross = (list1.size() > list2.size() ? list2.size() : list1.size()) * 2;
        int size = list1.size() + list2.size();
        List<T> mergeList = new ArrayList<T>(size);
        for (int i = 0; i < size; i++) {
            if (cross > 0) {
                if (i % 2 == 0) {
                    mergeList.add(list1.get(0));
                    list1.remove(0);
                } else {
                    mergeList.add(list2.get(0));
                    list2.remove(0);
                }
                cross--;
            }
        }
        if (!list1.isEmpty())
            mergeList.addAll(list1);
        if (!list2.isEmpty())
            mergeList.addAll(list2);
        return mergeList;
    }
}
