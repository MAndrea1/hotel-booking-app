package com.example.hotelbookingapp.service.Imp;

import com.example.hotelbookingapp.model.PaymentType;
import com.example.hotelbookingapp.repository.PaymentTypeRepository;
import com.example.hotelbookingapp.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentTypeServiceImp implements PaymentTypeService {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Override
    public List<PaymentType> findAll() {
        return paymentTypeRepository.findAll();
    }

    @Override
    public Optional<PaymentType> findById(Integer id) throws Exception {
        return paymentTypeRepository.findById(id);
    }

    @Override
    public PaymentType save(PaymentType entity) throws Exception {
        return null;
    }

    @Override
    public PaymentType update(Integer id, PaymentType Entity) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(Integer id) throws Exception {
        return false;
    }
}
