Here’s how to persist user sessions across Spring Boot restarts using **Spring Session with Redis**:

---

### 1. **Add Redis and Spring Session dependencies to `pom.xml`:**
```xml
<dependency>
    <groupId>org.springframework.session</groupId>
    <artifactId>spring-session-data-redis</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

---

### 2. **Configure Redis in `application.yml`:**
```yaml
spring:
  session:
    store-type: redis
  redis:
    host: localhost
    port: 6379
```

---

### 3. **Run a Redis server locally**  
If you don’t have Redis installed, you can download it from [https://redis.io/download](https://redis.io/download) or use Docker:

```
docker run -p 6379:6379 redis
```

---

### 4. **Restart your Spring Boot app**  
Now, when you log in with Google, your session will be stored in Redis.  
If you restart your Spring Boot app, your session will persist (as long as the session cookie in your browser and the Redis server are still available).

---

**Note:**  
- If you clear browser cookies or Redis data, you’ll need to log in again.
- This approach works for session-based authentication (including OAuth2 logins).

Let me know if you need help with a