package com.shokkin.Shokkin;

import com.shokkin.Shokkin.DTO.EventDTO;
import com.shokkin.Shokkin.Service.EventService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class EventAllureJUnitTests {

    @Autowired
    private EventService eventService;
    private static final Logger logger = LogManager.getLogger(EventAllureJUnitTests.class);

    // Autowire other dependencies like EventRepository if needed...

    @DisplayName("Testing creation of event DTO, saving it and reading it from DB.")
    @Test
    public void eventWithSteps() {
        logger.info("Beginning of testing Event DTO.");
        EventDTO eventToSave = createEvent();
        EventDTO savedEvent = saveEventToDB(eventToSave);
        EventDTO retrievedEvent = getEventById(savedEvent.getId());
        assertMatchingEvents(retrievedEvent);

    }
    @DisplayName("Create a DTO event")
    public EventDTO createEvent() {
        logger.info("Testing creating a new DTO Event.");
        EventDTO eventToSave = new EventDTO();
        eventToSave.setName("Test Event");
        eventToSave.setDate("2024-01-01");
        return eventToSave;
    }

    @DisplayName("Save the event")
    public EventDTO saveEventToDB(EventDTO eventToSave){
        logger.info("STesting saving created DTO to DB.");
        return eventService.saveEvent(eventToSave);
    }

    @DisplayName("Get the event by ID from the DB.")
    public EventDTO getEventById(Long eventId){
        logger.info("Testing reading Event from the DB by the ID");
        return eventService.getEventById(eventId);
    }
    @DisplayName("Display that the saved event has correct parameters.")
    public void assertMatchingEvents(EventDTO retrievedEvent){
        logger.info("Testing assertion that right parameters were saved to DB.");
        // Assert that the retrieved event matches the saved event
        assertEquals("Test Event", retrievedEvent.getName());
        assertEquals("2024-01-01", retrievedEvent.getDate());
    }
}