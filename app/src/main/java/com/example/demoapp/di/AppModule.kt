package com.example.demoapp.di

import android.content.Context
import androidx.room.Room
import com.example.demoapp.domain.DemoRepository
import com.example.demoapp.domain.usecase.DemoUseCase
import com.example.demoapp.data.DemoRepositoryImp
import com.example.demoapp.domain.RoomRepository
import com.example.demoapp.domain.usecase.InsertInfoUseCase
import com.example.demoapp.domain.usecase.MockApiUseCase
import com.example.demoapp.localdb.AppDatabase
import com.example.demoapp.localdb.MyDao
import com.example.demoapp.localdb.RoomRepositoryImp
import com.example.demoapp.network.DemoApi
import com.example.demoapp.utils.Contsant
import com.example.demoapp.utils.LogInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // This is used to add ApplicationInterceptor.
            .addNetworkInterceptor(LogInterceptor())
            .build()
    }
    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder().client(client).addConverterFactory(GsonConverterFactory.create()).baseUrl(
            Contsant.BASE_URL)

    }
    @Singleton
    @Provides
    fun providesUserApi(retrofitBuilder: Retrofit.Builder):DemoApi{
        return retrofitBuilder.build().create(DemoApi::class.java)
    }

  @Singleton
  @Provides
  fun ProvideRepositoryIml(demoApi:DemoApi):DemoRepository{
      return DemoRepositoryImp(demoApi)
  }

    @Singleton
    @Provides
    fun ProvideDemoUseCase(repository: DemoRepository,roomRepository: RoomRepository)= DemoUseCase(
        mockApiUseCase = MockApiUseCase(repository),
        insertInfo = InsertInfoUseCase(roomRepository)
    )

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "my_app_db"
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(db: AppDatabase) = db.myDao()
    @Singleton
    @Provides
    fun ProvideRoomRepositoryIml(myDao: MyDao):RoomRepository{
        return RoomRepositoryImp(myDao)
    }

}