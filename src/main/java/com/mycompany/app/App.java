package com.mycompany.app;

/**
 * Hello world!
 */


public class App {
    // minor change
    // minor change 2

    private final String message = "Hello World!";

    public App() {
    }
    public static void main(String[] args) {
        System.out.println(new App().getMessage());

    }

    private final String getMessage() {
        boolean cond = true;
        if (cond)
            return message;
        else
            return "";
    }

    private final String getMessageNew() {
        return message;
    }

    private final String getMessageNew2() {
        return message;
    }
    private final String getMessageNew3() {
        return message;
    }
    private final String getMessageNew4() {
        return message;
    }
}
