package bank.payday.storage.db.models.customers

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DCustomer(
		@PrimaryKey
		val id: Int = 0,
		val firstName: String = "",
		val lastName: String = "",
		val gender: String = "",
		val email: String = "",
		val password: String = "",
		val dob: String = "",
		val phone: String = ""
)