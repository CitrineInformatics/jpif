package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Information about a referenced publication.
 *
 * @author Kyle Michel
 */
public class Reference extends Pio {

    /**
     * Set the DOI of the published work.
     *
     * @param doi String with DOI of the published work.
     * @return This object.
     */
    public Reference setDoi(final String doi) {
        this.doi = doi;
        return this;
    }

    /**
     * Get the DOI of the published work.
     *
     * @return String with the DOI of the published work.
     */
    public String getDoi() {
        return this.doi;
    }

    /**
     * Set the ISBN of the published work.
     *
     * @param isbn String with the ISBN of the published work.
     * @return This object.
     */
    public Reference setIsbn(final String isbn) {
        this.isbn = isbn;
        return this;
    }

    /**
     * Get the ISBN of the published work.
     *
     * @return String with the ISBN of the published work.
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * Set the ISSN of the published work.
     *
     * @param issn String with the ISSN of the published work.
     * @return This object.
     */
    public Reference setIssn(final String issn) {
        this.issn = issn;
        return this;
    }

    /**
     * Get the ISSN of the published work.
     *
     * @return String with the ISSN of the published work.
     */
    public String getIssn() {
        return this.issn;
    }

    /**
     * Set the URL to the published work.
     *
     * @param url String with the URL to the published work.
     * @return This object.
     */
    public Reference setUrl(final String url) {
        this.url = url;
        return this;
    }

    /**
     * Get the URL to the published work.
     *
     * @return String with the URL to the published work.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Set the title of the published work.
     *
     * @param title String with the title of the published work.
     * @return This object.
     */
    public Reference setTitle(final String title) {
        this.title = title;
        return this;
    }

    /**
     * Get the title of the published work.
     *
     * @return String with the title of the published work.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Set the publisher of the work.
     *
     * @param publisher String with the publisher of the work.
     * @return This object.
     */
    public Reference setPublisher(final String publisher) {
        this.publisher = publisher;
        return this;
    }

    /**
     * Get the publisher of the work.
     *
     * @return String with the publisher of the work.
     */
    public String getPublisher() {
        return this.publisher;
    }

    /**
     * Set the journal in which the work was published.
     *
     * @param journal String with the journal in which the work was published.
     * @return This object.
     */
    public Reference setJournal(final String journal) {
        this.journal = journal;
        return this;
    }

    /**
     * Get the journal in which the work was published.
     *
     * @return String with the journal in which the work was published.
     */
    public String getJournal() {
        return this.journal;
    }

    /**
     * Set the volume in which the work was published.
     *
     * @param volume String with the volume in which the work was published.
     * @return This object.
     */
    public Reference setVolume(final String volume) {
        this.volume = volume;
        return this;
    }

    /**
     * Get the volume in which the work was published.
     *
     * @return String with the volume in which the work was published.
     */
    public String getVolume() {
        return this.volume;
    }

    /**
     * Set the issue in which the work was published.
     *
     * @param issue String with the issue in which the work was published.
     * @return This object.
     */
    public Reference setIssue(final String issue) {
        this.issue = issue;
        return this;
    }

    /**
     * Get the issue in which the work was published.
     *
     * @return String with the issue in which the work was published.
     */
    public String getIssue() {
        return this.issue;
    }

    /**
     * Set the year in which the work was published.
     *
     * @param year String with the year in which the work was published.
     * @return This object.
     */
    public Reference setYear(final String year) {
        this.year = year;
        return this;
    }

    /**
     * Get the year in which the work was published.
     *
     * @return String with the year in which the work was published.
     */
    public String getYear() {
        return this.year;
    }

    /**
     * Set the starting and ending pages for the published work.
     *
     * @param pages {@link Pages} object with the starting and ending pages for the published work.
     * @return This object.
     */
    public Reference setPages(final Pages pages) {
        this.pages = pages;
        return this;
    }

    /**
     * Get the starting and ending pages for the published work.
     *
     * @return {@link Pages} object with the starting and ending pages for the published work.
     */
    public Pages getPages() {
        return this.pages;
    }

    /**
     * Set the list of authors.
     *
     * @param authors List of {@link Name} objects with the authors.
     */
    @JsonSetter
    private void setAuthors(final List<Name> authors) { // Private since only Jackson should use it
        this.authors = authors;
    }

    /**
     * Add an author.
     *
     * @param author {@link Name} object for the author to add.
     * @return This object.
     */
    public Reference addAuthor(final Name author) {
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
        return this;
    }

    /**
     * Get the number of authors.
     *
     * @return Number of authors.
     */
    public int numAuthors() {
        return (this.authors == null) ? 0 : this.authors.size();
    }

    /**
     * Get an author at a set index.
     *
     * @param index Index of the author to return.
     * @return {@link Name} object for the author at the set index.
     * @throws IndexOutOfBoundsException if the index is out of range of the list of authors.
     */
    @JsonIgnore
    public Name getAuthor(final int index) {
        if (this.authors == null) {
            throw new IndexOutOfBoundsException("Attempting to access author " + index + " of " + this.numAuthors());
        }
        return this.authors.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over the authors.
     *
     * @return {@link Iterable} object for iterating over authors.
     */
    public Iterable<Name> authors() {
        return (this.authors == null) ? Collections.emptyList() : this.authors;
    }

    /**
     * Get the list of authors.
     *
     * @return List of {@link Name} objects with the authors.
     */
    @JsonGetter
    private List<Name> getAuthors() { // Private since only Jackson should use it
        return this.authors;
    }

    /**
     * Set the list of editors.
     *
     * @param editors List of {@link Name} objects with the editors.
     */
    @JsonSetter
    private void setEditors(final List<Name> editors) { // Private since only Jackson should use it
        this.editors = editors;
    }

    /**
     * Add an editor.
     *
     * @param editor {@link Name} object for the editor to add.
     * @return This object.
     */
    public Reference addEditor(final Name editor) {
        if (this.editors == null) {
            this.editors = new ArrayList<>();
        }
        this.editors.add(editor);
        return this;
    }

    /**
     * Get the number of editors.
     *
     * @return Number of editors.
     */
    @JsonIgnore
    public int numEditors() {
        return (this.editors == null) ? 0 : this.editors.size();
    }

    /**
     * Get a reference at a set index.
     *
     * @param index Index of the reference to get.
     * @return {@link Reference} object for the reference at the input index.
     * @throws IndexOutOfBoundsException if the index is out of range of the reference list.
     */
    @JsonIgnore
    public Name getEditor(final int index) {
        if (this.editors == null) {
            throw new IndexOutOfBoundsException("Attempting to access editor " + index + " of " + this.numEditors());
        }
        return this.editors.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over editors.
     *
     * @return {@link Iterable} object for iterating over editors.
     */
    public Iterable<Name> editors() {
        return (this.editors == null) ? Collections.emptyList() : this.editors;
    }

    /**
     * Get the list of editors.
     *
     * @return List of {@link Name} objects with the editors.
     */
    @JsonGetter
    private List<Name> getEditors() { // Private since only Jackson should use it
        return this.editors;
    }

    /**
     * Set the list of references cited by this work.
     *
     * @param references List of {@link Reference} objects for the references cited by this work.
     */
    @JsonSetter
    private void setReferences(final List<Reference> references) { // Private since only Jackson should use it
        this.references = references;
    }

    /**
     * Add a reference cited by this work.
     *
     * @param reference {@link Reference} object for a reference cited by this work.
     * @return This object.
     */
    public Reference addReference(final Reference reference) {
        if (this.references == null) {
            this.references = new ArrayList<>();
        }
        this.references.add(reference);
        return this;
    }

    /**
     * Get the number of references cited by this work.
     *
     * @return Number of references cited by this work.
     */
    @JsonIgnore
    public int getNumReferences() {
        return (this.references == null) ? 0 : this.references.size();
    }

    /**
     * Get a reference at a set index.
     *
     * @param index Index of the reference to get.
     * @return {@link Reference} object at the input index.
     * @throws IndexOutOfBoundsException if the index is out of range of the reference list.
     */
    @JsonIgnore
    public Reference getReference(final int index) {
        if (this.references == null) {
            throw new IndexOutOfBoundsException("Attempting to access reference " + index + " of "
                    + this.getNumReferences());
        }
        return this.references.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over references cited by this work.
     *
     * @return {@link Iterable} object for iterating over references cited by this work.
     */
    public Iterable<Reference> references() {
        return (this.references == null) ? Collections.emptyList() : this.references;
    }

    /**
     * Get the list of references cited by this work.
     *
     * @return List of {@link Reference} objects for references cited by this work.
     */
    @JsonGetter
    private List<Reference> getReferences() { // Private since only Jackson should use it
        return this.references;
    }

    /** DOI of the published work. */
    private String doi;

    /** ISBN of the published work. */
    private String isbn;

    /** ISSN of the published work. */
    private String issn;

    /** URL to the published work. */
    private String url;

    /** Title of the published work. */
    private String title;

    /** Publisher of the work. */
    private String publisher;

    /** Journal in which the work was published. */
    private String journal;

    /** Volume in which the work was published. */
    private String volume;

    /** Issue in which the work was published. */
    private String issue;

    /** Year in which the work was published. */
    private String year;

    /** Starting and ending pages for the published work. */
    private Pages pages;

    /** List of authors. */
    private List<Name> authors;

    /** List of editors. */
    private List<Name> editors;

    /** List of works cited by this reference. */
    private List<Reference> references;
}