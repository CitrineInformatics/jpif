package io.citrine.jpif.stats.common;

/**
 * Base class for all stats objects.
 *
 * @author Kyle Michel
 */
public abstract class StatsWrapper {

    /**
     * Set the total field count.
     *
     * @param totalCount Total number of terms that have been encountered.
     */
    void setTotalCount(final long totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * Get the total number of terms that have been encountered.
     *
     * @return Total count.
     */
    public long getTotalCount() {
        return this.totalCount;
    }

    /** Total number of non-null and non-empty terms that were found. */
    private long totalCount;

    /**
     * Base class for objects that store statistics about a field and its count.
     *
     * @author Kyle Michel
     */
    public abstract static class Stats {

        /**
         * Set the count for the term.
         *
         * @param count Count for the term.
         */
        public void setCount(final long count) {
            this.count = count;
        }

        /**
         * Get the count for the term.
         *
         * @return Count for the term.
         */
        public long getCount() {
            return this.count;
        }

        /** Count for the term. */
        private long count;
    }
}