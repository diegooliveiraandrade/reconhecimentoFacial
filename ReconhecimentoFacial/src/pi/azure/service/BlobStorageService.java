package pi.azure.service;

import java.net.URI;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import pi.azure.model.FaceDTO;

public interface BlobStorageService {

    void init();

    String store(FaceDTO faceDto);
    
    String store(MultipartFile file);
    
    void setBlobMetaData(String url, String key, String value);
    
    String getBlobByMetaData(String key, String value);

    Stream<Path> loadAll();
      
    String getBlobUrl(String filename);
    
    String getFileNameFromBlobURI(URI uri, String containerName);
}
