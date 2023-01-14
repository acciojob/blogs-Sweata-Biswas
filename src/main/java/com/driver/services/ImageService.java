package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;
    @Autowired
    BlogRepository blogRepository;
    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        List<Image> imgList = blog.getImageList();
        if(imgList == null){
            imgList = new ArrayList<>();
        }
        imgList.add(image);
        blog.setImageList(imgList);

        blogRepository.save(blog);
        return  image;
    }

    public void deleteImage(Image image){
        imageRepository2.delete(image);
    }

    public Image findById(int id) {
           return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
         if(screenDimensions.split("X").length == 2|| image!= null ){

             String givenDimension = image.getDimensions();

                 int screenSize = Integer.parseInt(screenDimensions.split("X")[0]) * Integer.parseInt(screenDimensions.split("X")[1]);
                 int givenSize = Integer.parseInt(givenDimension.split("X")[0]) * Integer.parseInt(givenDimension.split("X")[1]);
                 return screenSize / givenSize;
         }
         return 0;
    }
}
