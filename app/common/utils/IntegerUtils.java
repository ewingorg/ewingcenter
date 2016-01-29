/*
 * Copyright (c) 2004-2015 by UCweb All rights reserved
 */
package common.utils;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * <p/>
 * <br>=
 * ============================== <br>
 * 公司：优视科技-安卓应用发行中心 <br>
 * 系统：UC应用商店-开放平台 <br>
 * 开发：Joeson <br>
 * 创建时间：2014年9月23日 下午12:07:31 <br>=
 * ==============================
 */
public class IntegerUtils {
    
    /***
     * 是否是整数
     * @param value
     * @author Joeson
     */
    public static boolean isInt(String value){
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        
        try {
            Integer.parseInt(value);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * 转为基本类型
     *
     * @param i
     * @return int
     */
    public static int toInt(Integer i) {
        return null == i ? 0 : i;
    }

    /**
     * nullOrZero
     *
     * @param i
     * @return boolean
     */
    public static boolean nullOrZero(Integer i) {
        if (null == i || i <= 0)
            return true;
        return false;
    }

    /**
     * 比较相等
     *
     * @param a
     * @param b
     * @return boolean
     */
    public static boolean equals(Integer a, Integer b) {
        return a == null ? b == null : a.equals(b);
    }

    /**
     * isEmpty
     *
     * @param i
     * @return boolean
     */
    public static boolean ltZero(Integer i) {
        if (null == i || i < 0)
            return true;
        return false;
    }

    /**
     * isEmpty
     *
     * @param i
     * @return boolean
     */
    public static boolean ltOne(Integer i) {
        if (null == i || i < 1)
            return true;
        return false;
    }

    /**
     * isNotEmpty
     *
     * @param i
     * @return boolean
     */
    public static boolean gtZero(Integer i) {
        if (null != i && i > 0)
            return true;
        return false;
    }

    /**
     * 创建一个数字序列
     * 
     * @param min 开始数字
     * @param size 多少个数字
     * @return 数组
     */
    public static List<Integer> range(int min, int size) {
        List<Integer> list = new LinkedList();

        for (int i = min, max = (min + size - 1); i <= max; i++) {
            list.add(i);
        }

        return list;
    }

    /**
     * 转化int
     */
    public static int parseInt(String value) {
        if (StringUtils.isEmpty(value)) {
            return 0;
        }
        Integer result = null;

        try {
            result = Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }

        return result;
    }

    public static String toString(Integer value) {
        return null == value ? StringUtils.EMPTY : value.toString();
    }

    /**
     * <pre>
     * null < notNull         true
     * null < null            true
     * notNull < null         false
     * small < big            true
     * </pre>
     * @param evalDate
     * @param parseInt
     * @return
     * @author Joeson
     */
    public static boolean lt(Integer value1, Integer value2) {
        if(null == value1){
            return true;
        }
        if(null == value2){
            return false;
        }
        
        return value1 < value2;
    }
    
    /**
     * <pre>
     * null > null            true
     * notNull > null         true
     * null > notNull         false
     * big > small         true
     * </pre>
     * @param evalDate
     * @param parseInt
     * @return
     * @author Joeson
     */
    public static boolean gt(Integer value1, Integer value2) {
        if(null == value2){
            return true;
        }
        if(null == value1){
            return false;
        }
        
        return value1 > value2;
    }
}
