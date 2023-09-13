import com.carpul.core.common.TestConstants
import com.carpul.news.data.repository.NewsRepository
import com.carpul.news.domain.usecases.GetLocalSavedNewsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class GetLocalSavedNewsUseCaseTest {

    @RelaxedMockK
    private lateinit var mockRepository: NewsRepository

    @InjectMockKs
    private lateinit var getLocalSavedNewsUseCase: GetLocalSavedNewsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `every time watch section on saved news then fetch data from data base`() = runBlocking {

        coEvery {
            mockRepository.getSavedNews()
        } returns TestConstants.flowArticles

        val result = getLocalSavedNewsUseCase()

        Assert.assertNotNull(result)
        Assert.assertEquals(result, TestConstants.flowArticles)
    }
}