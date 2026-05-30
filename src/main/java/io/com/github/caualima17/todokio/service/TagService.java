package io.com.github.caualima17.todokio.service;

import io.com.github.caualima17.todokio.dto.tag.TagRequestDTO;
import io.com.github.caualima17.todokio.dto.tag.TagResponseDTO;
import io.com.github.caualima17.todokio.mapper.TagMapper;
import io.com.github.caualima17.todokio.model.Tag;
import io.com.github.caualima17.todokio.model.Task;
import io.com.github.caualima17.todokio.repository.TagRepository;
import io.com.github.caualima17.todokio.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagMapper tagMapper;
    private final TagRepository tagRepository;
    private final TaskRepository taskRepository;

    public List<TagResponseDTO> getAll() {
        try {
            return tagRepository.findAll()
                    .stream()
                    .map((tagMapper::toResponse))
                    .toList();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao listar tags: " + e);
        }
    }

    public TagResponseDTO getById(Long id) {
        try {
            Tag tag = tagRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar a tag."));
            return tagMapper.toResponse(tag);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao buscar tag: " + e);
        }
    }

    public void create(TagRequestDTO data) {
        try {
            tagRepository.findByName(data.getName()).ifPresent(task -> {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma tag registrada com esse nome. Tente novamente com um novo nome ou atualize a tag existente.");
            });

            Tag tag = buildTask(data);
            tag.onCreate();
            tagRepository.save(tag);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao criar tarefa: " + e);
        }
    }

    public void update(Long id, TagRequestDTO data) {
        try {
            Tag target = tagRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar essa tag."));
            Tag source = buildTask(data);

            BeanUtils.copyProperties(source, target, "id", "creationDate");

            target.onUpdate();
            tagRepository.save(target);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao atualizar a tag: " + e);
        }
    }

    public void delete(Long id) {
        try {
            Tag tag = tagRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar essa tarefa."));

            List<Task> tasks = tag.getTasks();
            tasks.forEach(t -> t.getTags().remove(tag));

            taskRepository.saveAll(tasks);
            tagRepository.delete(tag);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao remover a tarefa: " + e);
        }
    }

    private Tag buildTask(TagRequestDTO data) {
        List<Task> tasks = new ArrayList<>();

        if (data.getTasksID() != null) tasks = taskRepository.findAllById(data.getTasksID());

        return tagMapper.toEntity(data, tasks);
    }
}
