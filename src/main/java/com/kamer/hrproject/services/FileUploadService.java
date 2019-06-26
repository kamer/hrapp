package com.kamer.hrproject.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Blob;

@Service
public class FileUploadService {

    public Blob handleUpload(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Blob blob = new SerialBlob(bytes);
            return blob;
        } catch (Exception e) {
            return null;
        }
    }


    // TODO: change this
    public String serveFile(Blob blobFile) {
        try {
            int blobLength = (int) blobFile.length();
            byte[] blobAsBytes = blobFile.getBytes(1, blobLength);
            FileOutputStream fos = new FileOutputStream("resume.pdf");
            fos.write(blobAsBytes);
            fos.close();
            File file = new File("resume.pdf");
            URL url = file.toURI().toURL();
            return url.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
