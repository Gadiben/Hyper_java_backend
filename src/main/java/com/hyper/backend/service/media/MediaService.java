package com.hyper.backend.service.media;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyper.backend.exception.NotFoundException;
import com.hyper.backend.model.Media;
import com.hyper.backend.repositories.MediaRepo;

@Service
public class MediaService implements IMediaService {

	@Autowired
	private MediaRepo mediaRepo;
	
	@Override
	public List<Media> findAll() {
		List<Media> medias = (List<Media>) mediaRepo.findAll();
		return medias;
	}

	@Override
	public Media findById(Integer id) {
		Optional<Media> media = mediaRepo.findById(id);
		if (media.isPresent()){
			return media.get();
		}else {
			throw new NotFoundException(id, "media");
		}
	}

}
