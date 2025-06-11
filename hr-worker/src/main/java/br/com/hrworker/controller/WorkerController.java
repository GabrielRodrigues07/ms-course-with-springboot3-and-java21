package br.com.hrworker.controller;

import br.com.hrworker.model.entity.WorkerEntity;
import br.com.hrworker.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
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

    private final WorkerRepository workerRepository;

    @GetMapping
    public ResponseEntity<List<WorkerEntity>> findAll() {
        return ResponseEntity.ok().body(workerRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkerEntity> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(workerRepository.findById(id).get());
    }
}
