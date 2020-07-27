package bank.payday.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bank.payday.storage.db.models.DTransaction

@Database(
		entities = [
			DTransaction::class
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

}
