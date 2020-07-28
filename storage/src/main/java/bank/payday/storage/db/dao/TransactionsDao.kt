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

	@Query("SELECT * FROM DTransaction ORDER BY date")
	suspend fun getAll(): List<DTransaction>


}