package com.alf.phoneduck.repo;

import com.alf.phoneduck.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMessageRepository extends JpaRepository<Message, Long> {
}
