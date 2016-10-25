package io.citrine.jpif.stats.system;

import io.citrine.jpif.stats.common.FieldStats;
import io.citrine.jpif.stats.common.PropertyStatsWrapper;
import io.citrine.jpif.stats.common.StatsWrapper;

import java.util.List;

/**
 * Class used to store statistics about a PIF system.
 *
 * @author Kyle Michel
 */
public class SystemStatsWrapper extends StatsWrapper {

    /**
     * Set the system stats.
     *
     * @param common List of {@link SystemStats} objects.
     */
    public void setCommon(final List<SystemStats> common) {
        this.common = common;
    }

    /**
     * Get the system stats.
     *
     * @return List of {@link SystemStats} objects.
     */
    public List<SystemStats> getCommon() {
        return this.common;
    }

    /** System stats. */
    private List<SystemStats> common;

    /**
     * Class to store statistics about a single system.
     *
     * @author Kyle Michel
     */
    public static class SystemStats extends Stats {

        /**
         * Set the common names.
         *
         * @param names List of {@link FieldStats} objects.
         */
        public void setNames(final FieldStats names) {
            this.names = names;
        }

        /**
         * Get the common names.
         *
         * @return List of {@link FieldStats} objects.
         */
        public FieldStats getNames() {
            return this.names;
        }

        /**
         * Set the common chemical formulas.
         *
         * @param chemicalFormula List of {@link FieldStats} objects.
         */
        public void setChemicalFormula(final FieldStats chemicalFormula) {
            this.chemicalFormula = chemicalFormula;
        }

        /**
         * Get the common chemical formulas.
         *
         * @return List of {@link FieldStats} objects.
         */
        public FieldStats getChemicalFormula() {
            return this.chemicalFormula;
        }

        /**
         * Set the common properties.
         *
         * @param properties {@link PropertyStatsWrapper} object.
         */
        public void setProperties(final PropertyStatsWrapper properties) {
            this.properties = properties;
        }

        /**
         * Get the common properties.
         *
         * @return {@link PropertyStatsWrapper} object.
         */
        public PropertyStatsWrapper getProperties() {
            return this.properties;
        }

        /** Stats for names. */
        private FieldStats names;

        /** Stats for chemical formulas. */
        private FieldStats chemicalFormula;

        /** Stats for properties. */
        private PropertyStatsWrapper properties;
    }
}