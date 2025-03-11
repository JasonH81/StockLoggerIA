package jason.InternalAssessment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FileManager {
	
	BufferedWriter out;
	public static String fileName = "Untitled2.txt";
	private File file;
	
	public FileManager() {
		file = new File(fileName);
	}
	
	public void writeStockData(int ID, String message, int position) {
		ArrayList<String> lines = new ArrayList<>();
		try {
			// Reads all lines in the file
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line;
			while((line = in.readLine())!=null) {
				lines.add(line);
			}
			
			int beginIndex = ID * 6 - 6;
			int target = beginIndex + position + 2;
			
			// Temporary logging
			//System.out.println("Position: " + position + "\nTarget Index: " + target + "\nTarget: " + lines.get(target) + "\nMessage: " + message);
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			lines.set(target, message);
			for (String tempLine : lines) {
				out.write(tempLine);
	            out.newLine();
			}
			out.close();
			
			in.close();
		}
		catch (IOException e) {
			String displayMessage = "Could not find file " + fileName + ".";
			int quit = JOptionPane.showConfirmDialog(null, displayMessage, "Quit Program?", JOptionPane.YES_NO_OPTION);
			if (quit == JOptionPane.YES_OPTION) {		
				System.exit(0);
		}	
	}
}
 	
	public ArrayList<String> readStockData(int ID) {
		ArrayList<String> lines = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			
			// Skip first line (number of stocks) & second line (** separators)
			in.readLine();
			in.readLine();
			
			// Set the correct offset in the code to start reading
			if (ID!=1) {
				for (int i = 0; i<ID*6 - 6; i++) {
					in.readLine();
				}
			}
			String line;
	        while ((line = in.readLine()) != null && !line.equals("**")) {
	        	//System.out.println(line);
	            lines.add(line);
	        }
	        in.close();
		} catch (FileNotFoundException e) {
			String message = "Could not find file " + fileName + ".";
			int quit = JOptionPane.showConfirmDialog(null, message, "Quit Program?", JOptionPane.YES_NO_OPTION);
			if (quit == JOptionPane.YES_OPTION) {
				System.exit(0);
				}
		} catch (IOException e) {
			String message = "Could not read file " + fileName + ".";
			int quit = JOptionPane.showConfirmDialog(null, message, "Quit Program?", JOptionPane.YES_NO_OPTION);
			if (quit == JOptionPane.YES_OPTION) {
				System.exit(1);
				}
			}
		return lines;
	}
}
