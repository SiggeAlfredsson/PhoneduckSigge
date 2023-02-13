package com.alf.phoneduck.repo;

import com.alf.phoneduck.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaChannelRepository extends JpaRepository<Channel, Long> {
}
