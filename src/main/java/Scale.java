import java.util.Random;

public abstract class Scale {

    public static long getWeight() {
        Random rd = new Random();
        return rd.nextLong(1, 10000); //returns between 1g and 10kg
    }
}
/* if(this.getProductGroups().contains(ProductGroup.PricedByWeight))
            scale.setWeight();*/