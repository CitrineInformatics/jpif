package io.citrine.jpif.stats.common;

import java.util.List;

/**
 * Class used to store statistics about a PIF property.
 *
 * @author Kyle Michel
 */
public class PropertyStatsWrapper extends StatsWrapper {

    /**
     * Set the properties stats
     *
     * @param common {@link PropertyStats} objects.
     */
    public void setCommon(final List<PropertyStats> common) {
        this.common = common;
    }

    /**
     * Get the list of properties and their counts.
     *
     * @return List of {@link PropertyStats} objects.
     */
    public List<PropertyStats> getCommon() {
        return this.common;
    }

    /** List of properties and their counts. */
    private List<PropertyStats> common;

    /**
     * Class to store statistics about a single property and its count.
     *
     * @author Kyle Michel
     */
    public static class PropertyStats extends Stats {

        /**
         * Set the common names.
         *
         * @param name {@link FieldStats} object.
         */
        public void setName(final FieldStats name) {
            this.name = name;
        }

        /**
         * Get the common names.
         *
         * @return {@link FieldStats} object.
         */
        public FieldStats getName() {
            return this.name;
        }

        /**
         * Set the common values.
         *
         * @param value {@link FieldStats} object.
         */
        public void setValue(final FieldStats value) {
            this.value = value;
        }

        /**
         * Get the common values.
         *
         * @return {@link FieldStats} object.
         */
        public FieldStats getValue() {
            return this.value;
        }

        /**
         * Set the common units.
         *
         * @param commonUnits {@link FieldStats} object.
         */
        public void setUnits(final FieldStats commonUnits) {
            this.units = commonUnits;
        }

        /**
         * Get the common units.
         *
         * @return {@link FieldStats} object.
         */
        public FieldStats getUnits() {
            return this.units;
        }

        /** Stats for names of properties. */
        private FieldStats name;

        /** Stats for the values of properties. */
        private FieldStats value;

        /** Stats for the units of the properties. */
        private FieldStats units;
    }
}