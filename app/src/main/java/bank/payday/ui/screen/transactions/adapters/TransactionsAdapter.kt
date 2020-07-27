package bank.payday.ui.screen.transactions.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bank.payday.R
import bank.payday.ui_models.transactions.TransactionModel

class TransactionsAdapter(private val listener: Listener) : RecyclerView.Adapter<TransactionsAdapter.Holder>() {

	private var data: MutableList<TransactionModel> = mutableListOf()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
		return Holder(
				LayoutInflater.from(parent.context)
						.inflate(R.layout.item_transaction, parent, false)
		)
	}

	override fun getItemCount() = data.size

	override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(data[position], listener)

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
			// TODO: Bind the data with View
			setOnClickListener {
				listener.onItemClick(item)
			}
		}
	}

	interface Listener {
		fun onItemClick(item: TransactionModel)
	}
}