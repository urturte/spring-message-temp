package it.academy.message;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin({"http://localhost:4200"})
@RequestMapping("messages")
@AllArgsConstructor
public class MessageController {

    private static final String LINE_SEPARATOR = "\n";
    private static final String BE_SUCCESS_MESSAGE = "BE works";
    private static final String DB_SUCCESS_MESSAGE = "DB works";
    private static final String DB_ERROR_MESSAGE = "DB error message:";
    private static final String STORED_MESSAGES = "Stored messages:";

    private final MessageRepository repository;

    @GetMapping
    public String getMessages() {
        StringBuilder result = new StringBuilder(BE_SUCCESS_MESSAGE).append(LINE_SEPARATOR);

        try {
            List<MessageBean> messages = repository.findAll();
            String results = messages.stream().map(MessageBean::getMessage).collect(Collectors.joining(LINE_SEPARATOR));
            result.append(DB_SUCCESS_MESSAGE).append(LINE_SEPARATOR).append(STORED_MESSAGES).append(LINE_SEPARATOR).append(results);
        } catch (Exception e) {
            result.append(DB_ERROR_MESSAGE).append(e.getMessage());
        }

        return result.toString();
    }

    @GetMapping("{messageContent}")
    public String createMessage(@PathVariable String messageContent) {
        String message = repository.save(MessageBean.builder().message(messageContent).build()).getMessage();
        return "Message: `%s` was save successfully".formatted(message);
    }

}
