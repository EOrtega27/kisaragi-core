package com.kisaragi.app.cloudinary;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cloudinary")
public class cloudinaryTestController {
	@Autowired
	cloudinaryService cloudinaryService;
	
	@PostMapping("/upload")
	public ResponseEntity<Map> upload(@RequestParam MultipartFile file) throws IOException{
		Map result = cloudinaryService.upload(file);
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map> delete(@PathVariable String id) throws IOException{
		Map result = cloudinaryService.delete(id);
		return new ResponseEntity(result, HttpStatus.OK);
	}

}
