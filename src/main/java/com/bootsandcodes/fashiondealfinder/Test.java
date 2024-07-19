package com.bootsandcodes.fashiondealfinder;

import com.bootsandcodes.fashiondealfinder.publisher.TwitterPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Test {

    private TwitterPublisher twitterPublisher;

    public Test(TwitterPublisher twitterPublisher) {
        this.twitterPublisher = twitterPublisher;
    }

    @Scheduled(initialDelay = 0, fixedRate = 60000)
    void test() {
        System.out.println("Triggering");
        twitterPublisher.publish();
    }
}
