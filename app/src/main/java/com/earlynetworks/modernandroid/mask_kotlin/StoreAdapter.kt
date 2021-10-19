package com.earlynetworks.modernandroid.mask_kotlin

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.earlynetworks.modernandroid.R
import com.earlynetworks.modernandroid.mask_kotlin.model.Store
import java.util.ArrayList


class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
    var addressTextView: TextView = itemView.findViewById(R.id.addr_text_view)
    var distanceTextView: TextView = itemView.findViewById(R.id.distance_text_view)
    var remainTextView: TextView = itemView.findViewById(R.id.remain_text_view)
    var countTextView: TextView = itemView.findViewById(R.id.count_text_view)

}

class StoreAdapter : RecyclerView.Adapter<StoreViewHolder>() {
    private var mItems: List<Store> = ArrayList()

    fun updateItems(items: List<Store>) {
        mItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = mItems[position]
        holder.nameTextView.text = store.name
        holder.addressTextView.text = store.addr
        holder.distanceTextView.text = "1km"
        var remainStat = "충분"
        var remainCount = "100개 이상"
        var color = Color.GREEN
        when (store.remain_stat) {
            "plenty" -> {
                remainStat = "충분"
                remainCount = "100개 이상"
                color = Color.GREEN
            }
            "some" -> {
                remainStat = "여유"
                remainCount = "30개 이상"
                color = Color.YELLOW
            }
            "few" -> {
                remainStat = "매진 임박"
                remainCount = "2개 이상"
                color = Color.RED
            }
            "empty" -> {
                remainStat = "재고 없음"
                remainCount = "1개 이하"
                color = Color.GRAY
            }
            else -> {
            }
        }
        holder.remainTextView.text = remainStat
        holder.remainTextView.setTextColor(color)
        holder.countTextView.text = remainCount
        holder.countTextView.setTextColor(color)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }
}