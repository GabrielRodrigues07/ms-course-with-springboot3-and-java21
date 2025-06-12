package br.com.hrpayroll.model.dto;

import java.math.BigDecimal;

public record Worker(Long id, String name, BigDecimal dailyIncome) {
}
