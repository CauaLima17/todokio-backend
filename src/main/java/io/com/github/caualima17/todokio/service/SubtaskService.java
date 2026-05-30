package io.com.github.caualima17.todokio.service;

import io.com.github.caualima17.todokio.dto.subtask.SubtaskRequestDTO;
import io.com.github.caualima17.todokio.dto.subtask.SubtaskResponseDTO;
import io.com.github.caualima17.todokio.dto.tag.TagRequestDTO;
import io.com.github.caualima17.todokio.dto.tag.TagResponseDTO;
import io.com.github.caualima17.todokio.mapper.SubtaskMapper;
import io.com.github.caualima17.todokio.mapper.TagMapper;
import io.com.github.caualima17.todokio.model.Subtask;
import io.com.github.caualima17.todokio.model.Tag;
import io.com.github.caualima17.todokio.model.Task;
import io.com.github.caualima17.todokio.repository.SubtaskRepository;
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
public class SubtaskService {
    private final SubtaskMapper subtaskMapper;
    private final SubtaskRepository subtaskRepository;
    private final TaskRepository taskRepository;

    public List<SubtaskResponseDTO> getAll() {
        try {
            return subtaskRepository.findAll()
                    .stream()
                    .map((subtaskMapper::toResponse))
                    .toList();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao listar subtarefas: " + e);
        }
    }

    public SubtaskResponseDTO getById(Long id) {
        try {
            Subtask tag = subtaskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar a subtarefa."));
            return subtaskMapper.toResponse(tag);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao buscar a subtarefa: " + e);
        }
    }

    public void create(SubtaskRequestDTO data) {
        try {
            Subtask subtask = buildTask(data);
            subtask.onCreate();
            subtaskRepository.save(subtask);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao criar subtarefa: " + e);
        }
    }

    public void update(Long id, SubtaskRequestDTO data) {
        try {
            Subtask target = subtaskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar essa subtarefa."));
            Subtask source = buildTask(data);

            BeanUtils.copyProperties(source, target, "id", "creationDate");

            target.onUpdate();
            subtaskRepository.save(target);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao atualizar a subtarefa: " + e);
        }
    }

    public void delete(Long id) {
        try {
            Subtask subtask = subtaskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar essa subtarefa."));

            Task task = subtask.getTask();
            task.getSubtasks().remove(subtask);

            taskRepository.save(task);
            subtaskRepository.delete(subtask);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao remover essa subtarefa: " + e);
        }
    }

    private Subtask buildTask(SubtaskRequestDTO data) {
        Task task = taskRepository.findById(data.getTaskID())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "A tarefa a qual essa subtarefa está associada não existe."));

        return subtaskMapper.toEntity(data, task);
    }
}
