package com.akos.uribuilder;

import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

public class UriBuilderTest {

    public static void main(String[] args) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory("/search/listings.json?q-check-out=2020-04-11&q-destination=Austin%2C%20Texas%2C%20United%20States%20of%20America&start-index=10&q-check-in=2020-04-07&q-room-0-children=0&points=false&destination-id=1496344&f-hotel-id=1124608128&q-room-0-adults=2&pg=1&q-rooms=1&resolved-location=CITY%3A1496344%3AUNKNOWN%3AUNKNOWN&pn=2\n");
        final UriBuilder builder = factory.builder();
    }
}
