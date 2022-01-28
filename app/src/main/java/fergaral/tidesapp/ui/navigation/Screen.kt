package fergaral.tidesapp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import fergaral.tidesapp.R

sealed class Screen(val route: String, @DrawableRes val navIconRes: Int, @StringRes val resourceId: Int) {
    object Favorites : Screen("favorites", R.drawable.ic_baseline_favorite_24, R.string.favorites)
    object Tides : Screen("tides", R.drawable.ic_baseline_waves_24, R.string.tides)
    object Ports : Screen("ports", R.drawable.ic_baseline_place_24, R.string.ports)
}