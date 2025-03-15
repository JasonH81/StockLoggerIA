package jason.InternalAssessment;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StockTextField extends JTextField {

	private static final long serialVersionUID = 1L;
	private StockInfoPanel infoPanel;
	private int position;
	private StockAPI stockAPI = new StockAPI();
	
	public StockTextField() {
		super("1", 5);
		addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				changedInput();
			}
			public void focusGained(FocusEvent e) {
				JTextField field = (JTextField)e.getSource();
				field.selectAll();
				field.setToolTipText("Position Information");
			}
		});
	}
	
	public StockTextField(int position, String message, StockInfoPanel infoPanel) {
		super(message, 5);
		this.infoPanel = infoPanel;
		this.position = position;
		addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				//changedInput();
			}
			public void focusGained(FocusEvent e) {
				JTextField field = (JTextField)e.getSource();
				field.selectAll();
				field.setToolTipText("Position Information");
			}
		});
	}
	
	// Check for valid number entry
    public void changedInput() {
    	String string = this.getText();
    	if (string!=null && string!="") {
	    	this.setText("" + string);
	    	infoPanel.changeField(string, position);
	    	if (position==1) {
	    		infoPanel.updateStockPrice(stockAPI.getStockData(string), string);
	    	}
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Must include stock data first");
    	}
    }
 }
