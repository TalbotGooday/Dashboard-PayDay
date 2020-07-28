package bank.payday.storage.db.models.transactions

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DTransaction(
		@PrimaryKey
		val id: Int = 0,
		val accountId: Int,
		val amount: Double = 0.0,
		val vendor: String = "",
		val category: String = "",
		val date: String = ""
)