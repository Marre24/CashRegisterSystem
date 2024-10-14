import java.math.BigDecimal;
import java.util.Map;

public class CurrencyConverter {

    public static Map<Currency, BigDecimal> sekRates = Map.ofEntries(
            Map.entry(Currency.SEK, new BigDecimal(1)),
            Map.entry(Currency.EURO, new BigDecimal(12.6))
    );


    public static Map<Currency, Map<Currency, BigDecimal>> rates = Map.ofEntries(
            Map.entry(Currency.SEK, sekRates)
    );

    public static Money convertCurrency(Money oldMoney, Currency currency) {

        var currencyRates = rates.get(oldMoney.getCurrency());
        var rate = currencyRates.get(currency);
        return new Money(currency, rate.multiply(oldMoney.getValue()));
    }
}