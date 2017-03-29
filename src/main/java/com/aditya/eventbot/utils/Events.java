package com.aditya.eventbot.utils;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
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
        String response = makeQuery(title);
        List<Object> list = new ArrayList<Object>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new InputSource(new StringReader(response)));
        NodeList nodeList = document.getElementsByTagName("event:location");
        return nodeList.item(0).getNodeValue();

    }


    private static String makeQuery(String title) throws Throwable {
        URL url = new URL("https://ruevents.rutgers.edu/events/getEventsRss.xml?keyword=congressman");

        Feed feed = FeedParser.parse(url);

        System.out.println("** HEADER **");
        FeedHeader header = feed.getHeader();
        System.out.println("Title: " + header.getTitle());
        System.out.println("Link: " + header.getLink());
        System.out.println("Description: " + header.getDescription());
        System.out.println("Language: " + header.getLanguage());
        System.out.println("PubDate: " + header.getPubDate());

        System.out.println("** ITEMS **");
        int items = feed.getItemCount();
        for (int i = 0; i < items; i++) {
            FeedItem item = feed.getItem(i);
            System.out.println("Title: " + item.getTitle());
            System.out.println("Link: " + item.getLink());
            //System.out.println("Location: " + item.);
        }
        return "";
    }
}
