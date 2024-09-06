package codesnippet.spring.rocketmq.controller;

import codesnippet.spring.rocketmq.service.PublishService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

    private final PublishService publishService;

    public PublishController(PublishService publishService) {

        this.publishService = publishService;
    }

    @PostMapping("/publish")
    public void publish() {

        this.publishService.sendMessage();
    }
}
