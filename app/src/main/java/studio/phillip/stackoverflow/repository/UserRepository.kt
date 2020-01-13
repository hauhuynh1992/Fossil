package studio.phillip.stackoverflow.repository

import studio.phillip.stackoverflow.api.StackOverflowApiService
import studio.phillip.stackoverflow.persistence.UserDao
import javax.inject.Inject

class UserRepository
@Inject
constructor(
    val openApiMainService: StackOverflowApiService,
    val blogPostDao: UserDao
) : JobManager("UserRepository") {

    private val TAG: String = "AppDebug"

//    fun searchBlogPosts(
//        authToken: AuthToken,
//        query: String,
//        filterAndOrder: String,
//        page: Int
//    ): LiveData<DataState<BlogViewState>> {
//        return object : NetworkBoundResource<BlogListSearchResponse, List<User>, BlogViewState>(
//            sessionManager.isConnectedToTheInternet(),
//            true,
//            false,
//            true
//        ) {
//            // if network is down, view cache only and return
//            override suspend fun createCacheRequestAndReturn() {
//                withContext(Dispatchers.Main) {
//
//                    // finishing by viewing db cache
//                    result.addSource(loadFromCache()) { viewState ->
//                        viewState.blogFields.isQueryInProgress = false
//                        if (page * PAGINATION_PAGE_SIZE > viewState.blogFields.blogList.size) {
//                            viewState.blogFields.isQueryExhausted = true
//                        }
//                        onCompleteJob(DataState.data(viewState, null))
//                    }
//                }
//            }
//
//            override suspend fun handleApiSuccessResponse(
//                response: ApiSuccessResponse<BlogListSearchResponse>
//            ) {
//
//                val blogPostList: ArrayList<User> = ArrayList()
//                for (blogPostResponse in response.body.results) {
//                    blogPostList.add(
//                        User(
//                            pk = blogPostResponse.pk,
//                            title = blogPostResponse.title,
//                            slug = blogPostResponse.slug,
//                            body = blogPostResponse.body,
//                            image = blogPostResponse.image,
//                            date_updated = DateUtils.convertServerStringDateToLong(
//                                blogPostResponse.date_updated
//                            ),
//                            username = blogPostResponse.username
//                        )
//                    )
//                }
//                updateLocalDb(blogPostList)
//
//                createCacheRequestAndReturn()
//            }
//
//            override fun createCall(): LiveData<GenericApiResponse<BlogListSearchResponse>> {
//                return openApiMainService.searchListBlogPosts(
//                    "Token ${authToken.token!!}",
//                    query = query,
//                    ordering = filterAndOrder,
//                    page = page
//                )
//            }
//
//            override fun loadFromCache(): LiveData<BlogViewState> {
//                return blogPostDao.returnOrderedBlogQuery(
//                    query = query,
//                    filterAndOrder = filterAndOrder,
//                    page = page
//                )
//                    .switchMap {
//                        object : LiveData<BlogViewState>() {
//                            override fun onActive() {
//                                super.onActive()
//                                value = BlogViewState(
//                                    BlogViewState.BlogFields(
//                                        blogList = it,
//                                        isQueryInProgress = true
//                                    )
//                                )
//                            }
//                        }
//                    }
//            }
//
//            override suspend fun updateLocalDb(cacheObject: List<User>?) {
//                // loop through list and update the local db
//                if (cacheObject != null) {
//                    withContext(IO) {
//                        for (blogPost in cacheObject) {
//                            try {
//                                // Launch each insert as a separate job to be executed in parallel
//                                launch {
//                                    Log.d(TAG, "updateLocalDb: inserting blog: ${blogPost}")
//                                    blogPostDao.insert(blogPost)
//                                }
//                            } catch (e: Exception) {
//                                Log.e(
//                                    TAG,
//                                    "updateLocalDb: error updating cache data on blog post with slug: ${blogPost.slug}. " +
//                                            "${e.message}"
//                                )
//                                // Could send an error report here or something but I don't think you should throw an error to the UI
//                                // Since there could be many blog posts being inserted/updated.
//                            }
//                        }
//                    }
//                } else {
//                    Log.d(TAG, "updateLocalDb: blog post list is null")
//                }
//            }
//
//            override fun setJob(job: Job) {
//                addJob("searchBlogPosts", job)
//            }
//
//        }.asLiveData()
//    }
//
//
//    fun isAuthorOfBlogPost(
//        authToken: AuthToken,
//        slug: String
//    ): LiveData<DataState<BlogViewState>> {
//        return object : NetworkBoundResource<GenericResponse, Any, BlogViewState>(
//            sessionManager.isConnectedToTheInternet(),
//            true,
//            true,
//            false
//        ) {
//
//
//            // not applicable
//            override suspend fun createCacheRequestAndReturn() {
//
//            }
//
//            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<GenericResponse>) {
//                withContext(Dispatchers.Main) {
//
//                    Log.d(TAG, "handleApiSuccessResponse: ${response.body.response}")
//                    if (response.body.response.equals(RESPONSE_NO_PERMISSION_TO_EDIT)) {
//                        onCompleteJob(
//                            DataState.data(
//                                data = BlogViewState(
//                                    viewBlogFields = BlogViewState.ViewBlogFields(
//                                        isAuthorOfBlogPost = false
//                                    )
//                                ),
//                                response = null
//                            )
//                        )
//                    } else if (response.body.response.equals(RESPONSE_HAS_PERMISSION_TO_EDIT)) {
//                        onCompleteJob(
//                            DataState.data(
//                                data = BlogViewState(
//                                    viewBlogFields = BlogViewState.ViewBlogFields(
//                                        isAuthorOfBlogPost = true
//                                    )
//                                ),
//                                response = null
//                            )
//                        )
//                    } else {
//                        onErrorReturn(
//                            ERROR_UNKNOWN,
//                            shouldUseDialog = false,
//                            shouldUseToast = false
//                        )
//                    }
//                }
//            }
//
//            // not applicable
//            override fun loadFromCache(): LiveData<BlogViewState> {
//                return AbsentLiveData.create()
//            }
//
//            // Make an update and change nothing.
//            // If they are not the author it will return: "You don't have permission to edit that."
//            override fun createCall(): LiveData<GenericApiResponse<GenericResponse>> {
//                return openApiMainService.isAuthorOfBlogPost(
//                    "Token ${authToken.token!!}",
//                    slug
//                )
//            }
//
//            // not applicable
//            override suspend fun updateLocalDb(cacheObject: Any?) {
//
//            }
//
//            override fun setJob(job: Job) {
//                addJob("isAuthorOfBlogPost", job)
//            }
//
//
//        }.asLiveData()
//    }
//
//    fun deleteBlogPost(
//        authToken: AuthToken,
//        blogPost: User
//    ): LiveData<DataState<BlogViewState>> {
//        return object : NetworkBoundResource<GenericResponse, User, BlogViewState>(
//            sessionManager.isConnectedToTheInternet(),
//            true,
//            true,
//            false
//        ) {
//
//            // not applicable
//            override suspend fun createCacheRequestAndReturn() {
//
//            }
//
//            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<GenericResponse>) {
//
//                if (response.body.response == SUCCESS_BLOG_DELETED) {
//                    updateLocalDb(blogPost)
//                } else {
//                    onCompleteJob(
//                        DataState.error(
//                            Response(
//                                ERROR_UNKNOWN,
//                                ResponseType.Dialog()
//                            )
//                        )
//                    )
//                }
//            }
//
//            // not applicable
//            override fun loadFromCache(): LiveData<BlogViewState> {
//                return AbsentLiveData.create()
//            }
//
//            override fun createCall(): LiveData<GenericApiResponse<GenericResponse>> {
//                return openApiMainService.deleteBlogPost(
//                    "Token ${authToken.token!!}",
//                    blogPost.slug
//                )
//            }
//
//            override suspend fun updateLocalDb(cacheObject: User?) {
//                cacheObject?.let { blogPost ->
//                    blogPostDao.deleteBlogPost(blogPost)
//                    onCompleteJob(
//                        DataState.data(
//                            null,
//                            Response(SUCCESS_BLOG_DELETED, ResponseType.Toast())
//                        )
//                    )
//                }
//            }
//
//            override fun setJob(job: Job) {
//                addJob("deleteBlogPost", job)
//            }
//
//        }.asLiveData()
//    }
//
//    fun updateBlogPost(
//        authToken: AuthToken,
//        slug: String,
//        title: RequestBody,
//        body: RequestBody,
//        image: MultipartBody.Part?
//    ): LiveData<DataState<BlogViewState>> {
//        return object : NetworkBoundResource<BlogCreateUpdateResponse, User, BlogViewState>(
//            sessionManager.isConnectedToTheInternet(),
//            true,
//            true,
//            false
//        ) {
//
//            // not applicable
//            override suspend fun createCacheRequestAndReturn() {
//
//            }
//
//            override suspend fun handleApiSuccessResponse(
//                response: ApiSuccessResponse<BlogCreateUpdateResponse>
//            ) {
//
//                val updatedBlogPost = User(
//                    response.body.pk,
//                    response.body.title,
//                    response.body.slug,
//                    response.body.body,
//                    response.body.image,
//                    DateUtils.convertServerStringDateToLong(response.body.date_updated),
//                    response.body.username
//                )
//
//                updateLocalDb(updatedBlogPost)
//
//                withContext(Dispatchers.Main) {
//                    // finish with success response
//                    onCompleteJob(
//                        DataState.data(
//                            BlogViewState(
//                                viewBlogFields = BlogViewState.ViewBlogFields(
//                                    blogPost = updatedBlogPost
//                                )
//                            ),
//                            Response(response.body.response, ResponseType.Toast())
//                        )
//                    )
//                }
//            }
//
//            // not applicable
//            override fun loadFromCache(): LiveData<BlogViewState> {
//                return AbsentLiveData.create()
//            }
//
//            override fun createCall(): LiveData<GenericApiResponse<BlogCreateUpdateResponse>> {
//                return openApiMainService.updateBlog(
//                    "Token ${authToken.token!!}",
//                    slug,
//                    title,
//                    body,
//                    image
//                )
//            }
//
//            override suspend fun updateLocalDb(cacheObject: User?) {
//                cacheObject?.let { blogPost ->
//                    blogPostDao.updateBlogPost(
//                        blogPost.pk,
//                        blogPost.title,
//                        blogPost.body,
//                        blogPost.image
//                    )
//                }
//            }
//
//            override fun setJob(job: Job) {
//                addJob("updateBlogPost", job)
//            }
//
//        }.asLiveData()
//    }

}
