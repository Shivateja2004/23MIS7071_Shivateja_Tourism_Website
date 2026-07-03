package com.tourism.service;

import com.tourism.model.Attraction;
import com.tourism.model.ContactMessage;
import com.tourism.model.Guide;
import com.tourism.model.Hotel;
import com.tourism.repository.TourismRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TourismService {
    private final TourismRepository repository;
    private final List<ContactMessage> messages = new ArrayList<>();

    public TourismService(TourismRepository repository) {
        this.repository = repository;
    }

    public List<Attraction> attractions() {
        return repository.attractions();
    }

    public List<Hotel> hotels() {
        return repository.hotels();
    }

    public List<Guide> guides() {
        return repository.guides();
    }

    public void saveContact(String name, String email, String message) {
        messages.add(new ContactMessage(name, email, message, LocalDateTime.now()));
    }

    public List<ContactMessage> messages() {
        return Collections.unmodifiableList(messages);
    }
}
