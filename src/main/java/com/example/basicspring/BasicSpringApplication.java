package com.example.basicspring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import java.util.Scanner;


@SpringBootApplication
@EnableAspectJAutoProxy
public class BasicSpringApplication implements CommandLineRunner {

    @Autowired
    private MessageService messageService;

    public static void main(String[] args) {
        SpringApplication.run(BasicSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Ingresa tu mensaje:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String result = messageService.processMessage(input);
        System.out.println("Mensaje procesado: " + result);

    }
}

