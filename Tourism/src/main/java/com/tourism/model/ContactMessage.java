package com.tourism.model;

import java.time.LocalDateTime;

public record ContactMessage(String name, String email, String message, LocalDateTime submittedAt) {
}
