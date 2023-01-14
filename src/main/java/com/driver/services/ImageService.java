package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimension(dimensions);
        image.setBlog(blog);
        blog.getImageList().add(image);
        imageRepository2.save(image);
        return  image;
    }

    public void deleteImage(Image image){
        imageRepository2.delete(image);
    }

    public Image findById(int id) {
            return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(int id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
         if(imageRepository2.findById(id).get() != null){
             Image image = imageRepository2.findById(id).get();
             String givenDimension = image.getDescription();
             int screenSize = Integer.parseInt(screenDimensions.split("X")[0])*Integer.parseInt(screenDimensions.split("X")[1]);
            int givenSize = Integer.parseInt(givenDimension.split("X")[0])*Integer.parseInt(givenDimension.split("X")[1]);
            return screenSize/givenSize;
         }
         return 0;
    }
}
