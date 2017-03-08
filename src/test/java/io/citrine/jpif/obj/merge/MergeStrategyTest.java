package io.citrine.jpif.obj.merge;

import static org.junit.Assert.assertEquals;

import io.citrine.jpif.obj.system.System;
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

            System pifA = PifObjectMapper.getInstance().readerFor(System.class).readValue(streamA);
            ChemicalSystem pifB = PifObjectMapper.getInstance().readerFor(ChemicalSystem.class).readValue(streamB);
            System merged = (System) pifA.merge(pifB, MergeStrategy.REPLACE_NON_NULL_AND_APPEND);

            // Make sure pifB properties are appended
            assertEquals(2, merged.numProperties());
            assertEquals("pifBUID", merged.getUid());
            assertEquals(2, merged.numReferences());
            assertEquals("B", merged.getUnsupportedFieldValue("unsupportedA"));
            assertEquals("B", merged.getUnsupportedFieldValue("unsupportedB"));
            assertEquals("A", merged.getUnsupportedFieldValue("unsupportedAOnly"));
            assertEquals("HgS", merged.getUnsupportedFieldValue("chemicalFormula"));
            assertEquals(null, merged.getUnsupportedFieldValue("composition"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}