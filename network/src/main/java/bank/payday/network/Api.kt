package bank.payday.network

import bank.payday.network.models.accounts.NAccount
import bank.payday.network.models.customer.NCustomerData
import bank.payday.network.models.transactions.NTransaction
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
	@POST("/authenticate")
	suspend fun signIn(@Body jsonData: HashMap<String, Any?>): NCustomerData

	@POST("/customers")
	suspend fun signUp(@Body jsonData: HashMap<String, Any?>): NCustomerData

	@GET("/customers")
	suspend fun loadCustomers(): List<NCustomerData>

	@GET("/accounts")
	suspend fun loadAccounts(): List<NAccount>

	@GET("/transactions")
	suspend fun loadTransactions(): List<NTransaction>
}