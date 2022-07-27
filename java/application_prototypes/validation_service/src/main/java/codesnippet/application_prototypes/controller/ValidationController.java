package codesnippet.application_prototypes.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codesnippet.application_prototypes.model.ValidationResponse;
import codesnippet.application_prototypes.service.ValidationService;

@RestController
@RequestMapping(value = "/validation")
public class ValidationController
{
    private final ValidationService validationService;

    public ValidationController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @GetMapping
    public ValidationResponse validate(@Valid @RequestParam(required = true) String password) {
        return new ValidationResponse(
                    this.validationService.validate(password));
    }
}
