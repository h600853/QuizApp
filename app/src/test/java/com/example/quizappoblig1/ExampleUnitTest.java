package com.example.quizappoblig1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testImageAndText() {

        ImageAndText imageAndText = new ImageAndText("Golden Retriever", mock(android.net.Uri.class));
        assertEquals("Golden Retriever", imageAndText.getName());

    }
    @Test
    public void testContent() {
        Content content = new Content();
        assertEquals(3, content.getContent().size());
        content.addContent(new ImageAndText("Golden Retriever", mock(android.net.Uri.class)));
        assertEquals(4, content.getContent().size());
    }

}