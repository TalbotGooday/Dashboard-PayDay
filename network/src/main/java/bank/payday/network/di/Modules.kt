package bank.payday.network.di

import android.content.Context
import bank.payday.network.Api
import bank.payday.network.repository.ApiRepository
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "http://192.168.1.7:3000"

val netModule = module {
	fun provideCache(context: Context): Cache {
		val cacheSize = 10 * 1024 * 1024
		return Cache(context.cacheDir, cacheSize.toLong())
	}

	fun provideHttpClient(
			cache: Cache
	): OkHttpClient {
		val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
			level = HttpLoggingInterceptor.Level.BODY
		}

		return OkHttpClient.Builder()
				.addInterceptor(httpLoggingInterceptor)
				.connectTimeout(15, TimeUnit.SECONDS)
				.readTimeout(15, TimeUnit.SECONDS)
				.writeTimeout(3, TimeUnit.MINUTES)
				.cache(cache)
				.build()
	}

	fun provideGson(): Gson {
		return GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.create()
	}

	fun provideRetrofit(factory: Gson, client: OkHttpClient): Api {
		val retrofit = Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(factory))
				.client(client)
				.build()

		return retrofit.create(Api::class.java)
	}

	fun provideApiRepository(api: Api): ApiRepository {
		return ApiRepository(api)
	}

	single { provideCache(androidContext()) }
	single { provideHttpClient(get()) }
	single { provideGson() }
	single { provideRetrofit(get(), get()) }

	single { provideApiRepository(get()) }
}