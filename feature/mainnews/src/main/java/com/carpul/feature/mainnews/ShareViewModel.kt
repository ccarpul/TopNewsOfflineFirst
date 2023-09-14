package com.carpul.feature.mainnews

import androidx.lifecycle.ViewModel

class ShareViewModel: ViewModel() {
    private var currentPage = 0

    fun saveCurrentPage(page: Int) {
        currentPage = page
    }

    fun getCurrentPage() = currentPage
}