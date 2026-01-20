package com.scm.SCM.Validators;

import java.nio.Buffer;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile,MultipartFile>{

    public static final long MAX_FILE_SIZE = 2 * 1024 * 1024; // 2 MB



    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if(file ==null || file.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File is required").addConstraintViolation();
            return false; // Consider empty file as valid, use @NotNull or @NotEmpty for mandatory check
        }

        if(file.getSize() > MAX_FILE_SIZE) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size exceeds 2MB limit").addConstraintViolation();
            return false;
        }
        

        return true;
    }
    }




