package com.BookClub.BookClub.Exceptions;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(UUID id, Class<?> entity){
        super("The " + entity.getSimpleName().toLowerCase() + " with id: " + id + " cannot found.");
    }
}
