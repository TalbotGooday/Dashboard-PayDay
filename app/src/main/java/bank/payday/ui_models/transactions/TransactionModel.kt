package bank.payday.ui_models.transactions

import java.util.*

class TransactionModel() {
	var description: String = "Hello"
	var date: String = Date().time.toString()
	var transactionValue: Double = 0.0

	constructor(description: String, date: String, transactionValue: Double) : this() {
		this.description = description
		this.date = description
		this.transactionValue = transactionValue
	}

	constructor(transactionValue: Double) : this() {
		this.transactionValue = transactionValue
	}
}