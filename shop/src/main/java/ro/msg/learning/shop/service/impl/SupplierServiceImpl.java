package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.SupplierRepository;
import ro.msg.learning.shop.service.SupplierService;
import ro.msg.learning.shop.utils.Status;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;


    @Override
    public Optional<Supplier> findByName(String name) {
        return supplierRepository.getSupplierByName(name);
    }


}
