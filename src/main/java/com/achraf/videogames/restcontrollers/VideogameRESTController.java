package com.achraf.videogames.restcontrollers;

import java.io.IOException;
import java.util.List;

import com.achraf.videogames.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.achraf.videogames.services.VideogameService;
import com.achraf.videogames.entities.Videogame;
import org.springframework.web.multipart.MultipartFile;
import com.achraf.videogames.entities.Image;
@RestController
@RequestMapping("/api")
@CrossOrigin
public class VideogameRESTController {
@Autowired
VideogameService videogameService;

@Autowired
ImageService imageService;
    @RequestMapping(path="all",method =RequestMethod.GET)
public List<Videogame> getAllVideogames(){
	return videogameService.getAllVideogames();
}

@RequestMapping(value="/getbyid/{id}",method = RequestMethod.GET)
public Videogame getVideogameById(@PathVariable("id") Long id) {
return videogameService.getVideogame(id);
 }


@RequestMapping(path="/addvid",method = RequestMethod.POST)
public Videogame createVideogame
        (@RequestBody Videogame videogame) throws IOException {
    return videogameService.saveVideogame(videogame);
}

@RequestMapping(path="/updatevid",method = RequestMethod.PUT)
public Videogame updateVideogame(@RequestBody Videogame videogame) {
return videogameService.updateVideogame(videogame);
}
@RequestMapping(value="/delvid/{id}",method = RequestMethod.DELETE)
public void deleteVideogame(@PathVariable("id") Long id)
{
    List<Image> images = imageService.getImagesParVid(id);
    for (Image image:images) {
        imageService.deleteImage(image.getIdImage());    }

    videogameService.deleteVideogameById(id);
}
@RequestMapping(value="/vidgen/{idGen}",method = RequestMethod.GET)
public List<Videogame> getVideogamesByCatId(@PathVariable("idGen") Long idGen) {
return videogameService.findByGenreIdGen(idGen);
}
@RequestMapping(value="/vidByName/{nom}",method = RequestMethod.GET)
public List<Videogame> findByNomVideogameContains(@PathVariable("nom") String nom) {
return videogameService.findByNomVideogameContains(nom);
}

 @RequestMapping(value="/imagesByVid/{idVid}",method = RequestMethod.GET)
    public List<Image> findImagesByVid(@PathVariable("idVid") Long id){
        return imageService.getImagesParVid(id);
}
}
