package com.achraf.videogames.services;
import com.achraf.videogames.entities.Image;
import com.achraf.videogames.entities.Videogame;
import com.achraf.videogames.repos.ImageRepository;
import com.achraf.videogames.repos.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    VideogameService videogameService;

    @Autowired
    VideogameRepository videogameRepository;

    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
 /*Ce code commenté est équivalent au code utilisant le design pattern Builder
 * Image image = new Image(null, file.getOriginalFilename(),
 file.getContentType(), file.getBytes(), null);
 return imageRepository.save(image);*/
        return imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(file.getBytes()).build() );
    }
    @Override
    public Image getImageDetails(Long id) throws IOException{
        final Optional<Image> dbImage = imageRepository. findById (id);
        return Image.builder()
                .idImage(dbImage.get().getIdImage())
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(dbImage.get().getImage()).build() ;
    }
    @Override
    public ResponseEntity<byte[]> getImage(Long id) throws IOException{
        final Optional<Image> dbImage = imageRepository. findById (id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(dbImage.get().getImage());
    }
    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Image uploadImageVid(MultipartFile file, Long idVid) throws IOException {
        Videogame v = new Videogame();
        v.setIdVideogame(idVid);
        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(file.getBytes())
                .videogame(v) // Set the Videogame reference
                .build();

        return imageRepository.save(image);
    }



    @Override
    public List<Image> getImagesParVid(Long prodId) {
        Videogame v = videogameRepository.findById(prodId).get();
        return v.getImages();
    }
}
