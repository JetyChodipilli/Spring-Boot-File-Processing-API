package com.ccp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ccp.service.FileProcessingService;

@RestController
@RequestMapping("/api")
public class FileProcessingController {

    private final FileProcessingService processingService;

    @Autowired
    public FileProcessingController(FileProcessingService processingService) {
        this.processingService = processingService;
    }
    
    /* POST /api/upload/excel-csv?startRow=2 with file as form-data.
     */
    @PostMapping("/upload/excel-csv")
    public ResponseEntity<?> uploadExcelCsvFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "startRow", defaultValue = "0") int startRow) {
        try {
            String output = processingService.processExcelOrCsv(file, startRow);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error processing file: " + e.getMessage());
        }
    }

    /* POST /api/upload/xml?outputPath=/tmp/output.json  with file as form-data.
     */
    @PostMapping("/upload/xml")
    public ResponseEntity<?> uploadXmlFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("outputPath") String outputPath) {
        try {
            processingService.convertXmlToJson(file, outputPath);
            return ResponseEntity.ok("JSON file generated at: " + outputPath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error processing XML file: " + e.getMessage());
        }
    }
}