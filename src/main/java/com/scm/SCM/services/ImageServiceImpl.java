package com.scm.SCM.services;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.SCM.helper.AppConstants;

@Service
public class ImageServiceImpl implements imageService{

    @Autowired
    private Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile contactImage,String filename) {
       //upload image on the server and return the image URL
       
       try {
        byte[] data = new byte[contactImage.getInputStream().available()];

        contactImage.getInputStream().read(data);
        cloudinary.uploader().upload(data,ObjectUtils.asMap(
            "public_id",filename
            
        ));
        return this.getUrlFromPublicId(filename);
       } catch (IOException e) {
       
        e.printStackTrace();
          return null;
       }
      
     
    }

    @Override
    public String getUrlFromPublicId(String publicId) {
      
        return cloudinary.url().transformation(
            new Transformation<>().width(AppConstants.CONTACT_IMAGE_WIDTH).height(AppConstants.CONTACT_IMAGE_HEIGHT).crop(AppConstants.CONTACT_IMAGE_CROP)
        ).generate(publicId);
    }

    

}


