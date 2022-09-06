package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.repository.PictureRepository;
import bg.softuni.pathfinder.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<String> findAllUrls() {
        return this.pictureRepository.findAllUrls();
    }
}
