package hillellit.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Ludmila Litvinova on 30.12
 */
@Data
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private Double price;
}