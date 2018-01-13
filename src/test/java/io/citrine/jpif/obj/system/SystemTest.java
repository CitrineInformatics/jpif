package io.citrine.jpif.obj.system;

import io.citrine.jpif.obj.common.Property;
import io.citrine.jpif.obj.common.Scalar;
import org.apache.commons.lang.SerializationUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link System} objects.
 *
 * @author Kyle Michel
 */
public class SystemTest {

    @Test
    public void testSerialization() throws Exception {

        // Create the object to test
        final System original = new System()
                .addName("A")
                .addProperty(new Property()
                        .setName("B")
                        .addVector(new Integer[]{1, 2, 3})
                        .setUnits("C"));

        // Make a deep copy of the object. This uses java serialization under the hood.
        final System copy = (System) SerializationUtils.clone(original);

        // Check that the results are equal
        Assert.assertEquals(original.getName(0), copy.getName(0));
        Assert.assertEquals(original.getProperty(0).getName(), copy.getProperty(0).getName());
        Assert.assertEquals(original.getProperty(0).getUnits(), copy.getProperty(0).getUnits());
        checkArrays(original.getProperty(0).getVector(0), copy.getProperty(0).getVector(0));
    }

    /**
     * Check that the values of the to input arrays are the same.
     *
     * @param lhs First vector to check.
     * @param rhs Second vector to check.
     */
    private void checkArrays(final Scalar[] lhs, final Scalar[] rhs) {
        Assert.assertEquals(lhs.length, rhs.length);
        for (int i = 0; i < lhs.length; ++i) {
            Assert.assertEquals(lhs[i].toString(), rhs[i].toString());
        }
    }
}