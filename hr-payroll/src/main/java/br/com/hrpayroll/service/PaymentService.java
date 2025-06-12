package br.com.hrpayroll.service;

import br.com.hrpayroll.model.dto.Payment;
import br.com.hrpayroll.model.dto.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentService {

    @Value("${hr-worker.host}")
    private String hrWorkerHost;

    private final RestTemplate restTemplate;

    public Payment getPayments(Long workerId, Integer days) {
        Worker worker = restTemplate.getForObject(String.format("%s/workers/%s", hrWorkerHost, workerId), Worker.class);

        assert worker != null;

        return new Payment(worker.name(), worker.dailyIncome(), days);
    }
}
