package br.com.hrpayroll.service;

import br.com.hrpayroll.feign.WorkerFeignClient;
import br.com.hrpayroll.model.dto.Payment;
import br.com.hrpayroll.model.dto.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final WorkerFeignClient workerFeignClient;

    public Payment getPayments(Long workerId, Integer days) {

        Worker worker = workerFeignClient.findById(workerId).getBody();

        assert worker != null;

        return new Payment(worker.name(), worker.dailyIncome(), days);
    }
}
