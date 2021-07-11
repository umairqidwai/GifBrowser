package com.mobimeo.gifbrowser.domain.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobimeo.gifbrowser.TestCoroutineRule
import com.mobimeo.gifbrowser.domain.interactors.GifSearchUseCase
import com.mobimeo.gifbrowser.domain.model.GifSearch
import com.mobimeo.gifbrowser.domain.model.Meta
import com.mobimeo.gifbrowser.domain.model.Pagination
import com.mobimeo.gifbrowser.domain.repo.GiphyRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GifSearchUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var giphyRepository: GiphyRepository

    lateinit var gifSearchUseCase: GifSearchUseCase


    @Before
    fun setUp() {
        gifSearchUseCase = GifSearchUseCase(giphyRepository)
    }


    @Test
    fun run_test_pass() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(giphyRepository.search("", "", 0))
                .thenReturn(
                    Response.success(
                        GifSearch(
                            arrayListOf(),
                            Meta("", "", 200),
                            Pagination(0, 0, 0)
                        )
                    )
                )

            gifSearchUseCase.run(GifSearchUseCase.Params("", "", 0))
            Mockito.verify(giphyRepository).search("", "", 0)
            Mockito.verifyNoMoreInteractions(giphyRepository)
        }


    }

    @After
    fun tearDown() {
    }
}