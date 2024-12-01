package me.marioscalas.edemo.product;

import java.util.Collection;

public interface ProductBundleService {
    public static record ConstituentPart(
        String programCode,
        String productId
    ) {}

    ProductBundle createProductBundle(String name, String description, Collection<ConstituentPart> parts);
}   
