package br.com.hrworker.controller;

import br.com.hrworker.model.entity.WorkerEntity;
import br.com.hrworker.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/workers")
@RequiredArgsConstructor
public class WorkerController {


    private final Environment env;

    @Value("${test.config}")
    private String testConfig;

    private final Logger logger = LoggerFactory.getLogger(WorkerController.class);

    private final WorkerRepository workerRepository;

    @GetMapping
    public ResponseEntity<List<WorkerEntity>> findAll() {
        return ResponseEntity.ok().body(workerRepository.findAll());
    }

    @GetMapping(value = "/configs")
    public ResponseEntity<List<WorkerEntity>> getConfigs() {
        logger.info("CONFIG: " + testConfig);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkerEntity> findById(@PathVariable Long id) {
//        try {
//            Thread.sleep(3000L);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        String port = env.getProperty("local.server.port");
        logger.info("PORT: " + port);
        return ResponseEntity.ok().body(workerRepository.findById(id).get());
    }
}
