package com.shokkin.Shokkin.Mapper;

import com.shokkin.Shokkin.DTO.EventDTO;
import com.shokkin.Shokkin.Entities.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
//map out methods for entity conversion
    public EventDTO toDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setName(event.getName());
        dto.setDate(event.getDate());
        return dto;
    }

    public Event toEntity(EventDTO dto) {
        Event event = new Event();
        event.setId(dto.getId());
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        return event;
    }
}