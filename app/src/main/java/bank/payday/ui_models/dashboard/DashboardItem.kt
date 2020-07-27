package bank.payday.ui_models.dashboard

import java.util.*

class DashboardItem() {
	var date: String = Date().time.toString()
	var transactionsSum: Double = 0.0
	var title: String = "Some shit"
	var isHeader: Boolean = false
}