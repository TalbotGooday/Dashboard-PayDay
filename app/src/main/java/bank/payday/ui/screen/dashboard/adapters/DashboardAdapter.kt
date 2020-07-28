package bank.payday.ui.screen.dashboard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bank.payday.R
import bank.payday.core.models.dashboard.DashboardModel
import kotlinx.android.synthetic.main.item_dashboard.view.*
import kotlinx.android.synthetic.main.item_dashboard_header.view.*


class DashboardAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
	companion object {
		private const val HEADER = 0
		private const val ITEM = 1
	}

	override fun getItemViewType(position: Int): Int {
		return if (this.data[position].isHeader) HEADER else ITEM
	}

	private var data: MutableList<DashboardModel> = mutableListOf()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		val inflater = LayoutInflater.from(parent.context)

		return if (viewType == HEADER) {
			HeaderHolder(inflater.inflate(R.layout.item_dashboard_header, parent, false))
		} else {
			ItemHolder(inflater.inflate(R.layout.item_dashboard, parent, false))
		}
	}

	override fun getItemCount() = data.size

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		if (holder is HeaderHolder) {
			holder.bind(data[position])
		} else if (holder is ItemHolder) {
			holder.bind(data[position])
		}
	}

	fun swapData(data: List<DashboardModel>) {
		this.data.clear()
		this.data.addAll(data)
		notifyDataSetChanged()
	}

	fun addData(data: List<DashboardModel>) {
		this.data.addAll(data)
		notifyDataSetChanged()
	}

	class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: DashboardModel) = with(itemView) {
			title.text = item.title
			transaction_value.text = item.amount
		}
	}

	class HeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: DashboardModel) = with(itemView) {
			header_title.text = item.date
			header_amount.text = item.amount
		}
	}
}