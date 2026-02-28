package io.com.github.caualima17.todokio.service;

import io.com.github.caualima17.todokio.model.Tag;
import io.com.github.caualima17.todokio.model.Task;
import io.com.github.caualima17.todokio.model.TaskList;
import io.com.github.caualima17.todokio.repository.TagRepository;
import io.com.github.caualima17.todokio.repository.TaskListRepository;
import io.com.github.caualima17.todokio.repository.TaskRepository;
import io.com.github.caualima17.todokio.transfer.TagRequestDTO;
import io.com.github.caualima17.todokio.transfer.TaskRequestDTO;
import io.com.github.caualima17.todokio.transfer.TaskResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private TaskListRepository taskListRepository;
    private TagRepository tagRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, TagRepository tagRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.tagRepository = tagRepository;
        this.taskListRepository = taskListRepository;
    }

    public List<TaskResponseDTO> getAll() {
        try {
            return taskRepository.findAll()
                    .stream()
                    .map((TaskResponseDTO::fromEntityToDTO))
                    .toList();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao listar tarefas: " + e);
        }
    }

    public void create(TaskRequestDTO data) {
        try {
            taskRepository.findByName(data.getName()).ifPresent(task -> {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma tarefa registrada com esse nome. Tente novamente com um novo nome ou atualize a tarefa existente.");
            });

            validateTaskList(data.getList());
            List<Tag> tags = validateTaskTags(data.getTags());

            Task task = TaskRequestDTO.fromDtoToEntity(data);
            task.setTags(tags);

            taskRepository.save(task);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao criar tarefa: " + e);
        }
    }

    public TaskResponseDTO getById(Long id) {
        try {
            Task task = taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar essa tarefa."));
            return TaskResponseDTO.fromEntityToDTO(task);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao buscar tarefa: " + e);
        }
    }

    public void update(Long id, TaskRequestDTO data) {
        try {
            Task task = taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar essa tarefa."));

            validateTaskList(data.getList());
            List<Tag> tags = validateTaskTags(data.getTags());

            BeanUtils.copyProperties(data, task, "id");
            task.setTags(tags);

            task.onUpdate();
            taskRepository.save(task);
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

    public void validateTaskList(TaskList taskList) {
        if (Objects.nonNull(taskList)) {
            taskListRepository.findById(taskList.getId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.BAD_REQUEST, "A lista de tarefas a qual essa tarefa está associada não existe ou foi deletada.")
            );
        }
    }

    public List<Tag> validateTaskTags(List<TagRequestDTO> tags) {
        List<Tag> validatedTags = new ArrayList<>();

        for (TagRequestDTO tagRequest : tags) {
            Optional<Tag> tagPersisted = tagRepository.findByName(tagRequest.getName());

            if (tagPersisted.isEmpty()) {
                Tag tagConstructed = TagRequestDTO.fromDtoToEntity(tagRequest);
                validatedTags.add(tagRepository.save(tagConstructed));
            } else {
                validatedTags.add(tagPersisted.get());
            }
        }

        return validatedTags;
    }
}
