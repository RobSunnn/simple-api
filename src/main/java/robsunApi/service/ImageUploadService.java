//package robsunApi.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.UUID;
//
//@Service
//public class ImageUploadService {
//
//    public String uploadToTemporaryStorage(MultipartFile file) throws IOException {
//        // Define the upload directory depending on the environment (Azure or local)
//        String uploadDirectory = System.getenv("HOME") != null ? System.getenv("HOME") + "/site/wwwroot/uploads/" : "C:/test/";
//
//        File uploadDir = new File(uploadDirectory);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdirs();
//        }
//
//        // Create a unique file name
//        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
//        Path filePath = Paths.get(uploadDirectory, fileName);
//
//        // Save the file
//        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//        // Return the relative path to the uploaded file
//        return filePath.toString();
//    }
//}
