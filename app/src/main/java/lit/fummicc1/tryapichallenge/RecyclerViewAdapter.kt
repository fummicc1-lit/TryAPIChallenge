package lit.fummicc1.tryapichallenge

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lit.fummicc1.tryapichallenge.databinding.ItemTodoListBinding

class RecyclerViewAdapter(val todos: MutableList<Todo>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        val textView: TextView = root.findViewById(R.id.todoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todos[position]
        holder.textView.text = todo.title
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    // このメソッドを用いてAPIサーバーから取得したTodoに更新するよ
    @SuppressLint("NotifyDataSetChanged")
    fun updateTodos(todos: List<Todo>) {
        this.todos.clear()
        this.todos.addAll(todos)
        notifyDataSetChanged()
    }
}