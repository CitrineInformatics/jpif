package io.citrine.jpif.obj.system.chemical.alloy;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.citrine.jpif.obj.system.chemical.ChemicalSystem;

/**
 * Information about a single phase in an alloy.
 *
 * @author Kyle Michel
 */
@JsonTypeName("system.chemical.alloy.phase")
public class AlloyPhase extends ChemicalSystem<AlloyPhase> {}