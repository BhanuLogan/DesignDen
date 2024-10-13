## **Problem Statement: JSON Parser**

### Objective:
Design and implement a **JSON Parser** in Java that can parse JSON strings into their corresponding Java objects (e.g., Lists, Maps, Strings, Numbers, Booleans). Your parser should be capable of handling various valid JSON inputs, including nested structures. The goal is to develop a **custom JSON parser** instead of relying on existing libraries like `Gson` or `Jackson`.

---

### Requirements:
1. **Support for JSON Structures:**
    - Parse JSON Objects (e.g., `{"name": "Alice", "age": 25}`)
    - Parse JSON Arrays (e.g., `["apple", 42, {"key": "value"}]`)
    - Handle Strings, Numbers, Booleans, and Null values.

2. **Character Tokenization:**
    - Use **tokenization** to break the JSON input into meaningful tokens (e.g., `{`, `"name"`, `:`, `"Alice"`, `}`).
    - Avoid hardcoding values; the parser should recognize tokens dynamically.

3. **Handle Nested Structures:**
    - Support **nested objects** and **arrays** (e.g., `{"person": {"name": "Alice", "address": {"city": "NY"}}}`).

4. **Error Handling:**
    - Detect and handle **malformed JSON** gracefully by throwing appropriate exceptions (e.g., if braces or brackets are unbalanced).

5. **Thread Safety:**
    - If parsing is done concurrently, ensure that the implementation is **thread-safe**.

---

### Constraints:
1. **No External Libraries:** You cannot use libraries like `Gson`, `Jackson`, or `org.json`. You must write the parser from scratch.
2. **Whitespace Handling:** Ignore whitespace between tokens (e.g., spaces, tabs, and newlines should be skipped).
3. **Extendability:** Ensure that the design is **modular and extendable**, so new features (e.g., pretty-printing JSON) can be easily added.

---

### Additional requirements:
1. **Unit Tests:** Ensure that the parser is properly tested with a variety of valid and invalid JSON inputs.
2. **Performance Optimization:** Consider the performance for large JSON inputs.
3. **Streaming Parsing:** Implement an optional feature to support **streaming large JSON files** to avoid memory overflow.
