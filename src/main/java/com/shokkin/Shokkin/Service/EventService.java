package com.shokkin.Shokkin.Service;

import com.shokkin.Shokkin.DTO.EventDTO;
import com.shokkin.Shokkin.Entities.Event;
import com.shokkin.Shokkin.Mapper.EventMapper;
import com.shokkin.Shokkin.Repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
public class EventService {
    private static final Logger logger = LogManager.getLogger(EventService.class);
    public final EventRepository eventRepository;
    private final EventMapper eventMapper;
    //initiate rep and mapper for functionality

    public EventService(EventRepository eventRepository, EventMapper eventMapper){
        logger.info("Initialising Event Mapper and Repository!");
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }
    //save or update existing event
    public EventDTO saveEvent(EventDTO eventDTO) {
        logger.info("Saving and Event DTO into DB!");
        Event event = eventMapper.toEntity(eventDTO);
        event = eventRepository.save(event);
        return eventMapper.toDTO(event);
    }
    //check if event exists -> delete it
    public void deleteEvent(Long eventId) {
        logger.info("Checking for the event in DB and deleting it afterwards.");
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        eventRepository.delete(existingEvent);
    }
    //check if event exists -> get it by id
    public EventDTO getEventById(Long eventId) {
        logger.info("Checking if event exists and reading it.");
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        return eventMapper.toDTO(event);
    }
}
