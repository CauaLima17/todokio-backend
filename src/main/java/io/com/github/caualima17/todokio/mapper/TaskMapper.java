package io.com.github.caualima17.todokio.mapper;

import io.com.github.caualima17.todokio.dto.subtask.SubtaskResponseDTO;
import io.com.github.caualima17.todokio.dto.subtask.SubtaskSimpleDTO;
import io.com.github.caualima17.todokio.dto.task.TaskRequestDTO;
import io.com.github.caualima17.todokio.dto.task.TaskResponseDTO;
import io.com.github.caualima17.todokio.dto.task.TaskSimpleDTO;
import io.com.github.caualima17.todokio.model.Subtask;
import io.com.github.caualima17.todokio.model.Tag;
import io.com.github.caualima17.todokio.model.Task;
import io.com.github.caualima17.todokio.model.TaskList;
import io.com.github.caualima17.todokio.repository.SubtaskRepository;
import io.com.github.caualima17.todokio.repository.TagRepository;
import io.com.github.caualima17.todokio.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    private final TaskListRepository taskListRepository;
    private final TagRepository tagRepository;
    private final SubtaskRepository subtaskRepository;
    private final TagMapper tagMapper;
    private final TaskListMapper taskListMapper;
    private final SubtaskMapper subtaskMapper;

    @Autowired
    public TaskMapper(TaskListRepository taskListRepository, TagRepository tagRepository, SubtaskRepository subtaskRepository, TagMapper tagMapper, TaskListMapper taskListMapper, SubtaskMapper subtaskMapper) {
        this.taskListRepository = taskListRepository;
        this.tagRepository = tagRepository;
        this.subtaskRepository = subtaskRepository;
        this.tagMapper = tagMapper;
        this.taskListMapper = taskListMapper;
        this.subtaskMapper = subtaskMapper;
    }

    public Task fromDtoToEntity(TaskRequestDTO data) {

        TaskList taskList = data.getListID() != null
                ? taskListRepository.findById(data.getListID())
                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "A coleção de tarefas a qual essa tarefa está associada não existe."))
                : null;

        List<Tag> tags = data.getTagsID().isEmpty()
                ? null
                : tagRepository.findAllById(data.getTagsID());

        List<Subtask> subtasks = data.getSubtasksID().isEmpty()
                ? null
                : subtaskRepository.findAllById(data.getSubtasksID());

        return Task.builder()
                .name(data.getName())
                .description(data.getDescription())
                .dueTime(data.getDueTime())
                .list(taskList)
                .tags(tags)
                .subtasks(subtasks)
                .build();
    }

    public TaskResponseDTO fromEntityToDto(Task data) {
        return TaskResponseDTO.builder()
                .id(data.getId())
                .name(data.getName())
                .description(data.getDescription())
                .dueTime(data.getDueTime())
                .list(taskListMapper.fromEntityToSimpleDto(data.getList()))
                .tags(tagMapper.fromEntityToSimpleDto(data.getTags()))
                .subtasks(subtaskMapper.fromEntityToSimpleDto(data.getSubtasks()))
                .createdOn(data.getCreationDate())
                .updatedOn(data.getUpdateDate())
                .build();
    }

    public TaskSimpleDTO fromEntityToSimpleDto(Task data) {
        return TaskSimpleDTO.builder()
                .id(data.getId())
                .name(data.getName())
                .description(data.getDescription())
                .dueTime(data.getDueTime())
                .createdOn(data.getCreationDate())
                .updatedOn(data.getUpdateDate())
                .build();
    }

    public List<TaskSimpleDTO> fromEntityToSimpleDto(List<Task> tasks) {
        List<TaskSimpleDTO> simpleDTOs = new ArrayList<>();

        for (Task data : tasks) {
            simpleDTOs.add(
                    TaskSimpleDTO.builder()
                            .id(data.getId())
                            .name(data.getName())
                            .description(data.getDescription())
                            .dueTime(data.getDueTime())
                            .createdOn(data.getCreationDate())
                            .updatedOn(data.getUpdateDate())
                            .build()
            );
        }

        return simpleDTOs;
    }
}
