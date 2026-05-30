package io.com.github.caualima17.todokio.service;

import io.com.github.caualima17.todokio.mapper.TaskMapper;
import io.com.github.caualima17.todokio.model.Subtask;
import io.com.github.caualima17.todokio.model.Tag;
import io.com.github.caualima17.todokio.model.Task;
import io.com.github.caualima17.todokio.model.TaskList;
import io.com.github.caualima17.todokio.repository.SubtaskRepository;
import io.com.github.caualima17.todokio.repository.TagRepository;
import io.com.github.caualima17.todokio.repository.TaskListRepository;
import io.com.github.caualima17.todokio.repository.TaskRepository;
import io.com.github.caualima17.todokio.dto.task.TaskRequestDTO;
import io.com.github.caualima17.todokio.dto.task.TaskResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;
    private final TagRepository tagRepository;
    private final SubtaskRepository subtaskRepository;

    public List<TaskResponseDTO> getAll() {
        try {
            return taskRepository.findAll()
                    .stream()
                    .map((taskMapper::toResponse))
                    .toList();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao listar tarefas: " + e);
        }
    }

    public TaskResponseDTO getById(Long id) {
        try {
            Task task = taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar essa tarefa."));
            return taskMapper.toResponse(task);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao buscar tarefa: " + e);
        }
    }

    public void create(TaskRequestDTO data) {
        try {
            taskRepository.findByName(data.getName()).ifPresent(task -> {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma tarefa registrada com esse nome. Tente novamente com um novo nome ou atualize a tarefa existente.");
            });

            Task task = buildTask(data);
            task.onCreate();
            taskRepository.save(task);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao criar tarefa: " + e);
        }
    }

    public void update(Long id, TaskRequestDTO data) {
        try {
            Task target = taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar essa tarefa."));
            Task source = buildTask(data);

            BeanUtils.copyProperties(source, target, "id", "creationDate");

            target.onUpdate();
            taskRepository.save(target);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao atualizar a tarefa: " + e);
        }
    }

    public void delete(Long id) {
        try {
            Task task = taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar essa tarefa."));
            task.onDelete();

            taskRepository.save(task);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao remover a tarefa: " + e);
        }
    }

    private Task buildTask(TaskRequestDTO data) {
        TaskList taskList = data.getListID() != null
                ? taskListRepository.findById(data.getListID())
                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "A coleção de tarefas a qual essa tarefa está associada não existe."))
                : null;

        List<Tag> tags = data.getTagsID().isEmpty()
                ? Collections.emptyList()
                : tagRepository.findAllById(data.getTagsID());

        List<Subtask> subtasks = data.getSubtasksID().isEmpty()
                ? Collections.emptyList()
                : subtaskRepository.findAllById(data.getSubtasksID());

        return taskMapper.toEntity(data, taskList, tags, subtasks);
    }
}
