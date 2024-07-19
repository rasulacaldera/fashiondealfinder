package com.bootsandcodes.fashiondealfinder.dealfinder;

import com.bootsandcodes.fashiondealfinder.model.Deal;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatagoniaDealFinder implements DealFinder {

    private static final String URL = "https://eu.patagonia.com/nl/en/shop/web-specials";

    @Override
    public List<Deal> find() {
        List<Deal> deals = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(URL).get();
            Elements products = doc.select(".product-tile"); // Adjust the selector as needed

            for (Element product : products) {
                Deal deal = new Deal();
                deal.setTitle(product.select(".product-tile__name").text());
                deal.setUrl(product.select(".product-tile__link").attr("href"));
                deal.setPrice(product.select(".product-tile__price--sales").text());
                deal.setOriginalPrice(product.select(".product-tile__price--original").text());
                deal.setImageUrl(product.select(".product-tile__image img").attr("src"));

                deals.add(deal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deals;
    }
}
