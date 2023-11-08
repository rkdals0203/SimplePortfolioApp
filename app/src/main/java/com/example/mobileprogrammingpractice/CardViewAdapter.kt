import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileprogrammingpractice.R
import kotlinx.android.synthetic.main.card_item.view.textImg
import com.example.mobileprogrammingpractice.Item
import kotlinx.android.synthetic.main.card_item.view.cardImg

class CardViewAdapter(val itemList: ArrayList<Item>): RecyclerView.Adapter<CardViewAdapter.ViewHolder>() {

    private var onItemClickListener: ((position: Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (position: Int) -> Unit) { //클릭 리스너 정의
        onItemClickListener = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClickListener?.invoke(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemView.textImg.text = item.name
        holder.itemView.cardImg.setImageResource(item.imageResId)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
