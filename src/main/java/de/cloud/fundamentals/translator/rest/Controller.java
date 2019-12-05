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
    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;
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
    @GetMapping("/")
    public String getStatus() {
        return "TranslateService is active.";
    }

    @PostMapping(value = "/api", produces = JSON, consumes = JSON) //define endpoint
    public ResponseEntity<String> receiveRequest(@RequestBody String message){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(translateService.getTranslation(message));
    }
    //return ResponseEntity<String> with body filled with the response-message
}
