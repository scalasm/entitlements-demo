package me.marioscalas.edemo.product.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.marioscalas.edemo.product.Feature;
import me.marioscalas.edemo.product.FeatureRepository;
import me.marioscalas.edemo.product.Product;
import me.marioscalas.edemo.product.ProductMembershipRepository;
import me.marioscalas.edemo.product.ProductRepository;
import me.marioscalas.edemo.product.ProductService;
import me.marioscalas.edemo.product.ProgramRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FeatureRepository featureRepository;
    private final ProgramRepository programRepository;
    private final ProductMembershipRepository productMembershipRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void addFeatures(String productId, Collection<String> featureIds) {
        Assert.notEmpty(featureIds, "You must provide at least one feature id");
        final var product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found")); 
        
        featureIds.forEach(featureId -> {
            final var feature = featureRepository.findById(featureId).orElseThrow(() -> new IllegalArgumentException("Feature not found"));
//            product.getFeatures().add(feature);
        });

        productRepository.save(product);
    }

    @Override
    public void removeFeatures(String productId, Collection<String> featureIds) {
        Assert.notEmpty(featureIds, "You must provide at least one feature id");
        final var product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found")); 

        featureIds.forEach(featureId -> {
//            product.removeFeatureById(featureId);
        });
        productRepository.save(product);
    }

    @Override
    public List<Feature> createFeatures(List<Feature> features) {
        return featureRepository.saveAll(features);
    }
}
