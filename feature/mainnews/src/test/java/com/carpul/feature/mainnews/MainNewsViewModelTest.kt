package com.carpul.feature.mainnews

import com.carpul.core.common.TestConstants
import com.carpul.feature.mainnews.MainNewsViewModel
import com.carpul.news.domain.usecases.GetLocalMainNewsPagedUseCase
import com.carpul.news.domain.usecases.UpdateArticleUseCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

@ExperimentalCoroutinesApi
class MainNewsViewModelTest {

    @RelaxedMockK
    private lateinit var getLocalMainNewsPagedUseCase: GetLocalMainNewsPagedUseCase

    @RelaxedMockK
    private lateinit var updateArticleUseCase: UpdateArticleUseCase

    private lateinit var mainNewsViewModel: MainNewsViewModel

    private val testScope = TestScope(UnconfinedTestDispatcher())

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        mainNewsViewModel = MainNewsViewModel(
            getLocalMainNewsPagedUseCase,
            updateArticleUseCase,
            Dispatchers.IO
        )
    }

    @Test
    fun `when click on save button then update article in data base`() {
        testScope.runTest {
            assertNotNull(mainNewsViewModel.updateSaveArticle(TestConstants.article))
        }
    }
}