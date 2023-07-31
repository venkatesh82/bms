## To Run

./gradlew bootRun

Once run the above command login into below h2 console execute sample-data.sql script present in resource -> data folder 

## H2-Console URL
http://localhost:8080/h2-console

JDBC URL: jdbc:h2:file:/data/sampledata


## Swagger URL
http://localhost:8080/swagger-ui/index.html

## Example Curl
curl --location 'http://localhost:8080/theater/browse?city=Hyderabad&movieTitle=Mission%20Impossible&date=2023-07-22'

curl --location 'http://localhost:8080/booking' \
--header 'Content-Type: application/json' \
--data '{
"customerId":"1",
"showId":"1",
"seatIds":[
1
]
}'