package com.sixin.builder;

/**
 * Created by 周文涛 on 2017/8/10.
 */

public class Director {

    private Builder builder;

    public Director(Builder builder){
        this.builder = builder;
    }

    public void buildProduct(){
        builder.buildPartOne();
        builder.buildPartTwo();
    }

    public Product getProduct(){
        return builder.getProduct();
    }

}
