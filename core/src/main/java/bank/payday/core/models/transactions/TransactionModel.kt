package bank.payday.core.models.transactions

class TransactionModel(
		val id: Int = 0,
		val accountId: Int,
		val amount: String,
		val vendor: String = "",
		val category: String = "",
		val date: String = ""
)