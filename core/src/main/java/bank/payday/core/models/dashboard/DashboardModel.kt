package bank.payday.core.models.dashboard

class DashboardModel(
		var date: String = "",
		var amount: String = "",
		var title: String = "",
		var isHeader: Boolean = false
) {
	constructor(date: String) : this(
			date = date,
			amount = "",
			title = "",
			isHeader = true
	)
}