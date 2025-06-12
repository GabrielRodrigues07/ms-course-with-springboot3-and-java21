package br.com.hrpayroll.model.dto;

import java.math.BigDecimal;

public record Payment(String name, BigDecimal dailyIncome, Integer days) {

    public BigDecimal getTotal() {
        return dailyIncome.multiply(BigDecimal.valueOf(days));
    };
}
