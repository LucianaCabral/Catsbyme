import com.lcabral.catsbyme.core.data.remote.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY = "api_key"

internal class QueryInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = request.url.newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.API_KEY)
            .build()
        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()

        return  chain.proceed(newRequest)
    }
}