package bank.payday.core.mapper.customer

import bank.payday.core.mapper.Mapper
import bank.payday.network.models.customer.NCustomerData
import bank.payday.storage.db.models.customers.DCustomer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DCustomersMapper : Mapper<List<NCustomerData>, List<DCustomer>> {
	override suspend fun map(from: List<NCustomerData>): List<DCustomer> = withContext(Dispatchers.IO) {
		from.map { from ->
			DCustomer(
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
}