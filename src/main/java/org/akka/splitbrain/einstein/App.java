package org.akka.splitbrain.einstein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.akka.splitbrain")
public class App {

    public static void main(final String args[]) {
        SpringApplication.run(App.class, args);
    }
} 
