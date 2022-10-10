package com.kisaragi.app.cloudinary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class cloudinaryService {

	Cloudinary cloudinary;
	
	private Map<String, String> valuesMap = new HashMap<>();

	public cloudinaryService() {
		valuesMap.put("cloud_name", "dbwrei5py");
		valuesMap.put("api_key", "686314625655572");
		valuesMap.put("api_secret", "8zjYs-i59ynSBoM2uca2TZrgpHM");
		cloudinary = new Cloudinary(valuesMap);
	}
	
	public Map upload(MultipartFile multipartFile) throws IOException {
		File file = convert(multipartFile);
		Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		file.delete();
		return result;
	}
	
	public Map delete(String id) throws IOException{
		Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
		return result;
	}
	
	private File convert(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartFile.getBytes());
		fo.close();
		return file;
	}
	
	
}
