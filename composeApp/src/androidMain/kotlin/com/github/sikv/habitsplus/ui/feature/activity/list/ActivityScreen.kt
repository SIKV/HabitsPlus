package com.github.sikv.habitsplus.ui.feature.activity.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.data.model.ActivityModel
import com.github.sikv.habitsplus.feature.activity.list.ActivityListAction
import com.github.sikv.habitsplus.feature.activity.list.ActivityListState
import com.github.sikv.habitsplus.store.AppStore
import com.github.sikv.habitsplus.ui.feature.common.ScaffoldMenu
import com.github.sikv.habitsplus.ui.feature.common.ScaffoldMenuItem
import com.github.sikv.habitsplus.ui.feature.common.scaffoldMenuItems
import com.github.sikv.habitsplus.ui.theme.spacing
import com.github.sikv.habitsplus.util.DateTimeFormatter
import com.github.sikv.habitsplus.util.LocalDateTimeFormatter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.mapLatest
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@Composable
fun ActivityScreen(
    onMenuItemClick: (ScaffoldMenuItem) -> Unit,
    store: AppStore = koinInject()
) {
    val state by store.observeState()
        .mapLatest { it.activityListState }
        .collectAsStateWithLifecycle(ActivityListState())

    LaunchedEffect(Unit) {
        store.dispatch(ActivityListAction.Init)
    }

    val dateTimeProvider = LocalDateTimeFormatter.current

    ScaffoldMenu(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.activity_screen_title)) },
                actions = {
                    YearSelector(
                        selectedYear = state.selectedYear,
                        years = state.yearsFilter,
                        onYearSelect = { year ->
                            store.dispatch(ActivityListAction.FetchAll(year = year))
                        }
                    )
                }
            )
        },
        menuItems = scaffoldMenuItems,
        onMenuItemClick = onMenuItemClick
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.spacing.medium)
            ) {
                state.activities.forEach { activityGroup ->
                    item(span = { GridItemSpan(4) }) {
                        ActivityItemSection(dateTimeProvider.format(
                            LocalContext.current,
                            activityGroup.monthTimestamp(),
                            DateTimeFormatter.Pattern.FULL_MONTH_NAME)
                        )
                    }
                    items(activityGroup.activities) { activity ->
                        ActivityItem(activity)
                    }
                }
            }
        }
    }
}

@Composable
fun YearSelector(
    selectedYear: Int?,
    years: Set<Int>,
    onYearSelect: (Int) -> Unit
) {
    if (selectedYear == null) {
        return
    }

    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        OutlinedButton(
            onClick = {
                expanded = !expanded
            }
        ) {
            Text(selectedYear.toString())
            Icon(Icons.Filled.KeyboardArrowDown, contentDescription = null)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            years.forEach { year ->
                DropdownMenuItem(
                    text = { Text(year.toString()) },
                    onClick = {
                        onYearSelect(year)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun ActivityItemSection(text: String) {
    Text(text,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = MaterialTheme.spacing.small)
    )
}

@Composable
private fun ActivityItem(activity: ActivityModel) {
    val dateTimeProvider = LocalDateTimeFormatter.current

    Card(
        modifier = Modifier.aspectRatio(1f)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            // TODO: Use autosize text.
            Text(
                dateTimeProvider.format(LocalContext.current, activity.date, DateTimeFormatter.Pattern.DAY_NAME),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
            // TODO: Use autosize text.
            Text(
                dateTimeProvider.format(LocalContext.current, activity.date, DateTimeFormatter.Pattern.DAY_NUMBER),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}
