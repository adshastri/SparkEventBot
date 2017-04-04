package com.aditya.eventbot.utils;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import it.sauronsoftware.feed4j.FeedParser;
import it.sauronsoftware.feed4j.bean.Feed;
import it.sauronsoftware.feed4j.bean.FeedHeader;
import it.sauronsoftware.feed4j.bean.FeedItem;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by aditya on 3/27/17.
 */
public class Events {
    public static String getAddress(String title) throws Throwable {
        URL url = new URL("https://ruevents.rutgers.edu/events/getEventsRss.xml?keyword=" + title);

        Feed feed = FeedParser.parse(url);

        System.out.println("** HEADER **");
        FeedHeader header = feed.getHeader();
        int items = feed.getItemCount();
        if (items > 0) {

            FeedItem item = feed.getItem(0);
            return item.getElementValue("http://ruevents.rutgers.edu/events", "location");
        } else {
            return "No event found!";
        }

    }

}
