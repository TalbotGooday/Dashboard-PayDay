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

	@Query("SELECT * FROM DTransaction WHERE accountId == :accountId ORDER BY date DESC")
	suspend fun getAll(accountId: Int): List<DTransaction>

	@Query("SELECT * FROM DTransaction WHERE amount >= 0.0 AND accountId == :accountId AND date BETWEEN :dateStart and :dateEnd ORDER BY date DESC")
	suspend fun getDebit(accountId: Int, dateStart: String, dateEnd: String): List<DTransaction>

	@Query("SELECT * FROM DTransaction WHERE amount < 0.0 AND accountId == :accountId AND date BETWEEN :dateStart and :dateEnd ORDER BY date DESC")
	suspend fun getCredit(accountId: Int, dateStart: String, dateEnd: String): List<DTransaction>

}