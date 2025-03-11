package jason.InternalAssessment;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public class StockTextField extends JTextField {

	private static final long serialVersionUID = 1L;
	private StockInfoPanel infoPanel;
	private int position;
	
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
				changedInput();
			}
			public void focusGained(FocusEvent e) {
				JTextField field = (JTextField)e.getSource();
				field.selectAll();
				field.setToolTipText("Position Information");
			}
		});
	}
	
	// Check for valid number entry
    private void changedInput() {
    	String string = this.getText();
    	this.setText("" + string);
    	infoPanel.changeField(string, position);
    }
 }
