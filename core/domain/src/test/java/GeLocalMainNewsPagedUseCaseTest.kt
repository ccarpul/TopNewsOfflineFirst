
import com.platzi.news.data.repository.NewsRepository
import com.platzi.news.domain.usecases.GetLocalSavedNewsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GeLocalMainNewsPagedUseCaseTest {
    @RelaxedMockK
    private lateinit var mockRepository: NewsRepository

    @InjectMockKs
    private lateinit var getLocalSavedNewsUseCase: GetLocalSavedNewsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `every time watch section home and exist data en database then fetch data from data base`() = runBlocking {
        coEvery {
            mockRepository.getMainNewsPagedDao()
        } returns flow { }

        val result = getLocalSavedNewsUseCase()

        Assert.assertNotNull(result)
    }
}