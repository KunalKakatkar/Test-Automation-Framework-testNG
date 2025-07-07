package com.mystore.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtility {
	
	  public static Object[][] getTestData(String filePath) {
	        List<Object[]> data = new ArrayList<>();

	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            boolean isFirstLine = true;

	            while ((line = br.readLine()) != null) {
	                if (isFirstLine) {
	                    isFirstLine = false; // Skip header
	                    continue;
	                }

	                String[] values = line.split(",", -1); // Include empty cells
	                data.add(values);
	            }

	        } catch (IOException e) {
	            throw new RuntimeException("Error reading CSV file: " + e.getMessage(), e);
	        }

	        return data.toArray(new Object[0][]);
	    }

}
