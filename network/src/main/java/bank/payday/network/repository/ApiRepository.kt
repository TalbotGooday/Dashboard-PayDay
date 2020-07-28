package bank.payday.network.repository

import bank.payday.network.Api
import bank.payday.network.models.accounts.NAccount
import bank.payday.network.models.customer.NCustomerData
import bank.payday.network.models.transactions.NTransaction
import java.text.SimpleDateFormat
import java.util.*

class ApiRepository(
		private val api: Api
) {
	suspend fun signIn(email: String, password: String): NCustomerData {
		return api.signIn(hashMapOf(
				"email" to email,
				"password" to password
		))
	}

	suspend fun signUp(
			firstName: String,
			lastName: String,
			gender: String,
			email: String,
			password: String,
			phone: String,
			birthTimestamp: Long
	): NCustomerData {
		return api.signUp(hashMapOf(
				"First Name" to firstName,
				"Last Name" to lastName,
				"gender" to gender,
				"email" to email,
				"password" to password,
				"phone" to phone,
				"dob" to birthTimestamp.toISO8601()
		))
	}

	private fun Long.toISO8601(): String {
		return SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.ENGLISH).apply {
			timeZone = TimeZone.getTimeZone("UTC")
		}.format(this)
	}

	suspend fun loadCustomers(): List<NCustomerData> {
		return api.loadCustomers()
	}

	suspend fun loadAccounts(): List<NAccount> {
		return api.loadAccounts()
	}

	suspend fun loadTransactions(): List<NTransaction> {
		return api.loadTransactions()
	}
}
