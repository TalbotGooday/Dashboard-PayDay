package bank.payday.network.modules

import android.content.Context
import bank.payday.network.Api
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "http://localhost:3000"

val netModule = module {
	fun provideCache(context: Context): Cache {
		val cacheSize = 10 * 1024 * 1024
		return Cache(context.cacheDir, cacheSize.toLong())
	}

	fun provideHttpClient(
			cache: Cache
	): OkHttpClient {
		return OkHttpClient.Builder()
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
				.addCallAdapterFactory(CoroutineCallAdapterFactory())
				.client(client)
				.build()

		return retrofit.create(Api::class.java)
	}

	single { provideCache(androidContext()) }
	single { provideHttpClient(get()) }
	single { provideGson() }
	single { provideRetrofit(get(), get()) }
}
