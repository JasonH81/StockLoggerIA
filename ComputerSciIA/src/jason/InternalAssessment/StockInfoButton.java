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
	private int width = 300;
	private int height = 150;
	
	public StockInfoButton(String stockName, Icon icon, int ID, BackEnd backEnd) {
		super(stockName, icon);
		this.stockName = stockName;
		this.backEnd = backEnd;
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setInformation(ID);
			}
		});
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.RED);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAndShiftStocks(ID);
			}
		});
		 this.add(deleteButton, BorderLayout.PAGE_END);
	}
	
	public Dimension getPreferredSize() {
        Dimension size = new Dimension(width, height);
        return size;
    }
	
	public String getStockName() {
		return stockName;
	}
	
	private void deleteAndShiftStocks(int id) {
		backEnd.deleteStockButton(id);
	}
	
	private void setInformation(int id) {
		backEnd.createInfoPanel(id);
	}
}
