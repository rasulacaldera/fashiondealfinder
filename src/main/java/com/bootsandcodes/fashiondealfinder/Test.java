package com.bootsandcodes.fashiondealfinder;

import com.bootsandcodes.fashiondealfinder.publisher.EmailRedditPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Test {

    private EmailRedditPublisher emailRedditPublisher;

    public Test(EmailRedditPublisher emailRedditPublisher) {
        this.emailRedditPublisher = emailRedditPublisher;
    }

    @Scheduled(initialDelay = 0, fixedRate = 60000)
    void test() {
        System.out.println("Triggering");
        emailRedditPublisher.publish();
    }
}
