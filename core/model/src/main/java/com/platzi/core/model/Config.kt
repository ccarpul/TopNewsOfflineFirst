package com.platzi.core.model

import java.util.Locale

object Config {

    var country: String = Locale.US.country.lowercase()
}