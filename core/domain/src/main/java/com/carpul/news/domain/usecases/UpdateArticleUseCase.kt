package com.carpul.news.domain.usecases

import com.carpul.core.model.Article
import com.carpul.news.data.repository.NewsRepository
import javax.inject.Inject

class UpdateArticleUseCase @Inject constructor(private val newsRepository: NewsRepository)  {

    operator fun invoke(article: Article) = newsRepository.updateArticle(article)
}