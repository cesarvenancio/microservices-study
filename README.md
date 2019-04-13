# Tinyurl
API to short URLs.

## API

### 1. `/` 
Create the short URL: 
```
curl -X POST http://localhost:9090/shorten -H 'Content-type:application/json' -d '{"url": "http://www.google.com"}'
```
The response:
```json
{
    "originalUrl": "http://www.google.com",
    "shortUrl": "http://localhost:9090/L"
}
```

### 2. `/{tinyUrl}`
Redirect to the original URL used to create this link
```
curl -i http://localhost:9090/L
```
Will redirect to the original URL:
```
HTTP/1.1 301 
Location: http://www.google.com
Content-Length: 0
Date: Mon, 18 Mar 2019 15:56:55 GMT
```

If the short url informed does not exist it will return a *404 Not Found*.

## Settings
The domain can be set in `resources/application.properties`:
```properties

### Build the application:

```
mvn clean install
```

## Run the application

```
mvn spring-boot:run
```


The server will be started on port `9090`.

