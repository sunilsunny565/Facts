package com.assignment.facts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.assignment.facts.datamanager.AppDataManager
import com.assignment.facts.model.Facts
import com.assignment.facts.ui.main.MainActivityViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FactsUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initMocks() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun test_returningCorrectAppBarTitle() {
        val viewModel = getViewModel()

        //when
        val testTitle = "TestTitle"
        viewModel.title = testTitle

        //then
        assertEquals(viewModel.getAppBarTitle(), testTitle)
    }

    @Test
    fun test_returningCorrectNoOfObject() {
        val factItems: MutableLiveData<MutableList<Facts>> = generateFactItems()
        val viewModel = getViewModel()

        // When
        viewModel.factsList = generateFactItems()

        // then
        assertEquals(
            Objects.requireNonNull(viewModel.getFacts().value)?.size,
            factItems.value?.size
        )
    }

    //Method for generating dummy values
    private fun generateFactItems(): MutableLiveData<MutableList<Facts>> {
        val facts: MutableLiveData<MutableList<Facts>> = MutableLiveData()
        for (i in 0..9) {
            val fact = Facts(i)
            val factList = mutableListOf<Facts>()
            if (i != 5) {
                fact.itemTitle = "itemTitle$i"
                fact.itemDescription = "description$i"
                fact.imageUrl = "image$i"
                factList.add(fact)
            }
            facts.postValue(factList)
        }
        return facts
    }

    //Method for getting the viewModel
    private fun getViewModel(): MainActivityViewModel =
        MainActivityViewModel(mock(AppDataManager::class.java))
}