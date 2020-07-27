package bank.payday.network.models.customer


import com.google.gson.annotations.SerializedName

data class NCustomerData(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("First Name")
    val firstName: String = "",
    @SerializedName("Last Name")
    val lastName: String = "",
    @SerializedName("gender")
    val gender: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("password")
    val password: String = "",
    @SerializedName("dob")
    val dob: String = "",
    @SerializedName("phone")
    val phone: String = ""
)