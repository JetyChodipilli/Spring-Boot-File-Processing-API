package com.ccp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileProcessingService {

    public String processExcelOrCsv(MultipartFile file, int startRow) throws Exception {
        String filename = file.getOriginalFilename();
        if (filename == null) {
            throw new Exception("File name is empty.");
        }
        if (filename.endsWith(".csv")) {
            return processCsv(file, startRow);
        } else if (filename.endsWith(".xlsx") || filename.endsWith(".xls")) {
            return processExcel(file, startRow);
        } else {
            throw new Exception("Unsupported file type. Please upload an Excel or CSV file.");
        }
    }
    
    private String processCsv(MultipartFile file, int startRow) throws Exception {
        List<List<String>> result = new ArrayList<>();
        
        try (InputStream is = file.getInputStream();
             InputStreamReader reader = new InputStreamReader(is);
             CSVReader csvReader = new CSVReader(reader)) {
             
            String[] nextLine;
            int rowNum = 0;
            while ((nextLine = csvReader.readNext()) != null) {
                if (rowNum >= startRow) {
                    List<String> rowValues = new ArrayList<>();
                    for (String value : nextLine) {
                        rowValues.add(value);
                    }
                    result.add(rowValues);
                }
                rowNum++;
            }
        }
        return formatData(result);
    }
    
    private String processExcel(MultipartFile file, int startRow) throws Exception {
        List<List<String>> data = new ArrayList<>();
        
        try (InputStream is = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(is)) {
            
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();
            int rowNum = 0;
            for (Row row : sheet) {
                if (rowNum >= startRow) {
                    List<String> rowData = new ArrayList<>();
                    for (Cell cell : row) {
                        rowData.add(formatter.formatCellValue(cell));
                    }
                    data.add(rowData);
                }
                rowNum++;
            }
        }
        return formatData(data);
    }
    
    private String formatData(List<List<String>> data) {
        // Formats the list of rows into a simple textual table.
        StringBuilder sb = new StringBuilder();
        for (List<String> row : data) {
            sb.append(String.join(" | ", row));
            sb.append("\n");
        }
        return sb.toString();
    }
    
 
    public void convertXmlToJson(MultipartFile xmlFile, String outputPath) throws Exception {
        // Read XML file content
        String xmlContent = new String(xmlFile.getBytes());
        XmlMapper xmlMapper = new XmlMapper();
        
        // This will convert the XML to a generic Java Object (Map/Pojo structure)
        Object xmlObject = xmlMapper.readValue(xmlContent, Object.class);
        
        // Convert the Java object to a pretty-printed JSON string
        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonContent = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(xmlObject);
        
        // Save the JSON content to the specified output path
        Files.write(new File(outputPath).toPath(), jsonContent.getBytes());
    }
}