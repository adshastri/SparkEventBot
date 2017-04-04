package com.aditya.eventbot.server;

import com.aditya.eventbot.utils.Bus;
import com.aditya.eventbot.utils.Events;
import com.aditya.eventbot.utils.Geo;

import static spark.Spark.*;


/**
 * Created by aditya on 3/23/17.
 */
public class MainApp {
    public static void main(String[] args) {

        final Geo geo = new Geo();

        post("/event", (req, res) -> {
            System.out.println(req.body());
            try {
                String address = Events.getAddress("book talk with congressman John Lewis");
                System.out.println(address);
                res.type("application/json");
                String on = geo.getOn(geo.coor("Brett Hall"));
                String off = geo.getOff(geo.coor(address));
                String bus = Bus.bus(on, off);
                return formatString(on, off, bus);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        });
    }

    private static String formatString(String on, String off, String bus){
        return "{ " +
                "\"speech\" : \"go to the "+on+" stop, take the " + bus + " and get off at the " + off + " stop. \", " +
                "\"source\": \"webhook\", " +
                "\"displayText\": \"rutgerss\" }";
    }

}
