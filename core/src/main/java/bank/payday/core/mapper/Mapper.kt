package bank.payday.core.mapper

interface Mapper<T, R> {
    suspend fun map(from: T): R
}
