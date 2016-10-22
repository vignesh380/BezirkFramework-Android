package com.hackathon.yoko.bezirkhackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.android.BezirkMiddleware;
import com.hackathon.yoko.bezirkhackathon.Events.HelloWorldEvent;

public class MainActivity extends AppCompatActivity {
    private Bezirk bezirk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiate event with custom data
        HelloWorldEvent helloWorldEvent = new HelloWorldEvent();
        helloWorldEvent.helloMessage = "Hi world, how are you?";

        //Initialize the Bezirk service
        BezirkMiddleware.initialize(this);

        //Register with BezirkMiddleware to get an instance of Bezirk API.
        //The parameter is any human-readable string for a name of your Zirk
        bezirk = BezirkMiddleware.registerZirk("Hello World Zirk");

        //Give Bezirk 2 seconds to fully initialize before sending an event
        try { Thread.sleep(2000); }
        catch (InterruptedException e) { e.printStackTrace(); }

        //Send the Hello World event
        bezirk.sendEvent(helloWorldEvent);

        //Display a confirmation message
        TextView t = (TextView) findViewById(R.id.activity_main_hello);
        t.setText("Published hello world message: " + helloWorldEvent.helloMessage);

        //t.setText("Yoco Broah!!");
    }
}
