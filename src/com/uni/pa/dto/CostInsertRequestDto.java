package com.uni.pa.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CostInsertRequestDto extends BaseDto {

    @NotNull(message = "Goods is required.")
    @NotBlank(message = "Goods is required.")
    private String goods;

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

}
