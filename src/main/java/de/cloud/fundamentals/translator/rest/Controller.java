package de.cloud.fundamentals.translator.rest;

import de.cloud.fundamentals.translator.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    // handles api requests or provides features
    // input - message from telegram without keyword
    // output - message to response user request
    private final TranslateService translateService;

    @Autowired
    public Controller(TranslateService apiService) {
        this.translateService = apiService;
    }

    // (optional) to check status of service via browser
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getStatus() {
        return "TranslateService is active.";
    }

    @PostMapping("/api") //define endpoint
    public ResponseEntity<String> receiveRequest(@RequestBody String message){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(translateService.getTranslation(message));
    }
    //return ResponseEntity<String> with body filled with the response-message
}
