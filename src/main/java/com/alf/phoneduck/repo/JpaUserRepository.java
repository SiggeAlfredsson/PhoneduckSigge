package com.alf.phoneduck.repo;

import com.alf.phoneduck.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {
}
