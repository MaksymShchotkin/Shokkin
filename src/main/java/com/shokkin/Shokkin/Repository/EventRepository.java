package com.shokkin.Shokkin.Repository;

import com.shokkin.Shokkin.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
//extend JpaRep for event management in database
