package bank.payday.ui.screen.transactions.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bank.payday.R
import bank.payday.core.models.transactions.TransactionModel
import kotlinx.android.synthetic.main.item_transaction.view.*
import kotlinx.android.synthetic.main.item_transaction_header.view.*

class TransactionsAdapter(private val listener: Listener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
	companion object {
		private const val HEADER = 0
		private const val ITEM = 1
	}

	private var data: MutableList<TransactionModel> = mutableListOf()

	override fun getItemViewType(position: Int): Int {
		return if (this.data[position].isHeader) HEADER else ITEM
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		return if (viewType == HEADER) {
			HeaderHolder(inflater.inflate(R.layout.item_transaction_header, parent, false))
		} else {
			Holder(inflater.inflate(R.layout.item_transaction, parent, false))
		}
	}

	override fun getItemCount() = data.size

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		if (holder is HeaderHolder) {
			holder.bind(data[position])
		} else if (holder is Holder) {
			holder.bind(data[position], listener)
		}
	}

	fun swapData(data: List<TransactionModel>) {
		this.data.clear()
		this.data.addAll(data)
		notifyDataSetChanged()
	}

	fun addData(data: List<TransactionModel>) {
		this.data.addAll(data)
		notifyDataSetChanged()
	}

	class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: TransactionModel, listener: Listener) = with(itemView) {
			date.text = item.date
			title.text = item.vendor
			transaction_value.text = item.amount

			setOnClickListener {
				listener.onItemClick(item)
			}
		}
	}

	class HeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: TransactionModel) = with(itemView) {
			header_title.text = item.date
		}
	}

	interface Listener {
		fun onItemClick(item: TransactionModel)
	}
}