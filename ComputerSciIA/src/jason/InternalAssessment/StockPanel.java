package jason.InternalAssessment;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StockPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int width = 300;
    private int height = 640;
    
    BackEnd backEnd; 
    FileManager fm = new FileManager();
    
    public StockPanel(BackEnd backEnd) {
        initGUI();
        this.backEnd = backEnd;
        //firstStock = new StockInfoButton("Microsoft", null, 1, backEnd);
        //secondStock = new StockInfoButton("Tesla", null, 2, backEnd);
        //add(firstStock);
        //add(secondStock);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        addStockButtons();
    }
    
    // Set up java swing panel
    @Override
    public Dimension getPreferredSize() {
    	Dimension size = new Dimension(width, getComponentCount()*100);
        return size;
    }
    
    private void addStockButtons() {
    	for (int i = 1; i <= fm.getNumOfStocks(); i++) {
    		StockInfoButton temp = new StockInfoButton(fm.getStockName(i), null, i, backEnd);
    		System.out.println("Made new stock info button with ID: " + i);
    		add(temp);
    	}
    	repaint();
    	revalidate();
    	System.out.println("Added Stock Buttons from stock panel");
    }
    
    public void updateStockButtons() {
    	System.out.println("Updated stock buttons");
    	backEnd.killInfoPanel();
    	removeAll();
    	addStockButtons();
    }
    
    public void addNewButton(int ID) {
    	System.out.println("Added new stock button");
    	StockInfoButton newStock = new StockInfoButton("Test", null, ID, backEnd);
    	add(newStock);
    	repaint();
    	revalidate();
    }
    
    @Override
    public void paintComponent(Graphics g) {
		// background
    	super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
	}
    
    private void initGUI() {
        setFocusable(true);
    }
}
