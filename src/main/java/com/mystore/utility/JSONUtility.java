package com.mystore.utility;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class JSONUtility {
	
	private static final String JSON_FILE_PATH = System.getProperty("user.dir") + "/TestData/accountCreationData.json";

    public static Object[][] getTestData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, String>> dataList = mapper.readValue(new File(JSON_FILE_PATH), new TypeReference<>() {});
            
            Object[][] data = new Object[dataList.size()][1];
            for (int i = 0; i < dataList.size(); i++) {
                data[i][0] = dataList.get(i); // Each row = 1 Map
            }
            return data;

        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + e.getMessage(), e);
        }
    }

}
