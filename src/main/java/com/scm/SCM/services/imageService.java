package com.scm.SCM.services;

import org.springframework.web.multipart.MultipartFile;

public interface imageService {

     String uploadImage(MultipartFile contactImage,String filename);
        
        String getUrlFromPublicId(String publicId);

}
