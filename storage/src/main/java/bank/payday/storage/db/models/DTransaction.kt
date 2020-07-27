package bank.payday.storage.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DTransaction(
		@PrimaryKey
		val id: Int = 0,
		val amount: String = "",
		val vendor: String = "",
		val category: String = "",
		val date: String = ""
)