import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCurrencyConverter {


    @Test
    void CurrencyConversion_CurrencyInSEK_CurrencyInEURO() {
        BigDecimal bd = new BigDecimal(100.0);
        Money oldMoney = new Money(Currency.SEK, bd);
        Money newMoney = CurrencyConverter.convertCurrency(oldMoney, Currency.EURO);
        assertEquals(Currency.EURO, newMoney.getCurrency());
    }

    @Test
    void CurrencyConversion_CurrencyInSEK_ValueChangesAccordingly() {
        BigDecimal value = new BigDecimal(100.0);
        Money originalMoney = new Money(Currency.SEK, value);

        Currency newCurrency = Currency.EURO;

        var currencyRates = CurrencyConverter.rates.get(originalMoney.getCurrency());
        var rate = currencyRates.get(newCurrency);

        BigDecimal expectedValue = rate.multiply(originalMoney.getValue());

        Money actualMoney = CurrencyConverter.convertCurrency(originalMoney, Currency.EURO);

        assertEquals(expectedValue, actualMoney.getValue());
    }

}
