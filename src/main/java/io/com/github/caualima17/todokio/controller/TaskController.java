package io.com.github.caualima17.todokio.controller;

import io.com.github.caualima17.todokio.service.TaskService;
import io.com.github.caualima17.todokio.transfer.TaskRequestDTO;
import io.com.github.caualima17.todokio.transfer.TaskResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TaskResponseDTO>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<TaskResponseDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@Valid @RequestBody TaskRequestDTO data) {
        taskService.create(data);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody TaskRequestDTO data) {
        taskService.update(id, data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }
}
