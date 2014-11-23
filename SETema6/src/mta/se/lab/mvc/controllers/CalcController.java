package mta.se.lab.mvc.controllers;

import mta.se.lab.mvc.exceptions.InputException;
import mta.se.lab.mvc.interfaces.IController;
import mta.se.lab.mvc.interfaces.IView;
import mta.se.lab.mvc.model.CalcModel;
import mta.se.lab.mvc.web.GetInformations;
import mta.se.lab.mvc.web.WeatherHttpClient;

import javax.swing.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * class that implement controller 
 * @author ADY
 * @since 2014-11-22
 */

public class CalcController implements IController {
    // The Controller needs to interact with both the Model and View.
    private CalcModel mModel;
    

    // The list of views that listen for updates
    private List<IView> mViews;

    /**
     * Constructor
     */
    public CalcController() {
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(ACTION_CALCULATE)) {
            // Make the operation
            try {
                JButton source = (JButton) event.getSource();
                if (source != null ) {
                   
                    makeOperation();
                } else {
                    notifyViews(true, "Invalid operation data");
                }
            } catch (InputException e) {
                notifyViews(true, e.getMessage());
            } catch (ClassCastException ec) {
                notifyViews(true, ec.getMessage());
            }
        } 
    }

    /**
     * Adds a view reference in order to interact with it
     *
     * @param view The view from the controller will receive events and send messages
     */
    public void addView(IView view) {
        if (mViews == null) {
            mViews = new ArrayList<IView>();
        }

        mViews.add(view);
    }

    /**
     * Adds a reference to the model, so it can update it
     *
     * @param model The data model reference
     */
    public void addModel(CalcModel model) {
        mModel = model;
       
    }

    /**
     * Notifies the views when an message must be displayed
     *
     * @param isError {@code true} if the message is an error, {@code false} otherwise
     * @param message The string to be displayed
     */
    private void notifyViews(boolean isError, String message) {
        if (mViews != null && !mViews.isEmpty()) {
            for (IView view : mViews) {
                view.onMessage(isError, message);
            }
        }
    }

    /**
     * Sets values for all fields 
     *
     */
    private void makeOperation() throws InputException {
        if (mModel != null ) {
            
            try {
                // Update the model
            	
            	String informations=WeatherHttpClient. getWeatherData("lat=44&lon=26");
            	try {
					JSONObject jObj = new JSONObject(informations);
					JSONObject coordObj = GetInformations.getObject("main", jObj);
					float temp=GetInformations.getFloat("temp", coordObj);
					mModel.setValue(Float.toString(temp));
					int pressure=GetInformations.getInt("pressure", coordObj);
					mModel.setValue3(Integer.toString(pressure));
					JSONObject coordObj2 = GetInformations.getObject("wind", jObj);
					float wind=GetInformations.getFloat("speed", coordObj2);
					mModel.setValue2(Float.toString(wind));
				} catch (JSONException e) {
					System.out.println("ERROR:"+e.getMessage());
					e.printStackTrace();
				}
            	/*
            	int minimum=-20;
            	int maximum=41;
            	int randd=0;
            	randd = minimum + (int)(Math.random()*maximum); 
                mModel.setValue(Integer.toString(randd));
                minimum=3;
                maximum=120;
                randd = minimum + (int)(Math.random()*maximum); 
                mModel.setValue2(Integer.toString(randd));
                minimum=730;
                maximum=765;
                Random rand = new Random();
                randd = rand.nextInt((maximum - minimum) + 1) + minimum; 
                mModel.setValue3(Integer.toString(randd));
                */
            	
            } catch (NumberFormatException e) {
                throw new InputException("false", e.getMessage());
            }
        }
    }
}