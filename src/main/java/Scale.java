import java.util.Random;

public class Scale {

    public Scale() {

    }



    public long randomizeWeight() {
        Random rd = new Random();
        long min = 10;          //weight in gram
        long max = 10000;
        return min + (long) (rd.nextDouble() * (max-min));

    }


    public double getWeight() {
        return randomizeWeight();
    }

}
/* if(this.getProductGroups().contains(ProductGroup.PricedByWeight))
            scale.setWeight();*/