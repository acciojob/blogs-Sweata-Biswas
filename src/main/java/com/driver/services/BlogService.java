package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        //find all blogs
         return    blogRepository1.findAll();
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
            Blog blog = new Blog();
            blog.setContent(content);
            blog.setTitle(title);
            long milliSecond = System.currentTimeMillis();

            blog.setPubDate(new Date(milliSecond));

        //updating the blog details

        //Updating the userInformation and changing its blogs
        User user = userRepository1.findById(userId).get();
        blog.setUser(user);
        List<Blog> blogList = user.getBlogList();
        blogList.add(blog);
        user.setBlogList(blogList);

        userRepository1.save(user);
    }

    public Blog findBlogById(int blogId){
        //find a blog
      return  blogRepository1.findById(blogId).get();
    }

    public void addImage(Integer blogId, String description, String dimensions){
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        //add an image to the blog after creating it
        Blog blog = findBlogById(blogId);
        image.setBlog(blog);
        List<Image> imgList = blog.getImageList();
        imgList.add(image);
        blog.setImageList(imgList);

        blogRepository1.save(blog);
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        if(blogRepository1.findById(blogId).get()==null)return;

        blogRepository1.deleteById(blogId);
    }
}
