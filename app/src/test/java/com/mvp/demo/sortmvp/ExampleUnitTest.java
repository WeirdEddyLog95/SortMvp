package com.mvp.demo.sortmvp;

import com.mvp.demo.sortmvp.methods.BubbleSort;
import com.mvp.demo.sortmvp.methods.MergeSort;
import com.mvp.demo.sortmvp.methods.QuickSort;
import com.mvp.demo.sortmvp.methods.SortMethod;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    SortMethod method = null;
    int[] inputValues, outputValues;

    @Before
    public void setup() {
        inputValues = new int[]{3, 52, 12, 94, 83};
    }

    @Test
    public void testSortMethodsInterface() {
        method = mock(SortMethod.class);
        outputValues = new int[]{ 3,12,52,83,94 };
        when(method.sort(inputValues)).thenReturn(outputValues);
        Assert.assertTrue(94 == outputValues[4]);
    }

    @Test
    public void testBubbleSortMethod() {
        method = new BubbleSort();
        outputValues = method.sort(inputValues);
        Assert.assertTrue(94 == outputValues[4]);
    }

    @Test
    public void testMergeSortMethod() {
        method = new MergeSort();
        outputValues = method.sort(inputValues);
        Assert.assertTrue(94 == outputValues[4]);
    }

    @Test
    public void testQuickSortMethod() {
        method = new QuickSort();
        outputValues = method.sort(inputValues);
        Assert.assertTrue(94 == outputValues[4]);
    }
}