### Product Requirements: Task Scheduler

**Objective:**
Design and implement a Task Scheduler that can manage and execute tasks based on their priorities and scheduled times. The scheduler should support adding, removing, and executing both one-time and recurring tasks, creating a robust and efficient scheduling system.

---

### Functional Requirements:

1. **Task Interface:**
    - The system shall provide an interface for tasks that includes methods to:
        - Execute the task.
        - Retrieve the task's priority.
        - Obtain the next scheduled run time.

2. **Task Types:**
    - The system shall support the following task types:
        - **One-Time Task:**
            - A task that executes once at a specified time.
        - **Recurring Task:**
            - A task that executes at specified intervals (e.g., every minute, every hour).

3. **Scheduler:**
    - The system shall include a Scheduler component that can:
        - Add a task to the scheduling queue.
        - Remove a task from the scheduling queue.
        - Execute tasks based on their priority and scheduled time.

4. **Priority Handling:**
    - Each task shall have a priority level (e.g., low, medium, high).
    - The scheduler shall prioritize execution based on task priority.
    - The system shall allow users to adjust the priority of a task after it has been added to the scheduler.

5. **Concurrency:**
    - The system shall ensure that the scheduler can handle multiple tasks concurrently using appropriate synchronization techniques to maintain thread safety.

---

### Use Cases:

1. **Task Creation and Scheduling:**
    - **Use Case:** A user wants to schedule a task to send a reminder email.
        - **Input:** Task name, execution time (e.g., "2024-10-14 09:00"), priority (e.g., high).
        - **Output:** Task is added to the scheduler and will execute at the specified time.

2. **Recurring Task:**
    - **Use Case:** A user wants to schedule a task to generate a daily report.
        - **Input:** Task name, interval (e.g., every 24 hours), priority (e.g., medium).
        - **Output:** Task is added to the scheduler and will execute daily at the specified time.

3. **Task Removal:**
    - **Use Case:** A user decides to cancel a scheduled task.
        - **Input:** Task ID or name.
        - **Output:** Task is removed from the scheduler and will no longer execute.

4. **Priority Adjustment:**
    - **Use Case:** A user wants to change the priority of a task from low to high.
        - **Input:** Task ID or name, new priority (e.g., high).
        - **Output:** Task's priority is updated in the scheduler, affecting its execution order.

5. **Task Execution:**
    - **Use Case:** The scheduler checks for tasks to execute.
        - **Input:** Current time.
        - **Output:** Executes tasks based on their scheduled times and priorities.

---

### Non-Functional Requirements:

1. **Thread Safety:**
    - The implementation shall be thread-safe, allowing for concurrent additions and removals of tasks without data corruption.

2. **Execution Timing:**
    - The scheduler shall ensure tasks execute at the correct times for both one-time and recurring scheduling.

---

### Bonus Features:

1. **Persistence:**
    - The system shall allow tasks to be saved to a file or database to enable reloading when the application restarts.

2. **Task History:**
    - The system shall maintain a history of executed tasks, including timestamps and results.
