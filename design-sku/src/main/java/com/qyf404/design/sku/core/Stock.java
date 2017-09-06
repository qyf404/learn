package com.qyf404.design.sku.core;

public class Stock {
    private Long id;
    private Warehouse warehouse;
    private StockKeepingUnit stockKeepingUnit;
    private Long number;
    /**
     * Optimistic lock
     */
    private Long version;
}
