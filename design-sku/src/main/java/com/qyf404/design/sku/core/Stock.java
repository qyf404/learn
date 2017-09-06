package com.qyf404.design.sku.core;

public class Stock {
    private Long id;
    private Warehouse warehouse;
    private Product product;
    private Long number;
    /**
     * Optimistic lock
     */
    private Long version;
}
