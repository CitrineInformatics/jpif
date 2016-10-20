package io.citrine.jpif.stats.system;

import io.citrine.jpif.stats.common.FieldStatsWrapper;
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
     * Set the list of common systems.
     *
     * @param commonSystems List of {@link SystemStats} objects.
     */
    public void setCommonSystems(final List<SystemStats> commonSystems) {
        this.commonSystems = commonSystems;
    }

    /**
     * Get the list of common systems.
     *
     * @return List of {@link SystemStats} objects.
     */
    public List<SystemStats> getCommonSystems() {
        return this.commonSystems;
    }

    /** List of systems and their counts. */
    private List<SystemStats> commonSystems;

    /**
     * Class to store statistics about a single system.
     *
     * @author Kyle Michel
     */
    public static class SystemStats extends Stats {

        /**
         * Set the common categories.
         *
         * @param commonCategories List of {@link FieldStatsWrapper} objects.
         */
        public void setCommonCategories(final List<FieldStatsWrapper> commonCategories) {
            this.commonCategories = commonCategories;
        }

        /**
         * Get the list of common categories.
         *
         * @return List of {@link FieldStatsWrapper} objects.
         */
        public List<FieldStatsWrapper> getCommonCategories() {
            return this.commonCategories;
        }

        /**
         * Set the common names.
         *
         * @param commonNames List of {@link FieldStatsWrapper} objects.
         */
        public void setCommonNames(final List<FieldStatsWrapper> commonNames) {
            this.commonNames = commonNames;
        }

        /**
         * Get the list of common names.
         *
         * @return List of {@link FieldStatsWrapper} objects.
         */
        public List<FieldStatsWrapper> getCommonNames() {
            return this.commonNames;
        }

        /**
         * Set the common chemical formulas.
         *
         * @param commonChemicalFormulas List of {@link FieldStatsWrapper} objects.
         */
        public void setCommonChemicalFormulas(final List<FieldStatsWrapper> commonChemicalFormulas) {
            this.commonChemicalFormulas = commonChemicalFormulas;
        }

        /**
         * Get the list of common chemical formulas.
         *
         * @return List of {@link FieldStatsWrapper} objects.
         */
        public List<FieldStatsWrapper> getCommonChemicalFormulas() {
            return this.commonChemicalFormulas;
        }

        /**
         * Set the common properties.
         *
         * @param commonProperties List of {@link PropertyStatsWrapper} objects.
         */
        public void setCommonProperties(final List<PropertyStatsWrapper> commonProperties) {
            this.commonProperties = commonProperties;
        }

        /**
         * Get the list of common properties.
         *
         * @return List of {@link PropertyStatsWrapper} objects.
         */
        public List<PropertyStatsWrapper> getCommonProperties() {
            return this.commonProperties;
        }

        /** Stats for categories. */
        private List<FieldStatsWrapper> commonCategories;

        /** Stats for names. */
        private List<FieldStatsWrapper> commonNames;

        /** Stats for chemical formulas. */
        private List<FieldStatsWrapper> commonChemicalFormulas;

        /** Stats for properties. */
        private List<PropertyStatsWrapper> commonProperties;
    }
}