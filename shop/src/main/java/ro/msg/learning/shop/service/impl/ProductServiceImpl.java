package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.service.ProductCategoryService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.SupplierService;
import ro.msg.learning.shop.utils.Status;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    private final SupplierService supplierService;

    @Override
    @Transactional
    public Status addProduct(Product product) {
        Optional<Supplier> supplier=supplierService.findByName(product.getSupplier().getName());
        if(!supplier.isPresent()){
            return Status.SUPPLIER_NOT_FOUND;
        }
        Optional<ProductCategory> productCategory=productCategoryService.findByName(product.getCategory().getName());
        if(!productCategory.isPresent()){
            return Status.CATEGORY_NOT_FOUND;
        }
        product.setSupplier(supplier.get());
        product.setCategory(productCategory.get());
        productRepository.save(product);
        return Status.SUCCESS;
    }

    @Override
    @Transactional
    public Status editProduct(Integer id, Product product) {

        Optional<Product> oldProduct=productRepository.findById(id);
        if(!oldProduct.isPresent()){
            return Status.PRODUCT_NOT_FOUND;
        }
        if(null!=product.getName() && !product.getName().isEmpty()){
            oldProduct.get().setName(product.getName());
        }
        if(null!=product.getDescription() && !product.getDescription().isEmpty()){
            oldProduct.get().setDescription(product.getDescription());
        }
        if(null!=product.getPrice()){
            oldProduct.get().setPrice(product.getPrice());
        }
        if(null!=product.getWeight()){
            oldProduct.get().setWeight(product.getWeight());
        }
        if(null!=product.getSupplier() && null!=product.getSupplier().getName()){
            Optional<Supplier> supplier=supplierService.findByName(product.getSupplier().getName());
            if(!supplier.isPresent()){
                return Status.SUPPLIER_NOT_FOUND;
            }
            oldProduct.get().setSupplier(supplier.get());
        }
        if(null!=product.getCategory() && null!=product.getCategory().getName()){
            Optional<ProductCategory> productCategory=productCategoryService.findByName(product.getCategory().getName());
            if(!productCategory.isPresent()){
                return Status.CATEGORY_NOT_FOUND;
            }
            oldProduct.get().setCategory(productCategory.get());
        }
        return Status.SUCCESS;
    }

    @Override
    @Transactional
    public Status deleteProduct(Integer idProduct) {

        Optional<Product> product=productRepository.findById(idProduct);
        if(!product.isPresent()){
            return Status.PRODUCT_NOT_FOUND;
        }
        productRepository.delete(product.get());
        return Status.SUCCESS;
    }

    @Override
    public Product getProduct(Integer idProduct) throws ProductNotFoundException{

        return productRepository.findById(idProduct).orElseThrow(()->new ProductNotFoundException(idProduct));
    }

    @Override
    public Collection<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }


}
