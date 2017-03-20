package com.aracy.load.more.bean;

/**
 * @author Sun.bl
 * @version [1.0, 2016/8/29]
 */
public class Item {

    public int id;

    public String content() {
        return "第 " + (id + 1) + " 项Item";
    }


    public Item() {
        super();
    }

    public Item(int id) {
        this.id = id;
    }


}
