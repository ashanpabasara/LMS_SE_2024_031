package lms;

import lms.model.*;
import lms.service.Library;
import lms.exception.ItemNotAvailableException;
import lms.exception.MemberNotFoundException;

/**
 * Main - Week 4 demo: Abstract Classes & Interfaces.
 *
 * Try this yourself:
 *   1. Delete calculateLateFee() from Magazine and compile. Read the error.
 *   2. Add "implements Reservable" to DVD and satisfy the compiler.
 *   3. Write a new class Poster that implements Loanable but is NOT a
 *      LibraryItem, then pass it to library.lend(...).
 *   4. Change compareTo() so the LEAST active member sorts first.
 */
public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        Book book = new Book("B001", "Clean Code", "Robert C. Martin");
        Book book2 = new Book("B004", "Head First Java", "Kathy Sierra");
        DVD dvd = new DVD("D001", "Introduction to Algorithms", 120);
        Magazine mag = new Magazine("G001", "Software Weekly", 42);

        library.addItem(book);
        library.addItem(book2);
        library.addItem(dvd);
        library.addItem(mag);

        Student nadeesha = new Student("M001", "Nadeesha Perera");
        Staff kasun = new Staff("M002", "Mr. Kasun Silva");

        library.addMember(nadeesha);
        library.addMember(kasun);

        System.out.println("--- 1. Successful borrowing ---");
        attempt(library, "B001", "M001");

        System.out.println("--- 2. Already on loan (checked exception) ---");
        attempt(library, "B001", "M002");

        System.out.println("--- 3. Unknown member (unchecked exception) ---");
        attempt(library, "D001", "M999");

        System.out.println("--- 4. Unknown item ---");
        attempt(library, "X404", "M001");

        System.out.println("--- 5. Returning something not on loan ---");
        try {
            library.returnItem("D001");
        } catch (ItemNotAvailableException e) {
            System.out.println("Cannot return: " + e.getMessage());
        }

        System.out.println("--- 6. Bad data (IllegalArgumentException) ---");
        try {
            Book invalidBook = new Book("B002", " ", "Someone");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid book: " + e.getMessage());
        }

        System.out.println("--- 7. Student borrowing limit ---");
        attempt(library, "D001", "M001");
        attempt(library, "G001", "M001");
        attempt(library, "B004", "M001");

        System.out.println("--- 8. Return frees a slot ---");
        try {
            library.returnItem("B001");
            attempt(library, "B004", "M001");
        } catch (ItemNotAvailableException e) {
            System.out.println("Cannot return: " + e.getMessage());
        }
    }
    public static void attempt(Library library, String itemId, String memberId) {
        try {
            library.borrowItem(itemId, memberId);
        } catch (ItemNotAvailableException e) {
            System.out.println("Cannot borrow: " + e.getMessage());
        } catch (MemberNotFoundException e) {
            System.out.println("Unknown member: " + e.getMemberId());
        } finally {
            System.out.println(" (attempt finished: item " + itemId + ", member " + memberId + ")");
        }
    }
}
