package com.rusnoto.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rusnoto.core.R
import com.rusnoto.core.databinding.ListGamesBinding
import com.rusnoto.core.domain.model.GameListModel
import com.rusnoto.core.utils.Helper.setListImage

class GameListAdapter: RecyclerView.Adapter<GameListAdapter.ListViewHolder>() {
    private var itemList = ArrayList<GameListModel>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder{
        val itemBinding = LayoutInflater.from(parent.context).inflate(R.layout.list_games, parent, false)
        return ListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = itemList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = itemList.size

    fun setData(data: List<GameListModel>){
        itemList.clear()
        itemList.addAll(data)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ListGamesBinding.bind(itemView)
        fun bind(data: GameListModel){
            with(binding){
                ivGamesPoster.setListImage(data.gamePoster)
                tvGamesTitle.text = data.gameName
                listGames.setOnClickListener {
                    onItemClickCallback.onItemClicked(data)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: GameListModel)
    }
}