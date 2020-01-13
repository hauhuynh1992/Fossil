package studio.phillip.stackoverflow.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id")
    var user_id: Int,

    @ColumnInfo(name = "display_name")
    var display_name: String,

    @ColumnInfo(name = "location")
    var location: String,

    @ColumnInfo(name = "last_access_date")
    var last_access_date: Long,

    @ColumnInfo(name = "reputation")
    var reputation: Int
) {

    override fun toString(): String {
        return "User(user_id=$user_id, " +
                "display_name='$display_name', " +
                "location='$location', " +
                "last_access_date='$last_access_date', " +
                "reputation=$reputation)"
    }
}