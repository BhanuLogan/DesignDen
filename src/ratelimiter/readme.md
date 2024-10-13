## **Problem: Design a Rate Limiter**

### Problem Statement:
You are asked to design a **Rate Limiter**. A **Rate Limiter** ensures that a client doesn’t exceed a predefined number of requests within a given time window. For example, an API might allow **100 requests per user client minute**. If a client exceeds this limit, further requests within the same window should be **rejected**.

### Requirements:
1. **Throttle User Requests:** If a user sends more requests than the allowed limit within the window, the system should block any further requests until the window resets.
2. **Graceful Handling:** Allow concurrent requests but ensure thread-safety, so the rate limiter works in a multi-threaded environment.
3. **Configurable Limits:** The system should allow configuring **different request limits and time windows** for different users (or APIs).
4. **Testability:** The system should provide a way to **unit-test** the rate limiter logic.

---

### Constraints:
- You can assume that **user IDs** will be passed along with each request.
- Keep the design **simple and extendable**. Later, this system might be enhanced to support features like **burst traffic handling** or **distributed rate limiting**.

---

### Expected Classes and Interfaces:
Here are some classes/interfaces you might use, but feel free to add/change as needed:

1. **RateLimiter**: Interface that defines the contract for rate limiting.
    - `boolean isAllowed(String userId)`: Returns true if the request is allowed; false otherwise.

2. **FixedWindowRateLimiter**: A concrete implementation using the **fixed window** approach. This will reset limits at fixed intervals (e.g., every 60 seconds).

3. **SlidingWindowRateLimiter** (Optional Advanced): Implement a sliding window rate limiter to handle requests more smoothly over time.

---

### Example Use Case:

```plaintext
User-123: Allowed 10 requests per minute.
- Request 1: Allowed (total: 1)
- Request 2: Allowed (total: 2)
...
- Request 10: Allowed (total: 10)
- Request 11: Blocked (limit reached)

After 1 minute:
- Request 12: Allowed (new window started)
```
