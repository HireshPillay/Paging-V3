package com.android.paging.networking

import com.android.paging.utilities.Constants
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

val networkModule = module {
    factory { providesMoshiConverter() }
    factory { provideLoggingInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { providePagingApi(get()) }
    single { provideRetrofit(get(), get()) }
}

val VOID_JSON_ADAPTER: Any = object : Any() {
    @FromJson
    @Throws(IOException::class)
    fun fromJson(reader: JsonReader): Void? = reader.nextNull()

    @ToJson
    @Throws(IOException::class)
    fun toJson(writer: JsonWriter, v: Void?) {
        writer.nullValue()
    }
}

fun providesMoshiConverter(): Moshi = Moshi.Builder()
        .add(VOID_JSON_ADAPTER)
        .add(KotlinJsonAdapterFactory())
        .build()

fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .build()

fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(loggingInterceptor)
        .build()

fun providePagingApi(retrofit: Retrofit): PagingApis = retrofit.create(PagingApis::class.java)

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BODY
    return logger
}
