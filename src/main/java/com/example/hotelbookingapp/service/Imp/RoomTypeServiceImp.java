package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.model.RoomType;
import com.example.hotelbookingapp.repository.RoomTypeRepository;
import com.example.hotelbookingapp.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomTypeServiceImp implements RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Override
    public List<RoomType> findAll() {
        return roomTypeRepository.findAll();
    }

    @Override
    public Optional<RoomType> findById(Integer id) throws Exception {
        return roomTypeRepository.findById(id);
    }

    @Override
    public RoomType save(RoomType entity) throws Exception {
        return null;
    }

    @Override
    public RoomType update(Integer id, RoomType Entity) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        return false;
    }
}
