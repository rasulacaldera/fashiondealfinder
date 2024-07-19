package com.bootsandcodes.fashiondealfinder.publisher;

import org.springframework.stereotype.Service;

@Service
public class RedditPublisher implements Publish {
    @Override
    public void publish() {
        System.out.println("Starting Reddit Publish");
    }
}
