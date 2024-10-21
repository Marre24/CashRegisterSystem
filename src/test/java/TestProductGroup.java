import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProductGroup {

    @Test
    void ProductGroup_ValidProductGroup_CorrectValue(){
        ProductType p = new ProductType("", 0, ProductGroup.Bread);
        assertEquals(ProductGroup.Bread,p.getProductGroups().getFirst());
    }
}
