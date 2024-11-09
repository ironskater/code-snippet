package codesnippet.spring.websocket.basic.controller;

import codesnippet.spring.websocket.basic.model.Message;
import codesnippet.spring.websocket.basic.model.OutputMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageHandlingController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages") // broker topic
    public OutputMessage send(Message message) {

        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());

        return new OutputMessage(message.getFrom(), message.getText(), time);
    }

    @MessageMapping("/notification")
    @SendTo("/topic/messages") // broker topic
    public OutputMessage sendNotification(Message message) {

        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());

        return new OutputMessage(message.getFrom(), message.getText() + " (notification)", time);
    }
}
