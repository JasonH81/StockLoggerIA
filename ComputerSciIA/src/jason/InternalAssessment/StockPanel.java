package jason.InternalAssessment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class StockPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int width = 300;
    private int height = 640;
    
    private JButton addStock = new JButton("Add Stock");
    private JToolBar toolBar = new JToolBar();
    
    private StockInfoButton firstStock;
    private StockInfoButton secondStock;
    
    public StockPanel(BackEnd backEnd) {
        initGUI();
        
        add(addStock, BorderLayout.PAGE_START);
        
        firstStock = new StockInfoButton("Microsoft", null, 1, backEnd);
        secondStock = new StockInfoButton("Tesla", null, 2, backEnd);
        add(firstStock);
        add(secondStock);
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
