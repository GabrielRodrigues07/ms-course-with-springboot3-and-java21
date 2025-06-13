package br.com.hrpayroll.feign;

import br.com.hrpayroll.model.dto.Worker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "hr-worker", url = "localhost:8001", path = "/workers")
public interface WorkerFeignClient {

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    ResponseEntity<Worker> findById(@PathVariable Long id);
}
