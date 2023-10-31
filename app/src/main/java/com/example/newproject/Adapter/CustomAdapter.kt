package com.example.newproject.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newproject.Interface.InterfaceClickCallBack
import com.example.newproject.Item
import com.example.newproject.R

class CustomAdapter(
    var context: Context,
    private var itemlist: ArrayList<Item>,
    private var interfaceClickCallBack: InterfaceClickCallBack
) : RecyclerView.Adapter<CustomAdapter.Viewholder>() {

    // Track selected items by position
    private val selectedItems = ArrayList<Int>()

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.textname)
        var age = itemView.findViewById<TextView>(R.id.textage)
        var checkbox = itemView.findViewById<CheckBox>(R.id.checkedbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return Viewholder(view)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val data = itemlist[position]
        holder.name.text = data.name
        holder.age.text = data.age
        // Set checkbox is checked
        holder.checkbox.isChecked = selectedItems.contains(position)

        holder.itemView.setOnClickListener {
            interfaceClickCallBack.onClickCallBack(data, position, "clicked")
        }

        holder.checkbox.setOnClickListener {
            if (holder.checkbox.isChecked) {
                selectedItems.add(position)
            } else {
                selectedItems.remove(position)
            }
        }
    }

    fun addItem(addItem: Item) {
        itemlist.add(addItem)
        notifyDataSetChanged()

    }

    fun updateItem(position: Int, addItem: Item) {
        itemlist[position] = addItem
        notifyItemChanged(position)
    }

    fun deleteSelectedItems() {
        // Delete selected items from the itemlist and clear the selected items set
        for (position in selectedItems) {
            itemlist.removeAt(position)
        }
        selectedItems.clear()
        // Refresh the RecyclerView
        notifyDataSetChanged()
    }
}