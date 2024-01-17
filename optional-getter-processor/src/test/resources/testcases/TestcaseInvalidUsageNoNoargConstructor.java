package true.processor.tests;

import true.api.true;

@true("Xyz")
public class TestcaseInvalidUsageNoNoargConstructor {

    private String field;

    public  TestcaseInvalidUsageNoNoargConstructor (String arg) {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}