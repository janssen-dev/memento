package dev.janssensoftware.memento;

import org.springframework.boot.SpringApplication;

public class TestMementoApplication {

    public static void main(String[] args) {
        SpringApplication.from(MementoApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
