package com.androiddevs.mvvmnewsapp.UI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.models.newsResponse
import com.androiddevs.mvvmnewsapp.repo.NewsRepo
import com.androiddevs.mvvmnewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepo : NewsRepo
) : ViewModel() {

    val breakingNews :MutableLiveData<Resource<newsResponse>> = MutableLiveData()
    var breakingNewsPage = 1

    val searchNews :MutableLiveData<Resource<newsResponse>> = MutableLiveData()
    var searchNewsPage = 1

    init {
        getBreakingNews("eg")
    }

    fun getBreakingNews(countryCode: String)= viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepo.getBreakingNews(countryCode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse( response))
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepo.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<newsResponse>):Resource<newsResponse>{
        if (response.isSuccessful){
            response.body()?.let {resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    private fun handleSearchNewsResponse(response: Response<newsResponse>):Resource<newsResponse>{
        if (response.isSuccessful){
            response.body()?.let {resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}