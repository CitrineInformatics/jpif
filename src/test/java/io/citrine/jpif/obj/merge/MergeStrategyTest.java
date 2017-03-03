package io.citrine.jpif.obj.merge;

import static org.junit.Assert.assertEquals;

import io.citrine.jpif.obj.system.chemical.ChemicalSystem;
import io.citrine.jpif.util.PifObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Test Pio merging strategies.
 *
 * @author Sean Paradiso
 */
public class MergeStrategyTest {

    @Test
    public void replaceNonNullAndAppendTest() throws Exception {
        try (final InputStream streamA = getClass().getResourceAsStream("/pifA.json");
             final InputStream streamB = getClass().getResourceAsStream("/pifB.json")) {

            ChemicalSystem pifA = PifObjectMapper.getInstance().readerFor(ChemicalSystem.class).readValue(streamA);
            ChemicalSystem pifB = PifObjectMapper.getInstance().readerFor(ChemicalSystem.class).readValue(streamB);
            ChemicalSystem merged = (ChemicalSystem) pifA.merge(pifB, MergeStrategy.REPLACE_NON_NULL_AND_APPEND);

            // Make sure pifB properties are appended
            assertEquals(2, merged.numProperties());
            assertEquals("pifBUID", merged.getUid());
            assertEquals(2, merged.numReferences());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}