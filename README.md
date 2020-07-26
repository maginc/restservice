## How to run
```git clone https://github.com/maginc/restservice.git``` <br>
Or download repository trough Github UI

Run as usual Spring Boot maven project <br>

By default app uses in memory H2 database so any saved data will be lost on app restart, <br>
if you want to save data permanently change ```spring.datasource.``` settings in ```application.properties``` <br>
AND comment out/remove demo data bean at RestserviceApplication.java before second app launch.


## Rest API
### ``` POST /api/v1/request/:customerId```
Main endpoint, requires JSON body.
Example body: <br>
```{"customerID":1,"tagID":2,"userID":"aaaaaaaa-bbbb-cccc-1111-222222222222","remoteIP":"123.234.56.78","timestamp":1500000000}``` <br>
Customer id should be same in request path and in json body, otherwise requests will be marked as invalid. <br>

### ```GET /api/v1/stats/:customerId/:date```
Get statistics for particular customer per this day. <br>
Date string should be in format: "yyyy-MM-dd" <br>

Return json body with customer info, hourly stats for this customer on particular day <br>

