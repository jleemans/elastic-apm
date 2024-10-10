# Features

## Intro

In this document you'll find a description of the things you can see in Elastic APM. This is done after
having ran a load test with the `wrk` command (see [README.md](../README.md)). Feel free to experiment
for yourself. Your results may vary of course.

## Overview of incoming requests

![transaction for HTTP POST user.png](transaction%20for%20HTTP%20POST%20user.png)

In this screenshot you see a transaction corresponding to one particular request. This happens to be one
of those requests where a call to randomuser.me is made. In addition to that, there are some database calls
involved in storing user data. The "otherOperations" span is one that was explicitly demarcated in the code as
follows:
```
	@CaptureSpan("otherOperations")
	private void sleep() {
		// logic that randomly sleeps between 20 ms and 100 ms.
	}
```
You see that the remote call took the majority of the time.

## Overview of response times and throughput

![response times and throughput.png](response%20times%20and%20throughput.png)

Per type of request (e.g. a "POST user" request in this case) you get a nice overview of the response times
and throughput of those requests. Some HTTP calls have failed, and these are nicely distinguished from the
successful ones.

## Response times distribution

![response times distribution.png](response%20times%20distribution.png)

Also, per type of request you can see how they are distributed in terms of response times. From here you can
single out slow requests and investigate them individually.

## View transaction in Discover

![transaction in discover.png](transaction%20in%20discover.png)

From the first screenshot showing the transaction for an HTTP POST user request, you could easily navigate
to "view transaction in Discover", giving you the view above. It shows that APM-related data is stored in apm-*
indices in ElasticSearch. This info should allow to correlate the transactions in the APM view with the application
logs.


