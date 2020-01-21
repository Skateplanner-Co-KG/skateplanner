package de.htwberlin.skateplanner.email;

import de.htwberlin.skateplanner.user.UserEntity;
import de.htwberlin.skateplanner.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class EmailReminderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @Async
    public Future<Boolean> sendMessage(String to, String subject, String text) {
        AsyncResult<Boolean> result = new AsyncResult<>(true);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
        return result;
    }

    @Async
    public Future<Boolean> sendMessageToAllAccounts(String subject, String message) {
        for (UserEntity e : userRepository.findAll()) {
            sendMessage(e.getEmail(), subject, message);
        }
        return new AsyncResult<>(true);
    }

}
