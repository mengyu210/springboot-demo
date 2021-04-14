package com.gyp.springboot.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author guoyapeng
 * @create 2020/8/23 11:34 AM
 * excel表头信息
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ExcelHeaderInfo {

    /**
     * 标题的首行坐标
     */
    private int firstRow;
    /**
     * 标题的末行坐标
     */
    private int lastRow;
    /**
     * 标题的首行坐标
     */
    private int firstCol;
    /**
     * 标题的首行坐标
     */
    private int lastCol;
    /**
     * 标题
     */
    private String title;

}
