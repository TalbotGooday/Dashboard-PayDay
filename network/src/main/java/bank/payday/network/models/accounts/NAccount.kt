package bank.payday.network.models.accounts


import com.google.gson.annotations.SerializedName

data class NAccount(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("customer_id")
    val customerId: Int = 0,
    @SerializedName("iban")
    val iban: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("date_created")
    val dateCreated: String = "",
    @SerializedName("active")
    val active: Boolean = false
)