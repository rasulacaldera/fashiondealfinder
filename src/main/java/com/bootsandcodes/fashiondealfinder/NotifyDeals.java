package com.bootsandcodes.fashiondealfinder;

import com.bootsandcodes.fashiondealfinder.dealfinder.DealFinderContext;
import com.bootsandcodes.fashiondealfinder.publisher.PublisherContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotifyDeals {

    private final DealFinderContext dealFinderContext;
    private final PublisherContext publisherContext;

    public NotifyDeals(DealFinderContext dealFinderContext, PublisherContext publisherContext) {
        this.dealFinderContext = dealFinderContext;
        this.publisherContext = publisherContext;
    }

    @Scheduled(initialDelay = 0, fixedRate = 60000)
    void notifyDeals() {
        publisherContext.publishDeals(dealFinderContext.findDeals());
    }
}
