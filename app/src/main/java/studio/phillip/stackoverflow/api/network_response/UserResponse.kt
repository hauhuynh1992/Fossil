package studio.phillip.stackoverflow.api.network_response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse(
    @SerializedName("user_id")
    @Expose
    var user_id: Int,

    @SerializedName("display_name")
    @Expose
    var display_name: String,

    @SerializedName("location")
    @Expose
    var location: String,

    @SerializedName("last_access_date")
    @Expose
    var last_access_date: Long,

    @SerializedName("reputation")
    @Expose
    var reputation: Int

) {
    override fun toString(): String {
        return "UserResponse(user_id='$user_id', display_name='$display_name', location='$location',last_access_date='$last_access_date',reputation='$reputation')"
    }
}