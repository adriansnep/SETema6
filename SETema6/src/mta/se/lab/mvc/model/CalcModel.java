package mta.se.lab.mvc.model;

import mta.se.lab.mvc.exceptions.InputException;
import mta.se.lab.mvc.interfaces.IModelListener;


import java.util.ArrayList;
import java.util.List;

/**
 * class used to implement o model for the application
 * @author ADY
 * @since 2014-11-22
 */
public class CalcModel {
    // Constants
    public static final String INITIAL_VALUE = "1";

    // Member variable defining state of calculator, the total current value
    private Float mTotal;
    private Float mTotal2;
    private Float mTotal3;

    private List<IModelListener> mListeners;

    /**
     * Constructor
     */
    public CalcModel() {
        mTotal = new Float(INITIAL_VALUE);
        mTotal2 = new Float(INITIAL_VALUE);
        mTotal3 = new Float(INITIAL_VALUE);
    }

    /**
     * Set the temperature value.
     *
     * @param value New value that should be used for the temperature field.
     */
    public void setValue(String value) throws InputException {
        try {
        	
            mTotal = new Float(value);
            notifyListeners();
        } catch (NumberFormatException e) {
            throw new InputException(value, e.getMessage());
        }
    }
    /**
     * Set the wind speed value.
     *
     * @param value New value that should be used for the wind speed field.
     */
    public void setValue2(String value) throws InputException {
        try {
            mTotal2 = new Float(value);
            notifyListeners();
        } catch (NumberFormatException e) {
            throw new InputException(value, e.getMessage());
        }
    }
    /**
     * Set the atmospheric pressure value.
     *
     * @param value New value that should be used for the atmospheric pressure field.
     */
    public void setValue3(String value) throws InputException {
        try {
            mTotal3 = new Float(value);
            notifyListeners();
        } catch (NumberFormatException e) {
            throw new InputException(value, e.getMessage());
        }
    }

    /**
     * Return current temperature value.
     */
    public String getValue() {
        return mTotal.toString();
    }
    /**
     * Return current wind speed value.
     */
    public String getValue2() {
        return mTotal2.toString();
    }
    /**
     * Return current atmospheric pressure value.
     */
    public String getValue3() {
        return mTotal3.toString();
    }

    /**
     * Adds the view listener to the list
     *
     * @param listener The model event listener
     */
    public void addModelListener(IModelListener listener) {
        if (mListeners == null) {
            mListeners = new ArrayList<IModelListener>();
        }

        mListeners.add(listener);
    }

    /**
     * Notifies the views listeners of the changed state (value)
     */
    private void notifyListeners() {
        if (mListeners != null && !mListeners.isEmpty()) {
            for (IModelListener listener : mListeners)
                listener.onUpdate();
        }
    }
}