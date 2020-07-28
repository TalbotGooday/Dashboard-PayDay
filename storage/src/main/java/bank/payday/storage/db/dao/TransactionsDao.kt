package bank.payday.storage.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bank.payday.storage.db.models.transactions.DTransaction

@Dao
interface TransactionsDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addAll(data: List<DTransaction>)

	@Query("SELECT * FROM DTransaction ORDER BY date DESC")
	suspend fun getAll(): List<DTransaction>

	@Query("SELECT * FROM DTransaction WHERE amount < 0.0 ORDER BY date DESC")
	suspend fun getAllExpenses(): List<DTransaction>

}