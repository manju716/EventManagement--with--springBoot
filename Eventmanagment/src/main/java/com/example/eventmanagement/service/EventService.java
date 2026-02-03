package com.example.eventmanagement.service;


import com.example.eventmanagement.exception.EventNotFoundException;
import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public Event createEvent(Event event) {
        return repository.save(event);
    }


    public Event getEventById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(
                        "Event not found with id " + id));
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Event existingEvent = repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(
                        "Event not found with id " + id));

        existingEvent.setName(updatedEvent.getName());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setDate(updatedEvent.getDate());

        return repository.save(existingEvent);
    }

    public void deleteEvent(Long id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(
                        "Event not found with id " + id));

        repository.delete(event);
    }


    public List<Event> getAllEvents() {
        return repository.findAll();
    }
}


