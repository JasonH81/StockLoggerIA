package jason.InternalAssessment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
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
    
    private StockPanel stockPanel;
    private JLabel stockPrice;
    private JLabel stockNet;
    
    public StockInfoPanel(int i, StockPanel stockPanel) {
    	this.stockPanel = stockPanel;
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
        
        JPanel pricePanel = new JPanel();
        pricePanel.setBackground(Color.LIGHT_GRAY);
        pricePanel.setPreferredSize(new Dimension(500, 100));
        stockPrice = new JLabel();
        stockNet = new JLabel();
        stockPrice.setFont(new Font(Font.DIALOG, Font.BOLD, 32));
        stockNet.setFont(new Font(Font.DIALOG, Font.BOLD, 32));
        pricePanel.add(stockPrice);
        pricePanel.add(stockNet);
        add(pricePanel, BorderLayout.PAGE_END);
    }
    
    public void updateStockPrice(String price, String symbol) {
    	stockPrice.setText(symbol + ": $" + price);
    	int stockCount = Integer.parseInt(thirdField.getText());
    	double stockPriceOld = Double.parseDouble(fourthField.getText());
    	double netOld = stockCount * stockPriceOld;
    	double stockPriceNew = Double.parseDouble(price);
    	double netNew = stockCount * stockPriceNew;
    	double net = netNew - netOld;
    	
    	// Rounding logic
    	net*=10;
    	int intNet = (int) net;
    	net = intNet/10.0;
    	if (net < 0) {
    		stockNet.setForeground(Color.RED);
    		stockNet.setText("Net Profit: (" + Math.abs(net) + ")");
    	}
    	else {
    		stockNet.setForeground(Color.GREEN);
    		stockNet.setText("Net Profit: " + (net));
    	}
    }
    
    public void updateAnyStock() {
    	firstField.changedInput();
    	secondField.changedInput();
    	thirdField.changedInput();
    	fourthField.changedInput();
    }
   
    
    public void changeField(String message, int position) {
    	// ID is the general position in the file
    	// Position is the exact criteria to modify
    	// Message is what you replace it with
    	fileM.writeStockData(ID, message, position);
    }
}