package studio.phillip.stackoverflow.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import studio.phillip.stackoverflow.models.User
import studio.phillip.stackoverflow.util.Constants.Companion.PAGINATION_PAGE_SIZE

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogPost: User): Long

    @Query(
        """
        SELECT * FROM users
        WHERE display_name LIKE '%' || :query || '%'
        OR location LIKE '%' || :query || '%'
        LIMIT (:page * :pageSize)
        """
    )
    fun getAllUsers(
        query: String,
        page: Int,
        pageSize: Int = PAGINATION_PAGE_SIZE
    ): LiveData<List<User>>
//
//    @Query("""
//        SELECT * FROM blog_post
//        WHERE title LIKE '%' || :query || '%'
//        OR body LIKE '%' || :query || '%'
//        OR username LIKE '%' || :query || '%'
//        ORDER BY date_updated DESC LIMIT (:page * :pageSize)
//        """)
//    fun searchBlogPostsOrderByDateDESC(
//        query: String,
//        page: Int,
//        pageSize: Int = PAGINATION_PAGE_SIZE
//    ): LiveData<List<User>>
//
//    @Query("""
//        SELECT * FROM blog_post
//        WHERE title LIKE '%' || :query || '%'
//        OR body LIKE '%' || :query || '%'
//        OR username LIKE '%' || :query || '%'
//        ORDER BY date_updated  ASC LIMIT (:page * :pageSize)""")
//    fun searchBlogPostsOrderByDateASC(
//        query: String,
//        page: Int,
//        pageSize: Int = PAGINATION_PAGE_SIZE
//    ): LiveData<List<User>>
//
//    @Query("""
//        SELECT * FROM blog_post
//        WHERE title LIKE '%' || :query || '%'
//        OR body LIKE '%' || :query || '%'
//        OR username LIKE '%' || :query || '%'
//        ORDER BY username DESC LIMIT (:page * :pageSize)""")
//    fun searchBlogPostsOrderByAuthorDESC(
//        query: String,
//        page: Int,
//        pageSize: Int = PAGINATION_PAGE_SIZE
//    ): LiveData<List<User>>
//
//    @Query("""
//        SELECT * FROM blog_post
//        WHERE title LIKE '%' || :query || '%'
//        OR body LIKE '%' || :query || '%'
//        OR username LIKE '%' || :query || '%'
//        ORDER BY username  ASC LIMIT (:page * :pageSize)
//        """)
//    fun searchBlogPostsOrderByAuthorASC(
//        query: String,
//        page: Int,
//        pageSize: Int = PAGINATION_PAGE_SIZE
//    ): LiveData<List<User>>


}