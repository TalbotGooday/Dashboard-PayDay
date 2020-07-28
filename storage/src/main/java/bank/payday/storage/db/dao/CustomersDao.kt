package bank.payday.storage.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bank.payday.storage.db.models.customers.DCustomer

@Dao
interface CustomersDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addAll(data: List<DCustomer>)

	@Query("SELECT * FROM DCustomer")
	suspend fun getAll(): List<DCustomer>


}