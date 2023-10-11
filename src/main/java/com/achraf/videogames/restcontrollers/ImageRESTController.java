package com.achraf.videogames.restcontrollers;


import com.achraf.videogames.entities.Image;
import com.achraf.videogames.entities.Videogame;
import com.achraf.videogames.services.ImageService;
import com.achraf.videogames.services.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")
public class ImageRESTController {
    @Autowired
    ImageService imageService ;
    @Autowired
    VideogameService videogameService;



    @RequestMapping(value = "/upload" , method = RequestMethod.POST)
    public Image uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        return imageService.uploadImage(file);
    }
    @RequestMapping(value = "/get/info/{id}" , method = RequestMethod.GET)
    public Image getImageDetails(@PathVariable("id") Long id) throws IOException {
        return imageService.getImageDetails(id) ;
    }
    @RequestMapping(value = "/load/{id}" , method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException
    {
        return imageService.getImage(id);
    }


    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
    public void deleteImage(@PathVariable("id") Long id){
        imageService.deleteImage(id);
    }
    @RequestMapping(value="/update",method = RequestMethod.PUT)
    public Image UpdateImage(@RequestParam("image")MultipartFile file) throws IOException {
        return imageService.uploadImage(file);
    }


    @PostMapping(value = "/uploadImageVid/{idVid}" )
    public Image uploadMultiImages(@RequestParam("image")MultipartFile file,
                                   @PathVariable("idVid") Long idVid)
            throws IOException {
        return imageService.uploadImageVid(file,idVid);
    }
    @RequestMapping(value = "/getImagesVid/{idVid}" ,
            method = RequestMethod.GET)
    public List<Image> getImagesVid(@PathVariable("idVid") Long idVid)
            throws IOException {
        return imageService.getImagesParVid(idVid);
    }

    @RequestMapping(value = "/uploadFS/{id}" , method = RequestMethod.POST)
    public void uploadImageFS(@RequestParam("image") MultipartFile
                                      file,@PathVariable("id") Long id) throws IOException {
        Videogame v = videogameService.getVideogame(id);
        v.setImagePath(id+".jpg");

        Files.write(Paths.get(System.getProperty("user.home")+"/images/"+v.getImagePath())
                , file.getBytes());
        videogameService.saveVideogame(v);

    }
    @RequestMapping(value = "/loadfromFS/{id}" ,
            method = RequestMethod.GET,
            produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageFS(@PathVariable("id") Long id) throws IOException {

        Videogame v = videogameService.getVideogame(id);
        return
                Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/images/"+v.getImagePath()));
    }


}