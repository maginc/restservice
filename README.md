### Rest API

### ``` POST /api/v1/request/:customerId```
Main endpoint, requires JSON body.
Example body:
```{"customerID":1,"tagID":2,"userID":"aaaaaaaa-bbbb-cccc-1111-222222222222","remoteIP":"123.234.56.78","timestamp":1500000000}```
Customer id should be same in request path and in json body, otherwise requests will be marked as invalid.

###```GET /api/v1/stats/:customerID/:date```

Return json body with customer info, hourly stats for this customer and particular day

Get statistics for particular customer per this day.
Date string should be in format: "yyyy-MM-dd"