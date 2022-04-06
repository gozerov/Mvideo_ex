package ru.gozerov.mvideo_ex.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gozerov.data.remote.news.service.NEWS_BASE_URL
import ru.gozerov.data.remote.news.service.NewsService
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideNewsService(): NewsService {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(NEWS_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(NewsService::class.java)
    }

}