package dev.swapnil.contacts.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.viewbinding.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.swapnil.contacts.db.MessageDatabase
import dev.swapnil.contacts.networking.ApiService
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideOkhttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://demo0762190.mockable.io/")
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            ))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context) = Room
        .databaseBuilder(
            context,
            MessageDatabase::class.java,
            "message_db"
        ).build()

    @Singleton
    @Provides
    fun provideDao(db: MessageDatabase) = db.messageDao()
}