package com.platzi.news.domain.usecases

import com.platzi.core.model.Article
import com.platzi.news.data.repository.NewsRepository
import javax.inject.Inject

class UpdateArticleUseCase @Inject constructor(private val newsRepository: NewsRepository)  {

    operator fun invoke(article: Article) = newsRepository.updateArticle(article)
}