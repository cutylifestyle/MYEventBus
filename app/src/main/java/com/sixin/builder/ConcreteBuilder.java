package com.sixin.builder;

/**
 * @author 周文涛
 */

public class ConcreteBuilder implements Builder {

    private Product product;

    @Override
    public void buildPartOne() {

    }

    @Override
    public void buildPartTwo() {

    }

    @Override
    public Product getProduct() {
        return product;
    }
}
