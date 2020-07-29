package bank.payday.core.mapper.customer

import bank.payday.core.mapper.Mapper
import bank.payday.network.models.customer.NCustomerData
import bank.payday.storage.db.models.customers.DUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DUserMapper : Mapper<NCustomerData, DUser> {
	override suspend fun map(from: NCustomerData): DUser = withContext(Dispatchers.IO) {
		DUser(
				id = from.id,
				firstName = from.firstName,
				lastName = from.lastName,
				gender = from.gender,
				email = from.email,
				password = from.password,
				dob = from.dob,
				phone = from.phone
		)
	}
}