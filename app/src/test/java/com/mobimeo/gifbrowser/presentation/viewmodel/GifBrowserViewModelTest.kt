package com.mobimeo.gifbrowser.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mobimeo.gifbrowser.TestCoroutineRule
import com.mobimeo.gifbrowser.domain.interactors.GifSearchUseCase
import com.mobimeo.gifbrowser.domain.model.GifSearch
import com.mobimeo.gifbrowser.domain.model.Meta
import com.mobimeo.gifbrowser.domain.model.Pagination
import com.mobimeo.gifbrowser.domain.repo.GiphyRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GifBrowserViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    lateinit var gifBrowserViewModel: GifBrowserViewModel

    @Mock
    lateinit var gifSearchUseCase: GifSearchUseCase

    @Mock
    lateinit var giphyRepository: GiphyRepository


    @Mock
    private lateinit var gifListObserver: Observer<GifSearch>

    @Before
    fun setUp() {
        gifBrowserViewModel = GifBrowserViewModel(gifSearchUseCase)
    }


    @Test
    fun test_searchGif() {
        runBlockingTest {
            Mockito.doReturn(
                Response.success(
                    GifSearch(
                        arrayListOf(),
                        Meta("", "", 200),
                        Pagination(0, 0, 0)
                    )
                )
            ).`when`(giphyRepository.search("", "", 0))
            Mockito.doReturn(
                GifSearchUseCase.Result(
                    GifSearch(
                        arrayListOf(),
                        Meta("", "", 200),
                        Pagination(0, 0, 0)
                    ), true, ""
                )
            ).`when`(gifSearchUseCase.run(GifSearchUseCase.Params("", "", 0)))

            gifBrowserViewModel.gifList.observeForever(gifListObserver)
            verify(gifBrowserViewModel).searchGif(Mockito.anyString(), Mockito.anyString())
            verify(gifListObserver).onChanged(
                GifSearch(
                    arrayListOf(),
                    Meta("", "", 200),
                    Pagination(0, 0, 0)
                )
            )
            gifBrowserViewModel.gifList.removeObserver(gifListObserver)

        }
    }

    @After
    fun tearDown() {
    }
}