package com.jefferycalhoun.hw3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LightChangerFragment.LightChangerListener {

    private static final String EXTRA_TRAFFIC_LIGHT_STATE = "EXTRA_TRAFFIC_LIGHT_STATE";

    TrafficLight mTrafficLightManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrafficLightManager = new TrafficLight();

        if(savedInstanceState != null){
            String savedTrafficLightStateString = savedInstanceState.getString(EXTRA_TRAFFIC_LIGHT_STATE);

            if(savedTrafficLightStateString!= null && !savedTrafficLightStateString.equals("")){
                TrafficLight.TrafficLightState savedState = TrafficLight.TrafficLightState.valueOf(savedTrafficLightStateString);
                setTrafficLightState(savedState);
            }
        }
        else{
            changeTrafficLight();
        }




    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(EXTRA_TRAFFIC_LIGHT_STATE, mTrafficLightManager.getState().toString());
    }

    public void changeTrafficLight(){
        TrafficLight.TrafficLightState newState;
        TrafficLightFragment trafficLightFragment;
        trafficLightFragment = (TrafficLightFragment) getSupportFragmentManager().findFragmentById(R.id.traffic_light_fragment);

        if(trafficLightFragment != null){

            newState = mTrafficLightManager.changeTrafficLight();
            trafficLightFragment.changeLight(newState);


        }
        else{
            Log.e("TrafficLight", "Failed to retrieve TrafficLightFragment from support fragment manager");
        }
    }

    public void setTrafficLightState(TrafficLight.TrafficLightState state){
        TrafficLightFragment trafficLightFragment;
        trafficLightFragment = (TrafficLightFragment) getSupportFragmentManager().findFragmentById(R.id.traffic_light_fragment);

        if(trafficLightFragment != null){

            mTrafficLightManager.setState(state);
            trafficLightFragment.changeLight(state);


        }
        else{
            Log.e("TrafficLight", "Failed to retrieve TrafficLightFragment from support fragment manager");
        }
    }
}
