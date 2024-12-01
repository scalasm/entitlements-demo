package me.marioscalas.edemo.product;

import java.util.Collection;
import java.util.List;

public interface FeatureSetService {
    FeatureSet createFeatureSet(FeatureSet product);
    void addFeatures(String productId, Collection<String> featureIds);
    void removeFeatures(String productId, Collection<String> featureIds);

    List<Feature> createFeatures(List<Feature> features);
}
