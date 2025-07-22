# Banking API Reference

| API Endpoint    | Method | Headers                                                      | Request Body                                                                                             | Response Format                                                                                                                                                   | Description         |
| --------------- | ------ | ------------------------------------------------------------ | -------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------- |
| `/api/login`    | POST   | `Content-Type: application/json`                             | `{ "username": "string", "password": "string" }`                                                 | **200 OK**: `{ "token": "dummy-jwt-token", "user": { "user_id": "Long", "username": "string" } }`<br>**500 Error**: `{ "error": "Invalid username or password" }` | User authentication |
| `/api/accounts` | GET    | `Authorization: string`                                      | None                                                                                                     | **200 OK**: List of account objects                                                                                                                               | Get user's accounts |
| `/api/payees`   | GET    | `Authorization: string`                                      | None                                                                                                     | **200 OK**: List of payee objects                                                                                                                                 | Get user's payees   |
| `/api/payees`   | POST   | `Authorization: string`,<br>`Content-Type: application/json` | `{ "name": "string", "accountNumber": "string", "sortCode": "string", "isInternal": "boolean" }` | **201 Created**: Payee object<br>**500 Error**: `{ "error": "User not found" }`                                                                                   | Add new payee       |

> 💡 **Note:** Tables do not render multi-line JSON well in Markdown. For better presentation, include request and response examples below the table.

---

## Request and Response Examples

### `/api/login`

**Request:**

```json
{
  "username": "string",
  "password": "string"
}
```

**Success Response:**

```json
{
  "token": "dummy-jwt-token",
  "user": {
    "user_id": 12345,
    "username": "johndoe"
  }
}
```

**Error Response:**

```json
{
  "error": "Invalid username or password"
}
```

---

### `/api/payees` (POST)

**Request:**

```json
{
  "name": "Alice",
  "accountNumber": "12345678",
  "sortCode": "00-11-22",
  "isInternal": true
}
```

**Success Response:**

```json
{
  "payeeId": 1,
  "user": {
    "userId": 123,
    "username": "johndoe"
  },
  "name": "Alice",
  "accountNumber": "12345678",
  "sortCode": "00-11-22",
  "isInternal": true,
  "createdAt": "2025-07-22T10:30:00Z"
}
```

---

## Additional Notes

* **Authentication**: All APIs except `/api/login` require an `Authorization` header with a JWT token.
* **Token Handling**: Use `TokenUtil.extractUserId(token)` to extract user ID from the token.
* **Error Handling**: Uses `RuntimeException` → typically returns HTTP 500.
* **Data Types**:

  * `Long`: Integer-type IDs
  * `BigDecimal`: Monetary values
  * `ISO-8601`: Timestamps like `"2025-07-22T10:30:00Z"`
  * `boolean`: `true` / `false`

The key points to note:

1. **Login API** is the only endpoint that doesn't require authentication
2. **Token-based authentication** is used for all other endpoints via the Authorization header
3. **GET endpoints** return arrays of objects for accounts and payees
4. **POST endpoint** for payees creates new payee records and returns the created object
5. **Error responses** currently throw RuntimeExceptions which would result in 500 status codes

The response formats include all the entity fields as they would be serialized by Spring Boot's default JSON serialization.