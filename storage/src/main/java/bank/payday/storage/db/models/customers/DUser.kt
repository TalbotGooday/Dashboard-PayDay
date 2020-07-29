package bank.payday.storage.db.models.customers

import androidx.room.Entity

@Entity
class DUser(
		id: Int = 0,
		firstName: String = "",
		lastName: String = "",
		gender: String = "",
		email: String = "",
		password: String = "",
		dob: String = "",
		phone: String = ""
) : DCustomer(
		id = id,
		firstName = firstName,
		lastName = lastName,
		gender = gender,
		email = email,
		password = password,
		dob = dob,
		phone = phone
)