package lit.fummicc1.tryapichallenge

import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {
    @GET("todos")
    suspend fun getTodoList(): List<Todo>

    @POST("todos")
    suspend fun createTodo(title: String, priority: Int)
}