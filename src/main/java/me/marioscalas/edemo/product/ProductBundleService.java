package me.marioscalas.edemo.product;

import java.util.Collection;

public interface ProductBundleService {
    public static record ConstituentPart(
        String programCode,
        String productId
    ) {}

    Product createProduct(String name, String description, Collection<ConstituentPart> parts);
}   
