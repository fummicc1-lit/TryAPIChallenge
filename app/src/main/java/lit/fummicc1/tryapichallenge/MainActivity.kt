package lit.fummicc1.tryapichallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import lit.fummicc1.tryapichallenge.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://lit-fummicc1-checkwork-server.herokuapp.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val apiService = retrofit.create(APIService::class.java)

        val list: MutableList<Todo> = mutableListOf()
        val adapter = RecyclerViewAdapter(list)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(baseContext)
        }

        lifecycleScope.launch {
            val todoList = apiService.getTodoList()
            for (todo in todoList) {
                Log.d("MainActivity", "$todo")
            }
//            TODO("ここを実装しよう。実装できたらこの行を削除してね")
            adapter.updateTodos(todoList)
        }
    }
}