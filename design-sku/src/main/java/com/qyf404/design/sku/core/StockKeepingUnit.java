package com.qyf404.design.sku.core;

import java.util.Set;

public class StockKeepingUnit {
    private Long id;
    private String code;
    private String name;
    private Goods goods;
    private String description;
    private Set<AttributeValue> attributeValues;
}
