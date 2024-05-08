package org.db.exception;

import java.util.ResourceBundle;

public class ConnectionException extends RuntimeException {
    public ConnectionException() {
        super(ResourceBundle.getBundle("exception").getString("connection.fail"));
    }
}
