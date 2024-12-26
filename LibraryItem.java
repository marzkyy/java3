// Generic LibraryItem class
class LibraryItem {
    private String title;
    private String author;
    private String itemID;

    public LibraryItem(String title, String author, String itemID) {
        this.title = title;
        this.author = author;
        this.itemID = itemID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getItemID() {
        return itemID;
    }

    @Override
    public String toString() {
        return "ItemID: " + itemID + ", Title: " + title + ", Author: " + author;
    }
}