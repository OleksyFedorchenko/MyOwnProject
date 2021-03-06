package ua.cosmetology.ServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import ua.cosmetology.Service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService{

	private final String PATH = System.getProperty("user.dir");
	private final String SEPARATOR = System.getProperty("file.separator");
	
	private final Path fileStorageLocation;
	
	 public FileStorageServiceImpl() {
		
		 String uploadDir = PATH + SEPARATOR + "uploads";
		 System.out.println(uploadDir);
		 
		 this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
		 try {
			 
			 Files.createDirectories(this.fileStorageLocation);
			 
		 } catch (Exception e) {
			 e.printStackTrace();
			 
		 }
	}
	
	@Override
	public String saveFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Path targetLocation = null;
		try {
			
			targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fileName;
	}

	@Override
	public Resource loadFile(String fileName) {
		try {
			
			Path filePath = this.fileStorageLocation.resolve(fileName);
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
				
				return resource;
			}
			
		} catch (Exception e) {
           e.printStackTrace();
		}
		
		
		return null;
	}

}
