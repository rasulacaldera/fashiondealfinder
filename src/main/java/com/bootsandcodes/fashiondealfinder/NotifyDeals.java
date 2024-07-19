package com.bootsandcodes.fashiondealfinder;

import com.bootsandcodes.fashiondealfinder.dealfinder.DealFinderContext;
import com.bootsandcodes.fashiondealfinder.publisher.EmailPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotifyDeals {

    private final DealFinderContext dealFinderContext;
    private final EmailPublisher emailPublisher;

    public NotifyDeals(DealFinderContext dealFinderContext, EmailPublisher emailPublisher) {
        this.dealFinderContext = dealFinderContext;
        this.emailPublisher = emailPublisher;
    }

    @Scheduled(initialDelay = 0, fixedRate = 60000)
    void notifyDeals() {
        emailPublisher.publish(dealFinderContext.findDeals());
    }
}
