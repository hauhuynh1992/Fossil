package studio.phillip.stackoverflow.api

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import studio.phillip.stackoverflow.api.network_response.UserResponse
import studio.phillip.stackoverflow.util.GenericApiResponse


interface StackOverflowApiService {

    @GET("users")
    fun getUser(
        @Query("page") page: Int,
        @Query("pagesize") pagesize: Int,
        @Query("site") site: String
    ): LiveData<GenericApiResponse<UserResponse>>

    @GET("users")
    fun getDetailUser(
        @Query("page") page: Int,
        @Query("pagesize") pagesize: Int,
        @Path("userid") userid: String
    ): LiveData<GenericApiResponse<UserResponse>>
}