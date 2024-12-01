package me.marioscalas.edemo.product.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.marioscalas.edemo.product.Product;
import me.marioscalas.edemo.product.ProductBundle;
import me.marioscalas.edemo.product.ProductBundleRepository;
import me.marioscalas.edemo.product.ProductBundleService;
import me.marioscalas.edemo.product.ProductMembership;
import me.marioscalas.edemo.product.ProductMembershipId;
import me.marioscalas.edemo.product.ProductMembershipRepository;
import me.marioscalas.edemo.product.ProductRepository;
import me.marioscalas.edemo.product.Program;
import me.marioscalas.edemo.product.ProgramRepository;


@Service
@Transactional
@RequiredArgsConstructor
public class ProductBundleServiceImpl implements ProductBundleService {

    private final ProductBundleRepository productBundleRepository;

    private final ProductMembershipRepository productMembershipRepository;

    private final ProgramRepository programRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductBundle createProductBundle(String name, String description, Collection<ConstituentPart> parts) {
        final var productBundle = productBundleRepository.save(
            ProductBundle.builder().name(name).description(description).build()
        );

        parts.forEach(part -> {
            final Program program = programRepository.findById(part.programCode()).orElseThrow(() -> new IllegalStateException("Program code is unknown: " + part.programCode()));
            final Product product = productRepository.findById(part.productId()).orElseThrow(() -> new IllegalStateException("Product id is unknown: " + part.productId()));

            productMembershipRepository.save(
                ProductMembership.builder().id( 
                    new ProductMembershipId(productBundle, product, program)
                ).build()
            );
        });

        return productBundleRepository.findById(productBundle.getId()).get();
    }
}
