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
                res.type("application/json");
                String q = geo.getOn(geo.coor("Brett Hall"));
                //String q = Events.getAddress("congressman");
                System.out.println(q);
                return q;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        });
    }

}
