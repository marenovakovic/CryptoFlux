package com.marko.cryptoflux.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marko.cryptoflux.R
import com.marko.cryptoflux.entities.Coin
import com.marko.cryptoflux.entities.CoinId
import com.marko.cryptoflux.extensions.inflate
import kotlinx.android.synthetic.main.list_item_coin.view.*

class CoinsAdapter(
	private val onClick: (id: CoinId) -> Unit
) : ListAdapter<Coin, CoinsAdapter.ViewHolder>(DiffCallbacks) {

	var coins = listOf<Coin>()
	set(value) = submitList(value)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = parent.inflate(R.layout.list_item_coin)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val coin = getItem(position)
		holder.bind(coin)
	}

	inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

		fun bind(coin: Coin) {
			itemView.setOnClickListener { onClick(coin.id) }
			itemView.listItemCoinName.text = coin.name
		}
	}
}

object DiffCallbacks : DiffUtil.ItemCallback<Coin>() {
	override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean = oldItem.id == newItem.id

	override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean = oldItem == newItem
}