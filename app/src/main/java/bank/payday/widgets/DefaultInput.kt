package bank.payday.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.StringRes
import bank.payday.R
import bank.payday.extensions.gone
import bank.payday.extensions.invisible
import bank.payday.extensions.visible
import bank.payday.extensions.visibleOrInvisible
import kotlinx.android.synthetic.main.layout_input.view.*

class DefaultInput @JvmOverloads constructor(
		context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
	init {
		View.inflate(this.context, R.layout.layout_input, this)

		initView()
		getCustomAttrs(attrs)
	}


	val text
		get() = input_field.text

	fun setText(text: CharSequence) {
		input_field.setText(text)
	}

	fun validateLength(minLength: Int = 1): String? {
		val inputText = text

		if (inputText.isNullOrBlank()) {
			error(R.string.error_required_field)
			return null
		}

		if (inputText.length < minLength) {
			error(context.getString(R.string.error_field_length, minLength))

			return null
		}

		return inputText.toString()
	}

	fun validatePassword(minLength: Int = 0, reference: String): String? {
		val inputText = validateLength(minLength) ?: return null

		if (inputText != reference) {
			error(R.string.error_password_mismatch)
			return null
		}

		return inputText
	}

	private fun initView() {

	}


	private fun getCustomAttrs(attrs: AttributeSet?) {
		val arr = context?.obtainStyledAttributes(attrs, R.styleable.DefaultInput)
		arr?.run {
			for (i in 0 until indexCount) {
				when (val oneAttr = getIndex(i)) {
					R.styleable.DefaultInput_android_title -> {
						input_title.text = getString(oneAttr)
					}
					R.styleable.DefaultInput_android_hint -> {
						input_field.hint = getString(oneAttr)
					}
					R.styleable.DefaultInput_android_imeOptions -> {
						input_field.imeOptions = getInt(oneAttr, 0)
					}
					R.styleable.DefaultInput_android_inputType -> {
						val currentTypeface = input_field.typeface

						input_field.apply {
							inputType = getInt(oneAttr, 0)
							typeface = currentTypeface
						}

					}
					R.styleable.DefaultInput_visibilityError -> {
						input_error.visibleOrInvisible(getBoolean(oneAttr, false))
					}
				}
			}

			recycle()
		}
	}

	fun error(@StringRes message: Int) {
		error(context.getString(message))
	}

	fun error(message: String) {
		input_error.apply {
			visible()
			text = message
		}
	}

	fun hideError() {
		input_error.invisible()
	}
}