package com.qyf404.design.sku;

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
