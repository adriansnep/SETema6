package mta.se.lab.mvc.views;

import mta.se.lab.mvc.interfaces.IController;
import mta.se.lab.mvc.interfaces.IModelListener;
import mta.se.lab.mvc.interfaces.IView;
import mta.se.lab.mvc.model.CalcModel;


import javax.swing.*;

import java.awt.*;

/**
 * class that implement view part of the application
 * @author ADY
 * @since 2014-11-22
 */
public class CalcView extends JFrame implements IModelListener, IView {
    private static final long serialVersionUID = -5758555454500685115L;

    // View Components
    private JTextField mTemperature = new JTextField(6);
    private JTextField mWindSpeed = new JTextField(6);
    private JTextField mAtmosphericPressure = new JTextField(6);
    
    private JButton mUpdateBtn = new JButton("Update");

    private IController mCalcController;

    private CalcModel mModel;
   

    /**
     * Constructor
     */
    public CalcView() {
        // Initialize components
    	mTemperature.setEditable(false);
        mWindSpeed.setEditable(false);
        mAtmosphericPressure.setEditable(false);

        // Layout the components.
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(new JLabel("Temperature"));
        content.add(mTemperature);
        content.add(new JLabel("\u00b0K"));
        content.add(new JLabel("             "));
        content.add(new JLabel("Wind Speed"));
        content.add(mWindSpeed);
        content.add(new JLabel("m/s"));
        content.add(new JLabel("             "));
        content.add(new JLabel("Atmospheric Pressure"));
        content.add(content.add(mAtmosphericPressure));
        content.add(new JLabel("hPa"));
        content.add(new JLabel("             "));
        content.add(mUpdateBtn);

        // Finalize layout
        this.setContentPane(content);
        this.pack();

        this.setTitle("Weather");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Sets the view's reference to the model - Only get operations allowed
     *
     * @param model The weather model
     */
    public void addModel(CalcModel model) {
        mModel = model;
        
        mWindSpeed.setText(model.getValue2());
        mTemperature.setText(model.getValue());
        mAtmosphericPressure.setText(model.getValue3());
        
    }

    /**
     * Sets the view's event listener - the controller - so that the changes made by the user in the view, can be reflected in the model
     *
     * @param controller The controller (event listener)
     */
    public void addController(IController controller) {
       

        mUpdateBtn.setActionCommand(IController.ACTION_CALCULATE);
        mUpdateBtn.addActionListener(controller);
    }

    @Override
    public void onMessage(boolean isError, String message) {
        if (isError) {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, message, "Calc MVC", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void onUpdate() {
        mWindSpeed.setText(mModel.getValue2());
        mTemperature.setText(mModel.getValue());
        mAtmosphericPressure.setText(mModel.getValue3());
    }

	public IController getmCalcController() {
		return mCalcController;
	}

	public void setmCalcController(IController mCalcController) {
		this.mCalcController = mCalcController;
	}
}
