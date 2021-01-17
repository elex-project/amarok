# Project Amarok

Foreign Exchange Rates API                                                 
powered by Fixer.io & ExchangerRatesApi.io                                 

```java
ExchangeRate yen = new ExchangeRate(
        "JPY", "EUR", 117.18076, LocalDateTime.now());
ExchangeRate won = new ExchangeRate(
        "KRW", "EUR", 1345.146398, LocalDateTime.now());
ExchangeRate usd = new ExchangeRate(
        "USD", "EUR", 1.107935, LocalDateTime.now());

L.i("1 yen = ", won.calcPrice(1, yen) + " won");
L.i("1 usd = ", won.calcPrice(1, usd) + " won");
```

Copyright (c) 2020. Elex Project. All Rights Reserved.                     
https://www.elex-project.com/  
