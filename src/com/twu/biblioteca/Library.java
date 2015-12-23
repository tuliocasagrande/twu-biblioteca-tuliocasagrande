package com.twu.biblioteca;

public class Library {

    private Book[] books;

    public Library(Book[] books) {
        this.books = books;
    }

    public Book[] getBooks() {
        return books;
    }

    // LINEAR SEARCH!!!
    // If books[] is getting big, consider change it to a Hash
    private Book searchBook(int book_id) {
        for (Book b : books) {
            if (b.getId() == book_id) {
                return b;
            }
        }
        return null;
    }

    public boolean checkoutBook(int book_id) {
        Book book = searchBook(book_id);
        if (book != null && book.getStatus() == Book.Status.AVAILABLE) {
            book.setStatus(Book.Status.BORROWED);
            return true;
        }
        return false;
    }

    public boolean returnBook(int book_id) {
        Book book = searchBook(book_id);
        if (book != null && book.getStatus() == Book.Status.BORROWED) {
            book.setStatus(Book.Status.AVAILABLE);
            return true;
        }
        return false;
    }
}
