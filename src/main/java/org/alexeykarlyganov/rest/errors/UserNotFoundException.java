package org.alexeykarlyganov.rest.errors;

public class UserNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long id) {
        super("Could not found user by id = " + id);
    }
}
