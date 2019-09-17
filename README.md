# Bank App

App provides API to 
  - fetch a bank details, given branch IFSC code
  - fetch all details of branches, given bank name and a city
API is authenticated using JWT Token

### Usage

**Authenticate User** 
Request:
```sh
curl -H 'Content-Type:application/json' -d '{"username":"admin", "password":"admin123"}'  https://blooming-meadow-52246.herokuapp.com/authenticate
```
Response:
```sh
{"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU2Nzg1NDMzMSwiaWF0IjoxNTY3NDIyMzMxfQ.qPB-wB5VcqD6DJ-Ps8sfG_Bj5mIzYRmm3VDVoEemerDH3m1Yg8kUExqzPikhF51-3xrDRZKcJ6vc641zNgI_uA"}
```
**Fetch a bank details, given branch IFSC code** 
Request:
```sh
curl -X GET -H 'Content-Type:application/json' -H 'Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU2Nzg1NDMzMSwiaWF0IjoxNTY3NDIyMzMxfQ.qPB-wB5VcqD6DJ-Ps8sfG_Bj5mIzYRmm3VDVoEemerDH3m1Yg8kUExqzPikhF51-3xrDRZKcJ6vc641zNgI_uA' 'https://blooming-meadow-52246.herokuapp.com/api/bank?ifsc=ABHY0065001&page=0&size=1'
```
Response
```sh
{"bankId":"60","bankName":"ABHYUDAYA COOPERATIVE BANK LIMITED","branch":"RTGS-HO","ifsc":"ABHY0065001","address":"ABHYUDAYA BANK BLDG., B.NO.71, NEHRU NAGAR, KURLA (E), MUMBAI-400024","city":"MUMBAI","district":"GREATER MUMBAI","state":"MAHARASHTRA","status":"Success"}
```
**Fetch all details of branches, given bank name and a city** 
Request:
```sh
curl -X GET -H 'Content-Type:application/json' -H 'Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU2Nzg1NDMzMSwiaWF0IjoxNTY3NDIyMzMxfQ.qPB-wB5VcqD6DJ-Ps8sfG_Bj5mIzYRmm3VDVoEemerDH3m1Yg8kUExqzPikhF51-3xrDRZKcJ6vc641zNgI_uA' 'http://blooming-meadow-52246.herokuapp.com/api/banks?bankName=ICICI BANK LIMITED&city=BANGALORE&page=0&size=2'
```
Response
```sh
{"bankDetails":[{"ifsc":"ICIC0000002","branch":"BANGALORE - M G ROAD┬á","bankId":8,"bankName":"ICICI BANK LIMITED","address":"ICICI BANK TOWERS, 1, COMMISSARIAT ROAD, GROUND FLOOR, BANGALORE┬á560025.┬á","city":"BANGALORE","district":"BANGALORE URBAN","state":"KARNATAKA"},{"ifsc":"ICIC0000047","branch":"BANGALORE - KORAMANGALA┬á","bankId":8,"bankName":"ICICI BANK LIMITED","address":"NO:117,KORAMANGALA INDUSTRIAL LAYOUT,VII BLOCK,BANGALORE┬á","city":"BANGALORE","district":"BANGALORE URBAN","state":"KARNATAKA"}],"status":"Success"}
```

