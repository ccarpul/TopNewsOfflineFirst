package com.platzi.news.data

import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import com.platzi.core.network.model.NetworkNewsResource
import java.util.Locale
import java.util.concurrent.CancellationException


interface Synchronizer {

    suspend fun Syncable.sync() =
        this@sync.syncWith()
}

interface Syncable {

    companion object {
        const val DEFAULT_PAGE = 1
        const val DEFAULT_PAGE_SIZE = 10
    }

    suspend fun syncWith(
        page: Int = DEFAULT_PAGE,
        pageSize: Int = DEFAULT_PAGE_SIZE,
        country: String = Locale.US.country.lowercase(),
    ): Boolean
}

private suspend fun <T> suspendRunCatching(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (exception: Exception) {
    Log.i(
        "suspendRunCatching",
        "Failed to evaluate a suspendRunCatchingBlock. Returning failure Result",
        exception,
    )
    Result.failure(exception)
}

suspend fun changeListSync(
    changeListFetcher: suspend () -> List<NetworkNewsResource>,
    modelUpdater: suspend (List<NetworkNewsResource>) -> Unit,
) = suspendRunCatching {

    val changeList = changeListFetcher()
    if (changeList.isEmpty()) return@suspendRunCatching true
    modelUpdater(changeList)
}.isSuccess


val SyncConstraints
    get() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()