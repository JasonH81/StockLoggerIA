package jason.InternalAssessment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class StockInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int width = 500;
    private int height = 640;
    
    private int ID;
    private FileManager fileM;
    
    private StockTextField firstField;
    private StockTextField secondField;
    private StockTextField thirdField;
    private StockTextField fourthField;
    
    public StockInfoPanel(int i) {
    	fileM = new FileManager();
    	ID = i;
    	initGUI();
    }
    
    // set up java swing utilities
    @Override
    public Dimension getPreferredSize() {
        Dimension size = new Dimension(width, height);
        return size;
    }
    
    @Override
    public void paintComponent(Graphics g) {
		// background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
	}
    
    private void initGUI() {
        setFocusable(true);
		ArrayList<String> lines = fileM.readStockData(ID);
        if (ID>0) {
        	firstField = new StockTextField(1, lines.get(1).substring(lines.get(1).lastIndexOf(" ")+1), this);
        	secondField = new StockTextField(2, lines.get(2).substring(lines.get(2).lastIndexOf(" ")+1), this);
        	thirdField = new StockTextField(3, lines.get(3).substring(lines.get(3).lastIndexOf(" ")+1), this);
        	fourthField = new StockTextField(4, lines.get(4).substring(lines.get(4).lastIndexOf(" ")+1), this);
        	add(firstField);
        	add(secondField);
        	add(thirdField);
        	add(fourthField);
        }
    }
   
    
    public void changeField(String message, int position) {
    	// ID is the general position in the file
    	// Position is the exact criteria to modify
    	// Message is what you replace it with
    	fileM.writeStockData(ID, message, position);
    }
}