package jason.InternalAssessment;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StockPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int width = 300;
    private int height = 640;
    
    private StockInfoButton firstStock;
    private StockInfoButton secondStock;
    
    BackEnd backEnd;
    
    public StockPanel(BackEnd backEnd) {
        initGUI();
        this.backEnd = backEnd;
        //firstStock = new StockInfoButton("Microsoft", null, 1, backEnd);
        //secondStock = new StockInfoButton("Tesla", null, 2, backEnd);
        //add(firstStock);
        //add(secondStock);
//        addComponentListener(new ComponentListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		
//        	}
//        });
    }
    
    // Set up java swing panel
    @Override
    public Dimension getPreferredSize() {
        Dimension size = new Dimension(width, height);
        return size;
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
