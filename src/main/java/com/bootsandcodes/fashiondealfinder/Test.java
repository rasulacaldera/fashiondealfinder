package com.bootsandcodes.fashiondealfinder;

import com.bootsandcodes.fashiondealfinder.publisher.EmailPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Test {

    private EmailPublisher emailPublisher;

    public Test(EmailPublisher emailPublisher) {
        this.emailPublisher = emailPublisher;
    }

    @Scheduled(initialDelay = 0, fixedRate = 60000)
    void test() {
        System.out.println("Triggering");
        emailPublisher.publish();
    }
}
