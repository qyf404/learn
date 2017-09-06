package com.qyf404.design.sku;

import java.util.Set;

public class Goods {
    private Long id;
    private String name;
    private String description;
    private Set<StockKeepingUnit> stockKeepingUnits;
}
