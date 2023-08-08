import com.platzi.core.common.TestConstants
import com.platzi.feature.mainnews.SavedNewsState
import com.platzi.feature.mainnews.SavedNewsViewModel
import com.platzi.feature.mainnews.navigation.FakeSavedNewsUseCase
import com.platzi.news.domain.usecases.UpdateArticleUseCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import kotlin.test.assertIs

@ExperimentalCoroutinesApi
class SavedNewsViewModelTest {

    private val coroutinesTestRule = TestCoroutineDispatcher()

    @RelaxedMockK
    private lateinit var updateArticleUseCase: UpdateArticleUseCase

    private lateinit var saveNewsViewModel: SavedNewsViewModel


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(coroutinesTestRule)
        saveNewsViewModel = SavedNewsViewModel(
            FakeSavedNewsUseCase(),
            updateArticleUseCase,
            UnconfinedTestDispatcher()
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        coroutinesTestRule.cleanupTestCoroutines()
    }

    @Test
    fun `when click on bottom bar to navigate to saved news screen then fetch saved news`() =
        runTest {
            assertEquals(
                SavedNewsState.Loading,
                saveNewsViewModel.mainNewsUiState.value
            )
        }

    @Test
    fun `when click on save button then update article in data base`() =
        runTest {
            assertNotNull(saveNewsViewModel.updateSaveArticle(TestConstants.article))
        }

    @Test
    fun `when the data base is updated then the flow of the data start in Loading state` () = runTest {
        val it = saveNewsViewModel.mainNewsUiState.value
        assertIs<SavedNewsState.Loading>(it)
    }

    @Test
    fun `when the data base is updated and the data is received then the flow of the data change  to Success state` () = runTest {
        val job = launch( UnconfinedTestDispatcher(testScheduler) ) {
            saveNewsViewModel.mainNewsUiState.collect()
        }
        val item = saveNewsViewModel.mainNewsUiState.value
        saveNewsViewModel.updateSaveArticle(TestConstants.article)
        assertIs<SavedNewsState.Success>(item)

        job.cancel()
    }
}
