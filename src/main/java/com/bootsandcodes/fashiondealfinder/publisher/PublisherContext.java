package com.bootsandcodes.fashiondealfinder.publisher;

import com.bootsandcodes.fashiondealfinder.model.Deal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ServiceLoader;

@Service
public class PublisherContext {

    public void publishDeals(List<Deal> deals) {
        ServiceLoader<Publish> loader = ServiceLoader.load(Publish.class);
        for (Publish publisher : loader) {
            publisher.publish(deals);
        }
    }
}
