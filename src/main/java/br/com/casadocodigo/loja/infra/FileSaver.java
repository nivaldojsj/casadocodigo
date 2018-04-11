package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/* @Component -> faz uma classe ser conhecida e gerenciada pelo Spring
 */
@Component
public class FileSaver {
	// Faz a injeção
	@Autowired
    private HttpServletRequest request;
	
	public String write(String baseFolder, MultipartFile file) {
	    try {
	        String realPath = request.getServletContext().getRealPath("/"+baseFolder);
	        String path = realPath + "/" + file.getOriginalFilename();
	        file.transferTo(new File(path));
	        
	        return baseFolder + "/" + file.getOriginalFilename();
	    } catch (IllegalStateException | IOException e) {
	        throw new RuntimeException(e);
	    }
	}
}
