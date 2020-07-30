# Solution Architecture Diagram
<img src="https://github.com/TalbotGooday/Dashboard-PayDay/blob/master/images/solution_architecture_diagram.png"></a>
# Flow diagrams
### Launch Screen
<img src="https://github.com/TalbotGooday/Dashboard-PayDay/blob/master/images/launch_screen_diagram.png"></a>
___
### SignIn Screen
<img src="https://github.com/TalbotGooday/Dashboard-PayDay/blob/master/images/sign_in_screen.png"></a>
___
### SignUp Screen
<img src="https://github.com/TalbotGooday/Dashboard-PayDay/blob/master/images/sign_up_screen.png"></a>
___
### Transactions Screen
<img src="https://github.com/TalbotGooday/Dashboard-PayDay/blob/master/images/transactions_screen.png"></a>
___
### Dashboard Screen
<img src="https://github.com/TalbotGooday/Dashboard-PayDay/blob/master/images/dashboard_screen.png"></a>

# Answers to technical questions

### 1. How long did you spend on the coding test?
The coding test took me about 23 hours:
* Design: 6h;
* Application architecture: 4h;
* Business logic implementation: 10h;
* Readme creation: 3h.
### 2. What would you add to your solution if you had more time? If you didn'tspend much time on the coding test then use this as an opportunity to explain what you would add.
I would add better validation for fields like email, phone, more complete business logic for working with the API, which would include handling cases with no network. Perhaps I would add a logout function, I would have worked on the UX in more detail. It would also be interesting to create screens of detailed information on payments.
Also, perhaps, I would create my own version of the test server, which is closer to real projects.
### 3. What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.
I liked the last major update of the Kotlin (1.3.70) with the new function `scan()`.
The `scan()` function is similar to `fold()` and carry out a binary operation to a list of values. But `fold()` returns only the final result, while `scan()` returns a list containing transitional results. For example:
```kotlin
fun main() {
  val list = listOf(1, 3, 5, 6)
  
  println(list.fold(0){sum, acc -> sum + acc}) //Returns 15
  
  println(list.scan(0){ sum, acc -> sum + acc }) //Returns [0, 1, 4, 9, 15]
}
```
### 4. How would you track down a performance issue in production? Have you ever had to do this?
There are quite a few tools for finding performance problems, from the built-in Android Studio Profiler to the CanaryLeak library. I usually use these tools. 
Of course, there are other tools that help solve similar problems, but with each major release of the Android Studio, the built-in Android Profiler becomes more and more universal tool.
### 5. How would you debug issues related to API usage? Can you give us an example?
I usually use Postman or cURL to find problems in an API. If there is a need to log interaction with the API inside the IDE, then I use the `logging-interceptor` library:
```groovy
dependencies {
   implementation 'com.squareup.okhttp3:logging-interceptor:4.7.2'
}
```
```kotlin
fun provideHttpClient(cache: Cache
	): OkHttpClient {
		val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
			level = HttpLoggingInterceptor.Level.BODY
		}

		return OkHttpClient.Builder()
				.addInterceptor(httpLoggingInterceptor)
				.connectTimeout(15, TimeUnit.MINUTES)
				.readTimeout(15, TimeUnit.MINUTES)
				.writeTimeout(3, TimeUnit.MINUTES)
				.cache(cache)
				.build()
	}
```
### 6. How would you improve the Node server’s API that you just used?
The server lacks at least a primitive authorization, validation of requests for user authorization and customers data requests. It also lacks additional filters for requests to receive data on the Dashboard screen. I understand that the lack of such functionality may be due to the need to check the candidate skills to work with the database but it makes the test project "less real".

### 7. Please describe yourself using JSON.
```json
{
  "candidate": {
    "id": "20-15 2-5 3-15-14-20-9-14-21-5-4",
    "firstName": "Alexey",
    "lastName": "Mostovoy",
    "nickname": "TalbotGooday",
    "email": "alexey.mostovoy.w@gmail.com",
    "country": "Ukraine",
    "city": "Dnipro",
    "favouriteColor": "#5ab9f7",
    "online": true,
    "linkedin": "https://www.linkedin.com/in/alexey-mostovoy-a22913142/"
  }
}
```
