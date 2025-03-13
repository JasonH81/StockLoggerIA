package jason.InternalAssessment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

public class StockInfoButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	private String stockName;
	private int ID;
	private BackEnd backEnd;
	
	public StockInfoButton(String stockName, Icon icon, int ID, BackEnd backEnd) {
		super(stockName, icon);
		this.stockName = stockName;
		this.backEnd = backEnd;
		setPreferredSize(new Dimension(200, 100));
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setInformation(ID);
			}
		});
		JButton exitButton = new JButton("Exit");
		exitButton.setBackground(Color.RED);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeInformation();
			}
		});
		// this.setLayout();
		 this.add(exitButton, BorderLayout.PAGE_END);
	}
	
	public String getStockName() {
		return stockName;
	}
	
	public int getID() {
		return ID;
	}
	
	private void removeInformation() {
		backEnd.killInfoPanel();
	}
	
	private void setInformation(int id) {
		backEnd.createInfoPanel(id);
	}
}
