package com.hyper.backend.service.media;
import java.util.List;

import com.hyper.backend.model.Media;

public interface IMediaService {
	List<Media> findAll();
	Media findById(Integer id);
}
