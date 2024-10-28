import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProductGroup {

    @Test
    void ProductGroup_ValidProductGroup_CorrectValue(){
        Product p = new Product("", 0, Producer.Arla, ProductGroup.Bread);
        assertEquals(ProductGroup.Bread,p.getProductGroups().getFirst());
    }
}
