package com.rusnoto.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rusnoto.core.R
import com.rusnoto.core.databinding.ListGamesBinding
import com.rusnoto.core.domain.model.GameDetailModel
import com.rusnoto.core.utils.Helper.setListImage

class FavGameAdapter: RecyclerView.Adapter<FavGameAdapter.ListViewHolder>() {
	private var itemList = ArrayList<GameDetailModel>()
	private lateinit var onItemClickCallback: OnItemClickCallback

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
		val itemBinding = LayoutInflater.from(parent.context).inflate(R.layout.list_games, parent, false)
		return ListViewHolder(itemBinding)
	}

	override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
		val data = itemList[position]
		holder.bind(data)
	}

	override fun getItemCount(): Int = itemList.size

	fun setData(data: List<GameDetailModel>){
		itemList.clear()
		itemList.addAll(data)
		notifyDataSetChanged()
	}

	fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
		this.onItemClickCallback = onItemClickCallback
	}

	inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
		private val binding = ListGamesBinding.bind(itemView)
		fun bind(data: GameDetailModel){
			with(binding){
				data.poster?.let { ivGamesPoster.setListImage(it) }
				tvGamesTitle.text = data.name
				listGames.setOnClickListener {
					onItemClickCallback.onItemClicked(data)
				}
			}
		}
	}

	interface OnItemClickCallback {
		fun onItemClicked(data: GameDetailModel)
	}
}