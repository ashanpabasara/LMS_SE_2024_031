package lms.service;

import lms.model.Book;
import lms.model.DVD;
import lms.model.LibraryItem;
import lms.model.Loanable;
import lms.model.Member;
import lms.model.Reservable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {

    private final List<LibraryItem> items = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();


    public void addItem(LibraryItem item) {
        items.add(item);
        System.out.println("Added via addItem(LibraryItem): " + item.getTitle());
    }

    public void addItem(Book book) {
        items.add(book);
        System.out.println("Added via addItem(Book): " + book.getTitle());
    }

    public void addItem(DVD dvd) {
        items.add(dvd);
        System.out.println("Added via addItem(DVD): " + dvd.getTitle());
    }

    public void addMember(Member member) { members.add(member); }

    public void printAllLateFees(int daysLate) {
        System.out.println("--- Late fees if " + daysLate + " day(s) late ---");
        for (LibraryItem item : items) {
            System.out.println(item.getTitle() + ": " + item.calculateLateFee(daysLate));
        }
    }

    public void lend(Loanable thing, Member member) {
        thing.loan(member);
    }

    public void reserveIfPossible(LibraryItem item, Member member) {
        if (item instanceof Reservable r) {
            r.reserve(member);
        } else {
            System.out.println(item.getTitle() + " cannot be reserved.");
        }
    }

    public void printMembersByActivity() {
        List<Member> sorted = new ArrayList<>(members);
        Collections.sort(sorted);
        System.out.println("--- Members ranked by activity ---");
        for (Member m : sorted) {
            System.out.println(m.getName() + " (" + m.getBorrowedCount() + " borrowed)");
        }
    }

    public void listItems() {
        System.out.println("--- Items in the library ---");
        for (LibraryItem item : items) System.out.println(item);
    }

    public void listMembers() {
        System.out.println("--- Registered members ---");
        for (Member m : members) System.out.println(m);
    }

    public List<LibraryItem> getItems() { return items; }
    public List<Member> getMembers() { return members; }

}