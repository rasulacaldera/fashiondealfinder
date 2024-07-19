package com.bootsandcodes.fashiondealfinder;

import com.bootsandcodes.fashiondealfinder.dealfinder.PatagoniaDealFinder;
import com.bootsandcodes.fashiondealfinder.publisher.EmailPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Test {

    private EmailPublisher emailPublisher;
    private PatagoniaDealFinder patagoniaDeals;

    public Test(EmailPublisher emailPublisher, PatagoniaDealFinder patagoniaDeals) {
        this.emailPublisher = emailPublisher;
        this.patagoniaDeals = patagoniaDeals;
    }

    @Scheduled(initialDelay = 0, fixedRate = 60000)
    void test() {
        emailPublisher.publish(patagoniaDeals.find());
    }
}
