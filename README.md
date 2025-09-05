# Spring Boot File Processing API
##Overview
This is a Java Spring Boot application that provides REST APIs for processing uploaded files. It includes endpoints to:
* Upload and process Excel or CSV files, with an optional startRow parameter to skip initial rows.
* Convert XML files to JSON and save the output to a specified file path.
The application uses Spring Boot's REST controller to handle file uploads and delegates processing to a FileProcessingService.
## Features
* Excel/CSV Processing: Accepts Excel or CSV files via a POST request, processes them starting from a specified row, and returns the processed output as a string.
* XML-to-JSON Conversion: Accepts XML files, converts them to JSON, and saves the output to a specified file path.
* Error handling for invalid files or processing failures with meaningful error messages.
* Built with Spring Boot for easy setup and scalability.

## Technologies Used

* Java 17
* Spring Boot 3.x
* Maven
* Spring Web (for REST APIs and file upload handling)

Prerequisites

Java 17 or higher
Maven 3.6+
A running Spring Boot application environment
(Optional) A tool like Postman or cURL for testing API endpoints

Project Structure
src/main/java/com/ccp/
├── controller/
│   └── FileProcessingController.java  # REST controller for file upload endpoints
├── service/
│   └── FileProcessingService.java     # Service interface for file processing logic
src/main/resources/
└── application.properties             # Application configuration

Setup Instructions

Clone the Repository:
git clone <repository-url>
cd spring-boot-file-processing


Configure Application:

Ensure application.properties in src/main/resources is configured if needed (e.g., server port):server.port=8080




Build the Project:
mvn clean install


Run the Application:
mvn spring-boot:run



API Endpoints
1. Upload and Process Excel/CSV File

Endpoint: POST /api/upload/excel-csv
Description: Uploads an Excel or CSV file and processes it starting from the specified row (default: 0).
Parameters:
file: Multipart file (Excel or CSV) [Required]
startRow: Integer, row to start processing from (default: 0) [Optional]


Request Example (using cURL):curl -X POST "http://localhost:8080/api/upload/excel-csv?startRow=2" \
     -F "file=@/path/to/sample.csv"


Response:
Success: 200 OK with processed output as a string
Error: 500 Internal Server Error with error message


Sample Success Response:"Processed data: [output from FileProcessingService]"


Sample Error Response:"Error processing file: Invalid file format"



2. Convert XML to JSON

Endpoint: POST /api/upload/xml
Description: Uploads an XML file, converts it to JSON, and saves the output to the specified path.
Parameters:
file: Multipart file (XML) [Required]
outputPath: String, file path to save the JSON output (e.g., /tmp/output.json) [Required]


Request Example (using cURL):curl -X POST "http://localhost:8080/api/upload/xml?outputPath=/tmp/output.json" \
     -F "file=@/path/to/sample.xml"


Response:
Success: 200 OK with confirmation of JSON file generation
Error: 500 Internal Server Error with error message


Sample Success Response:"JSON file generated at: /tmp/output.json"


Sample Error Response:"Error processing XML file: Invalid XML format"



Usage

Start the Spring Boot application (mvn spring-boot:run).
Use a tool like Postman or cURL to test the endpoints:
For Excel/CSV, upload a file with the startRow parameter if needed.
For XML, provide a valid XML file and a writable output path for the JSON.


Check the response for processed output or error messages.
For XML-to-JSON, verify the generated JSON file at the specified outputPath.

Sample Files

Excel/CSV Example (sample.csv):id,name,email
1,John Doe,john.doe@example.com
2,Jane Smith,jane.smith@example.com


XML Example (sample.xml):<user>
    <id>1</id>
    <name>John Doe</name>
    <email>john.doe@example.com</email>
</user>



Notes

The FileProcessingService is assumed to contain the logic for processing Excel/CSV files and converting XML to JSON. Ensure it is implemented and injected correctly.
The application assumes the server has write permissions for the outputPath specified in the XML-to-JSON endpoint.
Add validation in FileProcessingService to check file formats (e.g., .csv, .xlsx, .xml) to avoid errors.

Troubleshooting

File Upload Fails: Ensure the file is valid and matches the expected format (CSV/Excel for /excel-csv, XML for /xml).
Permission Denied: Verify the server has write access to the outputPath for XML-to-JSON conversion.
Service Errors: Check console logs for exceptions thrown by FileProcessingService.
Port Conflict: Update server.port in application.properties if 8080 is in use.

