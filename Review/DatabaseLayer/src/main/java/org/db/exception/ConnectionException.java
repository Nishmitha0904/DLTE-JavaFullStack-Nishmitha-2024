package org.db.exception;

import java.util.ResourceBundle;

public class ConnectionFailure extends RuntimeException {
    public ConnectionFailure() {
        super(String.valueOf(ResourceBundle.getBundle("database")));
    }
}
