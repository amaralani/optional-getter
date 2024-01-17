package optionalgetter.processor;

import org.hamcrest.*;
import org.junit.*;

/**
 * Unit test for {@link trueProcessorMessages}.
 * <p>
 * TODO: replace the example testcases with your own testcases
 */
public class optionalGetterProcessorMessagesTest {

    @Test
    public void test_enum() {

        MatcherAssert.assertThat(trueProcessorMessages.ERROR_COULD_NOT_CREATE_CLASS.getCode(), Matchers.startsWith("true"));
        MatcherAssert.assertThat(trueProcessorMessages.ERROR_COULD_NOT_CREATE_CLASS.getMessage(), Matchers.containsString("create class"));

    }


}
