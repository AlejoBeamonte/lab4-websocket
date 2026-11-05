# Lab 4 WebSocket -- Project Report

## What I specified

I chose to send the sentence `always` to the ELIZA chatbot through a WebSocket connection.

The expected response is: `Can you think of a specific example?`

The goal was to create a test that checks one exact response from the server, instead of only checking that a message was received.

## What I changed

I modified only the file `src/test/kotlin/websockets/ElizaServerTest.kt`.

I enabled the `onChat()` test by removing `@Disabled` and completed the missing parts of the test. I added an assertion to check that the number of received messages is between 4 and 5, and another assertion to verify the exact response from ELIZA.

I also completed the `ComplexClient.onMessage()` method. The client now sends `always` when it receives the third welcome message (`---`). The condition ensures that the sentence is sent only once.

I did not modify the ELIZA implementation or the WebSocket server.

## Technical decisions

At first, I tried `Hello`, but I found that it could produce different responses because it does not match any defined keyword. I rejected it because the test could fail randomly depending on the server's response.

I chose `always` because, after reviewing `Eliza.kt`, I found that this keyword has only one possible response: `Can you think of a specific example?`. This makes the test deterministic, even though ELIZA uses random selection internally.

I kept `val size = list.size` to read the number of messages once. WebSocket messages arrive asynchronously, so the list can change while the test is running.

The test uses `CountDownLatch(4)` to wait for four messages: three welcome messages and one chatbot response. However, a fifth message (`---`) can arrive before the test checks the list size.

For this reason, I used `assertTrue(size in 4..5)` instead of `assertEquals` for the message count. Checking an exact number could make the test depend on the timing of message delivery.

Finally, I used `assertEquals` to verify the exact chatbot response stored in `list[3]`.

## How I verified

I first ran the original tests using:

```powershell
.\gradlew.bat check
```

After enabling `onChat()`, the test did not finish because the client was waiting for a fourth message. I fixed this by completing `ComplexClient.onMessage()` so it sends a sentence after receiving the welcome messages.

I initially tested with `Hello` and checked the server logs. After reviewing the response rules, I changed the sentence to `always` to guarantee a predictable answer.

I verified the implementation with:

```powershell
.\gradlew.bat test --rerun-tasks
.\gradlew.bat ktlintFormat
.\gradlew.bat check
```

All three commands finished with `BUILD SUCCESSFUL`.

I also tested the WebSocket connection manually using Postman at `ws://localhost:8080/eliza`. I checked that the server sent the three welcome messages, replied correctly to `always`, and closed the connection when I sent `bye`.

Some `ClosedChannelException` messages appeared during server shutdown, but they did not cause the tests to fail.

## AI disclosure

- **Tools / skills:** ChatGPT (OpenAI). No additional AI agent skills were used.
- **Purpose:** I used ChatGPT to understand the WebSocket test, explain `CountDownLatch`, identify a deterministic ELIZA response, help with debugging, review the code, and improve the English and structure of this report.
- **Representative prompts:**
  - "Summarize the main objectives and requirements of Lab 4."
  - "Explain the expected behavior of the WebSocket client and the CountDownLatch."
  - "Help me understand why the onChat test does not finish."
  - "Analyze the test execution logs and explain the results."
  - "Review my completed ElizaServerTest.kt file and check whether it meets the lab requirements."
  - "Help me improve the English and structure of REPORT.md."
- **Affected files/sections:** `src/test/kotlin/websockets/ElizaServerTest.kt` and `REPORT.md`.
- **Validation steps:** I reviewed the suggested changes, checked the response rules in `Eliza.kt`, executed the Gradle tests, ran the formatting checks, and manually verified the WebSocket communication using Postman.
- **Citations:** No external code snippets were copied. I used the provided lab project and its existing implementation as references.
- **Human-reviewed:** I reviewed the test logic, changed the initial sentence from `Hello` to `always`, checked the expected response, verified the asynchronous message count, and confirmed the results through automated tests and manual verification.