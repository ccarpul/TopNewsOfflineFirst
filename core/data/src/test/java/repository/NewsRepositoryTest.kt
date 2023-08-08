package repository

import com.platzi.core.common.TestConstants
import com.platzi.core.database.room.dao.MainNewsDao
import com.platzi.core.database.room.dao.SavedNewsDao
import com.platzi.core.database.room.entities.SavedNewsEntity
import com.platzi.core.database.room.entities.asExternalArticle
import com.platzi.core.database.room.entities.asSavedEntity
import com.platzi.news.data.repository.INewsRepository
import com.platzi.news.data.repository.NewsRepository
import com.platzi.news.data.repository.paging.MainNewsRemoteMediator
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import testdouble.TestSavedNewsDao

@ExperimentalCoroutinesApi
class NewsRepositoryTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    @MockK(relaxed = true)
    private lateinit var mainNewsDao: MainNewsDao


    private lateinit var savedNewsDao: SavedNewsDao

    @MockK(relaxed = true)
    private lateinit var mainNewsRemoteMediator: MainNewsRemoteMediator


    private lateinit var newsRepository: INewsRepository

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        savedNewsDao = TestSavedNewsDao()
        newsRepository = NewsRepository(
            mainNewsDao,
            mainNewsRemoteMediator,
            savedNewsDao
        )
    }

    @Test
    fun getSavedNews() =
        testScope.runTest {
            assertEquals(
                savedNewsDao.getSavedNews().first().map(SavedNewsEntity::asExternalArticle),
                newsRepository.getSavedNews().first(),
            )
        }

    @Test
    fun `when click in icon save to save an article, then article is added in the data base`() =
        testScope.runTest {

            assertEquals(
                0L,
                savedNewsDao.insertArticle(TestConstants.article.asSavedEntity()),
            )
        }


    @Test
    fun `when click in icon save on an article save, then article is deleted from data base`() {
        assertNotNull(
            savedNewsDao.deleteArticle(TestConstants.article.asSavedEntity()),
        )
    }
}