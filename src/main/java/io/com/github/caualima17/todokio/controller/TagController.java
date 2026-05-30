package io.com.github.caualima17.todokio.controller;

import io.com.github.caualima17.todokio.dto.tag.TagRequestDTO;
import io.com.github.caualima17.todokio.dto.tag.TagResponseDTO;
import io.com.github.caualima17.todokio.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TagResponseDTO>> getAll() {
        return ResponseEntity.ok(tagService.getAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<TagResponseDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tagService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@Valid @RequestBody TagRequestDTO data) {
        tagService.create(data);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody TagRequestDTO data) {
        tagService.update(id, data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        tagService.delete(id);
        return ResponseEntity.ok().build();
    }
}
