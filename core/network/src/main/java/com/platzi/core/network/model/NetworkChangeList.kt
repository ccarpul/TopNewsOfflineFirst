package com.platzi.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkChangeList(
    val id: String,
    val changeListVersion: Int,
    val isDelete: Boolean,
)