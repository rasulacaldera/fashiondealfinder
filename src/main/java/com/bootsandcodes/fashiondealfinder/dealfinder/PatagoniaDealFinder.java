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
            Elements products = doc.select(".product-tile");

            for (Element product : products) {
                Deal deal = new Deal();
                deal.setTitle(product.select(".product-tile__name").text());
                deal.setUrl("https://eu.patagonia.com" + product.select(".pdp-link").select("a.link").attr("href"));
                deal.setPrice(product.select("div.price").select("span.text-sales-price").select("span.value").attr("content"));
                deal.setOriginalPrice(product.select("div.price").select("span.strike-through").select("span.value").attr("content"));
                deal.setImageUrl(product.select("div.product-tile__image").select(".active").select("meta").attr("content"));
                deal.setDiscount(product.select("span.color-price").attr("data-discount-percent"));

                deals.add(deal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deals;
    }
}
