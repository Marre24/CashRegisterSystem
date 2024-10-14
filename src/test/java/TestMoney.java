import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TestMoney {


    @Test
    public void Constructor_Currency_ClassInstance(){
        Money money = new Money(Currency.SEK, new BigDecimal(0));
        
        assertEquals(money.getCurrency(), Currency.SEK);
    }

    @Test
    public void Constructor_Value_ClassInstance(){
        BigDecimal expextedvalue = new BigDecimal(100);
        Money money = new Money(Currency.SEK, expextedvalue);

        assertEquals(money.getValue(), expextedvalue);
    }

}
