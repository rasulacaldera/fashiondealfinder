package com.bootsandcodes.fashiondealfinder.dealfinder;

import com.bootsandcodes.fashiondealfinder.model.Deal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

@Service
public class DealFinderContext {

    public List<Deal> findDeals() {
        List<Deal> allDeals = new ArrayList<>();
        ServiceLoader<DealFinder> loader = ServiceLoader.load(DealFinder.class);
        for (DealFinder dealFinder : loader) {
            allDeals.addAll(dealFinder.find());
        }
        return allDeals;
    }
}
