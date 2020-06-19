package com.hyper.backend.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.hyper.backend.model.Media;
import com.hyper.backend.service.media.IMediaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MediaEndpoint {
	
	@Autowired
    private IMediaService mediaService;
	
	@GetMapping("/medias")
    public List<Media> listMedias() {
    	List<Media> medias = (List<Media>) mediaService.findAll();
        return medias;
    }
	@GetMapping("/medias/{id}")
    public Media retrieveMEdia(@PathVariable Integer id) {
    	Media media = (Media) mediaService.findById(id);
        return media;
    }
}
