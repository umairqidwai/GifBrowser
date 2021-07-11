package com.mobimeo.gifbrowser.data.remote.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobimeo.gifbrowser.TestCoroutineRule
import com.mobimeo.gifbrowser.domain.repo.GiphyRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GiphyRepositoryTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    lateinit var giphyRepository: GiphyRepository


    @Before
    fun setUp() {
    }

    @Test
    fun test_search() {
        testCoroutineRule.runBlockingTest {

        }
    }

    @After
    fun tearDown() {

    }


}