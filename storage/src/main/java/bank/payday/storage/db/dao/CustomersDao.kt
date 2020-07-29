package bank.payday.storage.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bank.payday.storage.db.models.customers.DCustomer
import bank.payday.storage.db.models.customers.DUser

@Dao
interface CustomersDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addAll(data: List<DCustomer>)

	@Query("SELECT * FROM DCustomer")
	suspend fun getAll(): List<DCustomer>

	@Query("SELECT * FROM DUser LIMIT 1")
	suspend fun getCurrentLogged(): DUser?

}