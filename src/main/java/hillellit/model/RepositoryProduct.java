package hillellit.model;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ludmila Litvinova on 30.12
 */
@Service
@Data
public class RepositoryProduct {
    List<Product> products;

    public RepositoryProduct(List<Product> products) {
        this.products = products;
    }

    public List<Product> getAll() {
        return products;
    }

    public Product getById(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }
}
