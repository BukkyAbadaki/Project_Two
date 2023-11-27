package com.user.usersdemo.constructor;

import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class MessageController {
    private static final String file = "message.txt";
    private static final String fileTwo = "log.txt";


    @GetMapping("/message")
    public StringBuilder getMessage() {
        StringBuilder sb = new StringBuilder();
        String x;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((x = bufferedReader.readLine()) != null) {
                sb.append(x);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return new StringBuilder(sb.toString());

    }

    @GetMapping("/count")
    public long getMessageCount() throws IOException{
        return Files.lines(Path.of(file)).count();
    }


    @PostMapping("/postMessage")
    public String postMessage(@RequestBody String message){

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true))) {
            bufferedWriter.write(message + "#");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return "You have successfully saved your text";
    }

    private void logActivity(String logMessage) throws IOException {
        Files.write(Path.of(fileTwo), logMessage.getBytes());
    }
}
