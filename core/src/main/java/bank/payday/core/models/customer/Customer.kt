package bank.payday.core.models.customer

class Customer(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "",
    val email: String = "",
    val password: String = "",
    val dob: String = "",
    val phone: String = ""
)