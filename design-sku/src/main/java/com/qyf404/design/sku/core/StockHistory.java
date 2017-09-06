package com.qyf404.design.sku.core;

import java.util.Date;

public class StockHistory {
    public enum Direction {
        INCREASE, DECREASE
    }

    private Date createTime;
    private Stock stock;
    private String description;
    private Direction direction;
    private Long numberOfChange;
    private Long afterNumber;
}
