package jason.InternalAssessment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class BackEnd extends JFrame {

private static final long serialVersionUID = 1L;
private StockPanel stockPanel;
private JPanel mainPanel;
private JScrollPane scrollPane;
private StockInfoPanel stockInfoPanel;
private JToolBar toolBar = new JToolBar("Menu");
private JButton addButton = new JButton("Add");
private JButton backButton = new JButton("Back");
    
    public BackEnd() {
        initGUI();
        
        // Set up program swing utility details
        setTitle("Stock App");
		setResizable(false);
		setLocationRelativeTo(null);
		
		setMinimumSize(new Dimension(300, 640));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
    }
    
    private void initGUI() {
        // main panel
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.RED);
		mainPanel.setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
		
		stockPanel = new StockPanel(this);
		
		scrollPane = new JScrollPane(stockPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		
		// game panel
		mainPanel.add(scrollPane, BorderLayout.WEST);
		mainPanel.add(toolBar, BorderLayout.NORTH);
		addToolBars();
		pack();
    }
    
    private void addToolBars() {
    	// Adding new stock button
    	addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 FileManager fm = new FileManager();
				 int ID = fm.writeNewTemplate();
				 stockPanel.addNewButton(ID);
			}
		});
    	toolBar.add(addButton);
    	backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				killInfoPanel();
			}
		});
   	toolBar.add(backButton);
    }
    
    public void createInfoPanel(int id) {
    	if (stockInfoPanel != null) {
            mainPanel.remove(stockInfoPanel);
            System.out.println("Killed current info panel");
        }
    	stockInfoPanel = new StockInfoPanel(id, stockPanel);
    	stockPanel.updateStockButtons();
    	mainPanel.add(stockInfoPanel, BorderLayout.CENTER);
    	setMinimumSize(new Dimension(800, 640));
    	setLocationRelativeTo(null);
    	mainPanel.repaint();
    	mainPanel.revalidate();
    	pack();
    }
    
    public void killInfoPanelHidden() {
    	if (stockInfoPanel!=null) {
    		mainPanel.remove(stockInfoPanel);
    	}
    	setMinimumSize(new Dimension(300, 640));
    	//mainPanel.repaint();
    	//mainPanel.revalidate();
    	//pack();
    	setLocationRelativeTo(null);
    }
    
    public void deleteStockButton(int id) {
    	FileManager fm = new FileManager();
    	fm.deleteStockButton(id);
//    	if (stockInfoPanel != null) {
//    		killInfoPanel();
//    	}
    	stockPanel.updateStockButtons();
    }
    
    public void killInfoPanel() {
    	mainPanel.remove(stockInfoPanel);
    	setMinimumSize(new Dimension(300, 640));
    	mainPanel.repaint();
    	mainPanel.revalidate();
    	pack();
    	setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BackEnd();
			}
		});
	}

}
