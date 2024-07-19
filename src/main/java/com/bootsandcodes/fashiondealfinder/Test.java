package com.bootsandcodes.fashiondealfinder;

import com.bootsandcodes.fashiondealfinder.publisher.RedditPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Test {

    private RedditPublisher redditPublisher;

    public Test(RedditPublisher redditPublisher) {
        this.redditPublisher = redditPublisher;
    }

    @Scheduled(initialDelay = 0, fixedRate = 60000)
    void test() {
        System.out.println("Triggering");
        redditPublisher.publish();
    }
}
