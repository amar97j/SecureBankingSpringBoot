package com.nada.SpringBootMiniProject.controller;

import com.nada.SpringBootMiniProject.bo.user.Contact;
import com.nada.SpringBootMiniProject.bo.user.Fields;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CodedController {
    private List<Contact> contacts = new ArrayList<>();

    @GetMapping("/sayHi")
    public String sayHi() {
        return "Welcome Nada";
    }

    @GetMapping("/greet")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello, " + name + "!";

    }

    @PostMapping("/farewell")
    public String farewell(@RequestBody Fields requestBody) {
        String name = requestBody.getName();
        return "Goodbye, " + name + "!";
    }

    @PostMapping("/addAContact")
    public ResponseEntity<String> addContact(@RequestBody Contact contact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getEmail().equals(contact.getEmail())) {
                return ResponseEntity.badRequest().body("Contact already exists with this email!");
            }
        }
        contacts.add(contact);
        return ResponseEntity.ok("Contact added successfully!");
    }


    @GetMapping("/getAllContactDetails")
    public ResponseEntity<Object> getContactDetails(@RequestParam String name) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equals(name)) {
                return ResponseEntity.ok(contacts.get(i));
            }
        }
        return ResponseEntity.badRequest().body("Contact not found");

    }

}
