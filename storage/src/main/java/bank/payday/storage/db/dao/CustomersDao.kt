package bank.payday.storage.db.dao

import androidx.room.*
import bank.payday.storage.db.models.customers.DCustomer
import bank.payday.storage.db.models.customers.DUser

@Dao
interface CustomersDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addAll(data: List<DCustomer>)

	@Query("SELECT * FROM DCustomer")
	suspend fun getAll(): List<DCustomer>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveCurrentUser(user: DUser)

	@Query("SELECT * FROM DUser LIMIT 1")
	suspend fun getCurrentLogged(): DUser?

	@Query("SELECT * FROM DCustomer WHERE email = :email LIMIT 1")
	suspend fun getCustomerByEmail(email: String): DCustomer?

	@Query("SELECT * FROM DCustomer WHERE phone = :phone LIMIT 1")
	suspend fun getCustomerByPhone(phone: String): DCustomer?

	@Delete
	suspend fun clearCurrentCustomer(user: DUser)

	@Transaction
	suspend fun logout() {
		getCurrentLogged()?.let { clearCurrentCustomer(it) }
	}
}