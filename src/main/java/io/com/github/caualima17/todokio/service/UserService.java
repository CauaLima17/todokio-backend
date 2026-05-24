package io.com.github.caualima17.todokio.service;

import io.com.github.caualima17.todokio.model.User;
import io.com.github.caualima17.todokio.repository.UserRepository;
import io.com.github.caualima17.todokio.dto.user.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAll() {
        try {
            return userRepository.findAll()
                    .stream()
                    .map((UserDTO::fromEntityToDTO))
                    .toList();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao listar usuários: " + e);
        }
    }

    public UserDTO getById(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar esse usuário."));
            return UserDTO.fromEntityToDTO(user);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao buscar usuário: " + e);
        }
    }

    public void update(Long id, UserDTO data) {
        try {
            BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
            User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar esse usuário."));

            data.setPassword(crypt.encode(data.getPassword()));
            BeanUtils.copyProperties(data, user, "id");
            user.onUpdate();

            userRepository.save(user);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao atualizar o usuário: " + e);
        }
    }

    public void delete(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar esse usuário."));
            user.onDelete();

            userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao remover o usuário: " + e);
        }
    }
}
