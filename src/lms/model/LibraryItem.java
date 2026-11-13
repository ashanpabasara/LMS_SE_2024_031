package lms.model;

import lms.service.Library;

public class LibraryItem {
    private String id;
    private String title;
    private boolean isBorrowed;

    LibraryItem(String id, String title) {
        setTitle(title);
        setId(id);
        this.isBorrowed = false;
    }

    public String getId() {
        return id;
    }
    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cant blank:");
        }
        this.title = title;
    }

    public void setId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cant blank:");
        }
        this.id = id;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.isBorrowed = borrowed;
    }

    public double calculateLateFee(int daysLate) {
        return daysLate * 10.0;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{id='" + id + "', title='" + title +
                "', borrowed=" + isBorrowed + "}";
    }
}





