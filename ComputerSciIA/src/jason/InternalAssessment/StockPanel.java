package jason.InternalAssessment;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BoxLayout;
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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addStockButtons();
    }
    
    // Set up java swing panel
    @Override
    public Dimension getPreferredSize() {
    	int panelHeight = getComponentCount() * 160;
        return new Dimension(width, Math.max(height, panelHeight));
    }
    
    private void addStockButtons() {
    	for (int i = 1; i <= fm.getNumOfStocks(); i++) {
    		StockInfoButton temp = new StockInfoButton(fm.getStockName(i), null, i, backEnd);
    		add(temp);
    	}
    	repaint();
    	revalidate();
    }
    
    public void updateStockButtons() {
    	backEnd.killInfoPanelHidden();
    	removeAll();
    	addStockButtons();
    }
    
    public void addNewButton(int ID) {
    	StockInfoButton newStock = new StockInfoButton("Test", null, ID, backEnd);
    	add(newStock);
    	repaint();
    	revalidate();
    }
    
    @Override
    public void paintComponent(Graphics g) {
		// background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
	}
    
    private void initGUI() {
        setFocusable(true);
    }
}
