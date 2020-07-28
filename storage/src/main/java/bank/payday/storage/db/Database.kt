package bank.payday.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bank.payday.storage.db.dao.CustomersDao
import bank.payday.storage.db.dao.TransactionsDao
import bank.payday.storage.db.models.customers.DCustomer
import bank.payday.storage.db.models.transactions.DTransaction

@Database(
		entities = [
			DTransaction::class,
			DCustomer::class
		],
		version = 1,
		exportSchema = false
)
abstract class PdDatabase : RoomDatabase() {
	companion object {
		fun create(context: Context, useInMemory: Boolean): PdDatabase {
			val databaseBuilder = if (useInMemory) {
				Room.inMemoryDatabaseBuilder(context, PdDatabase::class.java)
			} else {
				Room.databaseBuilder(context, PdDatabase::class.java, "pd.database")
			}

			return databaseBuilder
					.fallbackToDestructiveMigration()
					.build()
		}
	}

	abstract fun transactionsDao(): TransactionsDao
	abstract fun customersDao(): CustomersDao
}
