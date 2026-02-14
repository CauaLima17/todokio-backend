package io.com.github.caualima17.todokio.repository;

import io.com.github.caualima17.todokio.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
