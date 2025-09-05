# 🌟 Spring Boot File Processing API

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-green.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)
![Build](https://img.shields.io/badge/Build-Maven-success.svg)

> A RESTful API built with Spring Boot to process Excel/CSV and XML files with robust error handling and clean architecture.

---

## 📖 Overview

This Java Spring Boot application provides REST APIs for:

- 📄 Uploading and processing Excel or CSV files, with an optional `startRow` parameter to skip initial rows.
- 🔁 Converting XML files to JSON and saving the output to a specified file path.

The application uses a REST controller to handle file uploads and delegates processing to a service layer.

---

## 🚀 Features

- ✅ **Excel/CSV Processing**: Upload via POST, process from a specified row, return output as a string.
- 🔄 **XML-to-JSON Conversion**: Upload XML, convert to JSON, save to disk.
- 🛡️ **Error Handling**: Meaningful messages for invalid files or failures.
- ⚙️ **Spring Boot Powered**: Easy setup, scalable architecture.

---

## 🧰 Technologies Used

- Java 17  
- Spring Boot 3.x  
- Maven  
- Spring Web (REST APIs & file upload)

---

## 📋 Prerequisites

- Java 17+  
- Maven 3.6+  
- Spring Boot runtime environment  
- *(Optional)* Postman or cURL for testing

---

## 📁 Project Structure

```text
src/main/java/com/ccp/
├── controller/
│   └── FileProcessingController.java    # REST controller
├── service/
│   └── FileProcessingService.java       # Business logic interface

src/main/resources/
└── application.properties               # Config file
```

---

## ⚙️ Setup Instructions

### 📥 Clone the Repository

```bash
git clone https://github.com/JetyChodipilli/Spring-Boot-File-Processing-API
cd spring-boot-file-processing
```

### 🔧 Configure Application

Edit `src/main/resources/application.properties` if needed:

```properties
server.port=8080
```

### 🛠️ Build the Project

```bash
mvn clean install
```

### ▶️ Run the Application

```bash
mvn spring-boot:run
```

---

## 📡 API Endpoints

### 1️⃣ Upload and Process Excel/CSV

- **Endpoint**: `POST /api/upload/excel-csv`  
- **Params**:
  - `file` (Multipart) — Required  
  - `startRow` (Integer) — Optional (default: 0)

#### 🧪 Example (cURL)

```bash
curl -X POST "http://localhost:8080/api/upload/excel-csv?startRow=2" \
     -F "file=@/path/to/sample.csv"
```

#### ✅ Response

- `200 OK`: `"Processed data: [output]"`
- `500 Error`: `"Error processing file: Invalid format"`

---

### 2️⃣ Convert XML to JSON

- **Endpoint**: `POST /api/upload/xml`  
- **Params**:
  - `file` (Multipart) — Required  
  - `outputPath` (String) — Required

#### 🧪 Example (cURL)

```bash
curl -X POST "http://localhost:8080/api/upload/xml?outputPath=/tmp/output.json" \
     -F "file=@/path/to/sample.xml"
```

#### ✅ Response

- `200 OK`: `"JSON file generated at: /tmp/output.json"`
- `500 Error`: `"Error processing XML file: Invalid format"`

---

## 🧪 Usage Guide

- Start the app: `mvn spring-boot:run`
- Use Postman or cURL to test endpoints
- For Excel/CSV: upload file with optional `startRow`
- For XML: provide valid file and writable `outputPath`
- Check responses for output or errors
- Verify JSON file at specified path

---

## 📂 Sample Files

### 📄 CSV Example (`sample.csv`)

```csv
id,name,email
1,John Doe,john.doe@example.com
2,Jane Smith,jane.smith@example.com
```

### 📄 XML Example (`sample.xml`)

```xml
<user>
    <id>1</id>
    <name>John Doe</name>
    <email>john.doe@example.com</email>
</user>
```

---

## 📝 Notes

- Ensure `FileProcessingService` is implemented and injected correctly.
- Server must have write access to `outputPath`.
- Add validation for file formats (`.csv`, `.xlsx`, `.xml`) to avoid errors.

---

## 🛠️ Troubleshooting

| Issue                  | Solution                                                                 |
|------------------------|--------------------------------------------------------------------------|
| File Upload Fails      | Check file format and endpoint match                                     |
| Permission Denied      | Ensure server can write to `outputPath`                                  |
| Service Errors         | Check logs for exceptions from `FileProcessingService`                   |
| Port Conflict          | Change `server.port` in `application.properties`                         |

---

## 📜 License

This project is licensed under the [MIT License](LICENSE).

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you’d like to change.
