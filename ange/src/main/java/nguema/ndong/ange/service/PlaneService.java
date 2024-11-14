package nguema.ndong.ange.service;

import nguema.ndong.ange.model.Plane;
import nguema.ndong.ange.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneService {

    @Autowired
    private PlaneRepository planeRepository;

    public List<Plane> findAll() {
        return planeRepository.findAll();
    }

    public Optional<Plane> findById(Long id) {
        return planeRepository.findById(id);
    }

    public Plane save(Plane plane) {
        return planeRepository.save(plane);
    }

    public void deleteById(Long id) {
        planeRepository.deleteById(id);
    }
}
