package bank.payday.core.mapper.customer

import bank.payday.core.mapper.Mapper
import bank.payday.core.models.customer.Customer
import bank.payday.network.models.customer.NCustomerData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CustomerMapper : Mapper<NCustomerData, Customer> {
	override suspend fun map(from: NCustomerData): Customer = withContext(Dispatchers.IO) {
		Customer(
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