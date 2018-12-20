package ua.cosmetology.Service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	
	String saveFile(MultipartFile file);
	
	Resource loadFile(String fileName);

}
