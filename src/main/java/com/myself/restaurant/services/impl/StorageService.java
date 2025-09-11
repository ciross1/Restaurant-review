package com.myself.restaurant.services.impl;

import com.myself.restaurant.exceptions.StorageException;
import com.myself.restaurant.services.StorageServiceInterface;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class StorageService implements StorageServiceInterface {

    // @Value injects a property from your application.properties or application.yml.
    // If app.storage.location is defined → use that value.
    //If not → use default "uploads".
    @Value("${app.storage.location:uploads}")
    private String storageLocation;

     // This will hold the filesystem path where files are stored
    private Path rootLocation;

    @PostConstruct  // PostConstruct means:  “After Spring finishes creating this bean and wiring its
    // dependencies, call this method once for setup/initialization.
    public void init(){
        // Converts a String into a Path object.
        rootLocation = Paths.get(storageLocation);
        try {
            // Ensures the directory actually exists.
            //If it doesn’t exist → create it.
            //If it already exists → do nothing.
            Files.createDirectories(rootLocation);
        } catch (
                IOException e) {
            throw new StorageException("Could not initialize storage exception", e);
        }
    }

    @Override
    public String store(MultipartFile file, String fileName) {
        if (file.isEmpty()){
            throw new StorageException("Cannot save an empty file");
        }
        // Now the variable extension holds just the file extension.
        //Example: "jpg", "png", "pdf", etc
        // In plain English
        //“Take the uploaded file, get its original name,
        // and extract the extension (like 'jpg' or 'pdf') so you know the file type.”

      String extension =   StringUtils.getFilenameExtension(file.getOriginalFilename());
        String finalFileName = fileName + "." + extension;


        // Normalize → prevents weird paths like ../ from breaking your storage logic.
        //Absolute path → ensures you have the full path, not a relative one
        // .toAbsolutePath() ensures the path becomes absolute, i.e., a fixed, full address.
        //Resolve → safely combines the root folder and filename.
       Path destinationFile =
               rootLocation.resolve(Paths.get(finalFileName)
               .normalize()
                       .toAbsolutePath());

        // if the parent directory of our destination file is not equal to rootLocation then it will throw an exception
       if(!destinationFile.getParent().equals(rootLocation.toAbsolutePath())){

           throw new StorageException("Cannot storage file outside  specified directory");
       }

       try(InputStream inputStream = file.getInputStream()){
           // Take the file data from the inputStream and write it to destinationFile.
           // If a file with the same name already exists, overwrite it.
           Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

       } catch (
               IOException e) {
           throw new StorageException("Failed to store file", e);
       }

       return finalFileName;
    }






    // This method tries to load a file as a Spring Resource. If the file exists or can be read,
    // it returns it wrapped in an Optional. If not, it returns an empty Optional.

    @Override
    public Optional<Resource> loadAsResource(String fileName) throws MalformedURLException {

        // The .resolve() method combines paths safely.
        // Think of it as “append this file or folder name to the current path.”
        Path file = rootLocation.resolve(fileName);


        // file.toUri()
        //file is a Path object (like /uploads/picture.png).
        //.toUri() converts the file path into a URI (Uniform Resource Identifier), which is a standardized way to represent a resource.

        // By passing the URI, UrlResource now knows where the file is and allows you to:
        //Check if it exists
        //Read its contents
        //Serve it over HTTP (like in a REST API)
        Resource resource = new UrlResource(file.toUri());

        if(resource.exists() || resource.isReadable()){
            return Optional.of(resource);
        }else{
            return Optional.empty();
        }
    }
}
