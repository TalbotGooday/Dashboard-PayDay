package bank.payday.extensions

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Set the visibility state of this view to [View.GONE]
 */
fun View.gone() {
	if (visibility != View.GONE) {
		visibility = View.GONE
	}
}

/**
 * Set the visibility state of this view to [View.GONE] with fade out animation
 *
 * @param duration
 */
fun View.animateGone(duration: Long = 100) {
	if (visibility != View.GONE) {
		animate().setDuration(duration)
				.alpha(0f)
				.withEndAction {
					visibility = View.GONE
				}
	}
}

/**
 * Set the visibility state of this view to [View.VISIBLE]
 */
fun View.visible() {
	if (visibility != View.VISIBLE) {
		visibility = View.VISIBLE
	}
}

/**
 * Returns the visibility status for this view.
 *
 * @return true if view.visibility == [View.VISIBLE] and false in other cases
 */
fun View.isVisible() = visibility == View.VISIBLE

/**
 * Set the visibility state of this view to [View.VISIBLE] with fade in animation
 *
 * @param duration
 */
fun View.animateVisible(duration: Long = 100) {
	if (visibility != View.VISIBLE) {
		alpha = 0f
		visibility = View.VISIBLE
		animate().setDuration(duration)
				.alpha(1f)
	}
}

/**
 * Set the visibility state of this view to [View.INVISIBLE]
 */
fun View.invisible() {
	if (visibility != View.INVISIBLE) {
		visibility = View.INVISIBLE
	}
}

/**
 * Set the visibility state of this view to [View.INVISIBLE] with fade out animation
 *
 * @param duration
 */
fun View.animateInvisible(duration: Long = 100) {
	if (visibility != View.INVISIBLE) {
		animate().setDuration(duration)
				.alpha(0f)
				.withEndAction {
					visibility = View.INVISIBLE
				}
	}
}

/**
 * Set the visibility state of this view to [View.VISIBLE] or [View.GONE]
 *
 * @param visible
 */
fun View.visibleOrGone(visible: Boolean) {
	visibility = if (visible) View.VISIBLE else View.GONE
}

/**
 * Set the visibility state of this view to [View.VISIBLE] or [View.INVISIBLE]
 *
 * @param visible
 */
fun View.visibleOrInvisible(visible: Boolean) {
	visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun View?.remove() {
	this ?: return
	this.parent ?: return

	if (this.parent !is ViewGroup) return

	(this.parent as ViewGroup).removeView(this)
}
