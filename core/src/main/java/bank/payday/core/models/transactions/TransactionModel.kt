package bank.payday.core.models.transactions

class TransactionModel(
		val id: Int = 0,
		val accountId: Int,
		val amount: String,
		val vendor: String = "",
		val category: String = "",
		val date: String = "",
		val isHeader: Boolean = false
) {
	constructor(date: String) : this(
			id = 0,
			accountId = 0,
			amount = "",
			vendor = "",
			category = "",
			date = date,
			isHeader = true
	)
}