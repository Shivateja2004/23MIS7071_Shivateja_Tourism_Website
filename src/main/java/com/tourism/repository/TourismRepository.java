package com.tourism.repository;

import com.tourism.model.Attraction;
import com.tourism.model.Guide;
import com.tourism.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TourismRepository {
    public List<Attraction> attractions() {
        return List.of(
                new Attraction("Heritage Fort", "Jaipur", "Hilltop fort with royal courtyards and panoramic city views.", "Heritage"),
                new Attraction("Lotus Lake", "Udaipur", "Calm lakefront destination for boating, photography, and evening walks.", "Nature"),
                new Attraction("Temple Trail", "Madurai", "Historic temple district with guided cultural walks.", "Spiritual"),
                new Attraction("Coastal Promenade", "Goa", "Beach walk, water sports, local markets, and sunset dining.", "Coastal")
        );
    }

    public List<Hotel> hotels() {
        return List.of(
                new Hotel("Vista Palace Resort", "Jaipur", "4.6/5", "https://www.booking.com"),
                new Hotel("Lakeview Heritage Inn", "Udaipur", "4.4/5", "https://www.makemytrip.com"),
                new Hotel("Temple City Residency", "Madurai", "4.2/5", "https://www.goibibo.com"),
                new Hotel("Blue Coast Retreat", "Goa", "4.7/5", "https://www.agoda.com")
        );
    }

    public List<Guide> guides() {
        return List.of(
                new Guide("Three Day Heritage Circuit", "A practical route for forts, museums, markets, and food streets.", "October to March"),
                new Guide("Family Nature Weekend", "Lake activities, light trekking, gardens, and safe local transport.", "August to February"),
                new Guide("Budget Backpacking Plan", "Hostels, public transport, street food, and free walking routes.", "All year"),
                new Guide("Monsoon Travel Safety", "Packing, road checks, emergency contacts, and weather planning.", "June to September")
        );
    }
}
