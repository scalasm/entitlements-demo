package me.marioscalas.edemo.product.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.marioscalas.edemo.product.Feature;
import me.marioscalas.edemo.product.FeatureRepository;
import me.marioscalas.edemo.product.FeatureSet;
import me.marioscalas.edemo.product.FeatureSetRepository;
import me.marioscalas.edemo.product.FeatureSetService;

@Service
@Transactional
@RequiredArgsConstructor
public class FeatureSetServiceImpl implements FeatureSetService {

    private final FeatureSetRepository featureSetRepository;
    private final FeatureRepository featureRepository;

    @Override
    public FeatureSet createFeatureSet(FeatureSet featureSet) {
        return featureSetRepository.save(featureSet);
    }

    @Override
    public void addFeatures(String productId, Collection<String> featureIds) {
        Assert.notEmpty(featureIds, "You must provide at least one feature id");
        final var featureSet = featureSetRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found")); 
        
        featureIds.forEach(featureId -> {
            final var feature = featureRepository.findById(featureId).orElseThrow(() -> new IllegalArgumentException("Feature not found"));
            featureSet.getFeatures().add(feature);
        });

        featureSetRepository.save(featureSet);
    }

    @Override
    public void removeFeatures(String productId, Collection<String> featureIds) {
        Assert.notEmpty(featureIds, "You must provide at least one feature id");
        final var featureSet = featureSetRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found")); 

        featureIds.forEach(featureId -> {
            featureSet.removeFeatureById(featureId);
        });
        featureSetRepository.save(featureSet);
    }

    @Override
    public List<Feature> createFeatures(List<Feature> features) {
        return featureRepository.saveAll(features);
    }
}
