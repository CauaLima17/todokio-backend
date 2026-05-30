package io.com.github.caualima17.todokio.controller;

import io.com.github.caualima17.todokio.dto.subtask.SubtaskRequestDTO;
import io.com.github.caualima17.todokio.dto.subtask.SubtaskResponseDTO;
import io.com.github.caualima17.todokio.service.SubtaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subtask")
public class SubtaskController {
    private final SubtaskService subtaskService;

    @Autowired
    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SubtaskResponseDTO>> getAll() {
        return ResponseEntity.ok(subtaskService.getAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<SubtaskResponseDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(subtaskService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@Valid @RequestBody SubtaskRequestDTO data) {
        subtaskService.create(data);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody SubtaskRequestDTO data) {
        subtaskService.update(id, data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        subtaskService.delete(id);
        return ResponseEntity.ok().build();
    }
}
