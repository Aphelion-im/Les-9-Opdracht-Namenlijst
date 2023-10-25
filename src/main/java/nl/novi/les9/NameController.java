package nl.novi.les9;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RestController
public class NameController {

    private final ArrayList<String> names = new ArrayList<>(); // Private field?
    private final Set<String> noDuplicates = new HashSet<>();
    private String myName;

    @GetMapping("/hello")
    public static String printHello() {
        return "Hello, world!!";
    }

    // Naam in een normale variabele stoppen
    @PostMapping("/addName")
    public void addName(@RequestParam(required = true) String name) { // name moet overeenkomen met de key name in Postman!
        if (name != null) {
            myName = name;
        }
    }

    // Naam in een ArrayList stoppen
    @PostMapping("/addNames")
    public String addNames(@RequestParam(required = true) String name) { // name moet overeenkomen met de key name in Postman!
        if (name != null && !names.contains(name.toLowerCase())) {  // Het kan ook met Set, HashSet, etc.- Voegt alleen Greet toe of greet, niet beide. Case sensitive.
            names.add(name);
        } else {
            return "Error: Name was already added!";
        }
        return "201 OK: Name " + "\"" + name + "\"" + " successfully added!";
    }

    // Naam in een HashSet stoppen (Set mag geen duplicaten! Dubbele beveiliging met contains t/f check)
    @PostMapping("/addNameNoDuplicates")
    public String addNames2(@RequestParam String name) {
        if (name != null && !noDuplicates.contains(name)) {   // Ignore case, zowel Greet als greet kun je toevoegen als aparte namen
            noDuplicates.add(name);
        } else {
            return "Error: Name was already added!";
        }
        return "201 OK: Name " + "\"" + name + "\"" + " successfully added!";
    }


    // Laat naam in de variabele zien
    @GetMapping("/showName")
    public String showName() {
        return "Hallo " + myName;
    }


    // Version 1: Vraag de namen op die in de ArrayList staan door middel van ForEach
    @GetMapping("/showNames")
    public ArrayList<String> showNames() {
        ArrayList<String> result = new ArrayList<>();
        for (String name : names) {
            result.add("Hallo " + name);
        }
        return result;
    }

    // Version 2: Vraag de namen op die in de ArrayList staan door middel van StringBuilder
    // Met dubbele waarden
    @GetMapping("/showNames2")
    public StringBuilder showNames2() {
        StringBuilder greetings = new StringBuilder();
        for (String name : names) {
            greetings.append("Hallo ").append(name).append("\n");
        }
        return greetings; // Of String als return type + return greetings.toString();
    }

    // Normale variable reversed name met StringBuilder
    @GetMapping("/reversedName")
    public StringBuilder reversedName() {
        StringBuilder reversedName = new StringBuilder(myName);
        return reversedName.reverse();
    }

    // Normale variable reversed name met StringBuilder
    @GetMapping("/reversedName2")
    public ArrayList<String> reversedName2() {
        ArrayList<String> result = new ArrayList<>();
        for (String name : names) {
            StringBuilder reversedName = new StringBuilder(name).reverse(); // Is dit wel de beste manier om steeds een StringBuilder object aan te maken?
            result.add(String.valueOf(reversedName));
        }
        return result;
    }


}
