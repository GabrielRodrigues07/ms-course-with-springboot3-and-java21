package br.com.hrpayroll.service;

import br.com.hrpayroll.model.dto.Payment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    public Payment getPayments(Long workerId, Integer days) {
        return new Payment("Bob", BigDecimal.valueOf(200.0), days);
    }
}
