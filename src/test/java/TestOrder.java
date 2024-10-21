import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestOrder {

    ProductType p = new ProductType("ProductName", 0, ProductGroup.Beverage);
    ProductType p1 = new ProductType("ProductName1",1, ProductGroup.Dairy);
    ProductType p2 = new ProductType("ProductName2", 2, ProductGroup.Candy);

    @Test
    void Constructor_EmployeeCreatesOrder_EmptyOrder(){

    }
}
