package com.gyp.springboot;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 10:41 - 2021/2/19
 */
public class Menu {

    private String name;
    private String code;
    private String icons;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", icons='" + icons + '\'' +
                '}';
    }
}
