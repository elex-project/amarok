# Project Amarok
HTTP API sets with Java's new Http Api

requires Java 11+

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/elex-project/amarok")
    }
}
dependencies (
	implementation("com.elex-project.amarok:address:2.4.0")
    implementation("com.elex-project.amarok:currency:2.4.0")
    implementation("com.elex-project.amarok:weather:2.4.0")
    implementation("com.elex-project.amarok:astro:2.4.0")
)
```



## Address

- Kakao Address Search Api
- Kakao Coord to Address Api
- Kakao Trans Coord Api

```java
AddressSearchRequest api = new AddressSearchRequest("양운로 45", API_KEY);
AddressSearchResponse response = api.send();
```

```java
CoordToAddressRequest api = new CoordToAddressRequest(129.176777393347, 35.16737568579, API_KEY);
CoordToAddressResponse response = api.send();
```

```java
TransCoordRequest api = new TransCoordRequest(129.176777393347, 35.16737568579, Coord.WGS84, Coord.WCONGNAMUL, API_KEY);
TransCoordResponse response = api.send();
```



## Currency

* FixerIo

```java
FixerIoRequest request = new FixerIoRequest(API_KEY, null, null);
FixerIoResponse result = request.send();
```



## Weather
- OpenWeatherMap Current weather Api

```java
OpenWeatherMap api = new OpenWeatherMap(35.1802563, 127.8292504, API_KEY);
OWMResponse response = api.send();
```

```java
OpenWeatherMap api = new OpenWeatherMap("busan", Locale.KOREA, API_KEY);
OWMResponse response = api.send();
```



-----

Copyright &copy; 2020 Elex. All Rights Reserved.
https://www.elex-project.com/