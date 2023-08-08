package testdouble

import com.platzi.core.database.room.dao.SavedNewsDao
import com.platzi.core.database.room.entities.SavedNewsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class TestSavedNewsDao: SavedNewsDao {

    private var entitiesStateFlow = MutableStateFlow(
        emptyList<SavedNewsEntity>(),
    )

    override fun insertArticle(article: SavedNewsEntity): Long {
        entitiesStateFlow.update { oldValues ->
            (oldValues + article).distinctBy(SavedNewsEntity::title)
        }
        return 0

    }

    override fun deleteArticle(article: SavedNewsEntity) {
        entitiesStateFlow.update { entities ->
            entities.filterNot { article.id == it.id }
        }
    }

    override fun getSavedNews(): Flow<List<SavedNewsEntity>> = entitiesStateFlow
}