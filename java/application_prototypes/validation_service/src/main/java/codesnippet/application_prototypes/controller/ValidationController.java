package codesnippet.application_prototypes.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codesnippet.application_prototypes.model.ValidationRequest;
import codesnippet.application_prototypes.service.ValidationService;

@RestController
@RequestMapping(value = "/validation")
public class ValidationController
{
    private final ValidationService validationService;

    public ValidationController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public String validate(@RequestBody ValidationRequest request) {
        return this.validationService.validate(request.getPassword()) ? "valid" : "invalid";
    }
}
