package io.citrine.jpif.stats.common;

import java.util.List;

/**
 * Class used to store statistics about a PIF property.
 *
 * @author Kyle Michel
 */
public class PropertyStatsWrapper extends StatsWrapper {

    /**
     * Set the list of properties and their counts.
     *
     * @param commonProperties List of {@link PropertyStats} objects.
     */
    public void setCommonProperties(final List<PropertyStats> commonProperties) {
        this.commonProperties = commonProperties;
    }

    /**
     * Get the list of properties and their counts.
     *
     * @return List of {@link PropertyStats} objects.
     */
    public List<PropertyStats> getCommonProperties() {
        return this.commonProperties;
    }

    /** List of properties and their counts. */
    private List<PropertyStats> commonProperties;

    /**
     * Class to store statistics about a single property and its count.
     *
     * @author Kyle Michel
     */
    public static class PropertyStats extends Stats {

        /**
         * Set the list of common names.
         *
         * @param commonNames List of {@link FieldStats} objects.
         */
        public void setCommonNames(final List<FieldStats> commonNames) {
            this.commonNames = commonNames;
        }

        /**
         * Get the list of common names.
         *
         * @return List of {@link FieldStats} objects.
         */
        public List<FieldStats> getCommonNames() {
            return this.commonNames;
        }

        /**
         * Set the list of common values.
         *
         * @param commonValues List of {@link FieldStats} objects.
         */
        public void setCommonValues(final List<FieldStats> commonValues) {
            this.commonValues = commonValues;
        }

        /**
         * Get the list of common values.
         *
         * @return List of {@link FieldStats} objects.
         */
        public List<FieldStats> getCommonValues() {
            return this.commonValues;
        }

        /**
         * Set the list of common units.
         *
         * @param commonUnits List of {@link FieldStats} objects.
         */
        public void setCommonUnits(final List<FieldStats> commonUnits) {
            this.commonUnits = commonUnits;
        }

        /**
         * Get the list of common units.
         *
         * @return List of {@link FieldStats} objects.
         */
        public List<FieldStats> getCommonUnits() {
            return this.commonUnits;
        }

        /** Stats for names of properties. */
        private List<FieldStats> commonNames;

        /** Stats for the values of properties. */
        private List<FieldStats> commonValues;

        /** Statis for the units of the properties. */
        private List<FieldStats> commonUnits;
    }
}