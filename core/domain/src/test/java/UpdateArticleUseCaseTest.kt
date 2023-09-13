import com.carpul.core.common.TestConstants
import com.carpul.news.data.repository.NewsRepository
import com.carpul.news.domain.usecases.UpdateArticleUseCase
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
class UpdateArticleUseCaseTest {

    @RelaxedMockK
    private lateinit var mockRepository: NewsRepository

    @InjectMockKs
    private lateinit var updateArticleUseCase: UpdateArticleUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `every new update of data then result is not null`() = runBlocking {
        coEvery {
            mockRepository.updateArticle(TestConstants.article)
        } returns Unit

        val result = updateArticleUseCase(TestConstants.article)

        Assert.assertNotNull(result)
    }
}