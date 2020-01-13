package studio.phillip.stackoverflow.api.network_response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DetailUserResponse(
    @SerializedName("reputation_history_type")
    @Expose
    var reputation_history_type: String,

    @SerializedName("reputation_change")
    @Expose
    var reputation_change: Int,

    @SerializedName("creation_date")
    @Expose
    var creation_date: Long,

    @SerializedName("post_id")
    @Expose
    var post_id: Int

) {
    override fun toString(): String {
        return "DetailUserResponse(reputation_history_type='$reputation_history_type', reputation_change='$reputation_change', creation_date='$creation_date',post_id='$post_id')"
    }
}