package com.burak.btcturkcase.common

object Constants {
    const val BASE_URL = "https://api.btcturk.com/"
    const val GRAPH_BASE_URL = "https://graph-api.btcturk.com/v1/"
    const val API_SERVICE_TIMEOUT: Long = 60
    const val TRANSACTION_INTERVAL_MS = 2000L
    const val MULTI_SERIES_COUNT = 3
    const val ENTRY_COUNT = 50
    const val MAX_Y = 20
    const val COLUMN_LAYER_MIN_Y = 2
    const val COLUMN_LAYER_RELATIVE_MAX_Y = MAX_Y - COLUMN_LAYER_MIN_Y

}