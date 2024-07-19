package com.bootsandcodes.fashiondealfinder.publisher;

import org.springframework.stereotype.Service;

@Service
public class TwitterPublisher implements Publish {
    @Override
    public void publish() {
        System.out.println("Starting Twitter Publish");
    }
}
