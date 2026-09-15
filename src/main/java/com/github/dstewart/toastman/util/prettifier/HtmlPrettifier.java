package com.github.dstewart.toastman.util.prettifier;

import org.jsoup.Jsoup;

public class HtmlPrettifier extends Prettifier {
    @Override
    public String prettify(String body) {
        return Jsoup.parse(body).toString();
    }
}
