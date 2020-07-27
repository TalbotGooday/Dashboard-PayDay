package bank.payday.network.models.transactions


import com.google.gson.annotations.SerializedName

data class NTransaction(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("account_id")
    val accountId: Int = 0,
    @SerializedName("amount")
    val amount: String = "",
    @SerializedName("vendor")
    val vendor: String = "",
    @SerializedName("category")
    val category: String = "",
    @SerializedName("date")
    val date: String = ""
)