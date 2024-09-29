import java.util.*;

class Book {
    public String id;
    public String title;
}

class Member {
    public String id;
    public String name;
    public ArrayList<Book> borrowedBooks = new ArrayList<>();

    public void receiveBook(Book book) {
        this.borrowedBooks.add(book);
    }

    public void giveBook(Book book) {
        this.borrowedBooks.remove(book);
    }
}

class Library {
    public ArrayList<Book> books = new ArrayList<>();
    public ArrayList<Member> members = new ArrayList<>();

    public void addMember(Member member) throws Exception {
        if (isMemberIdExist(member.id)) {
            throw new Exception("ID already taken. Please choose another ID.");
        }
        this.members.add(member);
    }

    public Boolean isMemberIdExist(String id) {
        for (Member member : this.members) {
            if (member.id.equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void giveBook(String bookId, String memberId) throws Exception {
        Book book = this.getBookById(bookId);
        if (book == null) {
            throw new Exception("Book not available.");
        }

        Member member = this.getMemberById(memberId);
        if (member == null) {
            throw new Exception("You are not a member.");
        }

        this.books.remove(book);
        member.borrowedBooks.add(book);
    }

    public void receiveBook(String bookId, String memberId) throws Exception {
        Book book = this.getBookById(bookId);
        if (book == null) {
            throw new Exception("Invalid book ID.");
        }

        Member member = this.getMemberById(memberId);
        if (member == null) {
            throw new Exception("You are not a member.");
        }

        member.borrowedBooks.remove(book);
        this.books.add(book);
    }

    private Member getMemberById(String id) {
        for (Member member : this.members) {
            if (member.id.equals(id)) {
                return member;
            }
        }
        return null;
    }

    private Book getBookById(String id) {
        for (Book book : this.books) {
            if (book.id.equals(id)) {
                return book;
            }
        }
        return null;
    }
}

class Main {

    static Scanner scan = new Scanner(System.in);
    static Library library = new Library();

    public static void main(String[] args) {
        initLibraryData();

        String isContinue = "y";

        while (isContinue.equals("y")) {
            showMenu();
            int selectedMenu = chooseMenu();

            try {
                if (selectedMenu == 1) {
                    showBooks();
                } else if (selectedMenu == 2) {
                    showMembers();
                } else if (selectedMenu == 3) {
                    addMember();
                } else if (selectedMenu == 4) {
                    borrowBook();
                } else if (selectedMenu == 5) {
                    returnBook();
                } else {
                    System.out.println("Invalid choice, please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("Continue? (y/n): ");
            isContinue = scan.next();
        }
    }

    public static void showMenu() {
        System.out.println("================================");
        System.out.println("1. Show books list");
        System.out.println("2. Show members list");
        System.out.println("3. Add member");
        System.out.println("4. Borrow book");
        System.out.println("5. Return book");
        System.out.println("================================");
    }

    public static void initLibraryData() {
        Book book1 = new Book();
        book1.id = "1";
        book1.title = "Java";

        Book book2 = new Book();
        book2.id = "2";
        book2.title = "Python";

        Book book3 = new Book();
        book3.id = "3";
        book3.title = "OOP";

        Member member1 = new Member();
        member1.id = "1";
        member1.name = "aaa";

        Member member2 = new Member();
        member2.id = "2";
        member2.name = "bbb";

        Member member3 = new Member();
        member3.id = "3";
        member3.name = "ccc";

        library.books.add(book1);
        library.books.add(book2);
        library.books.add(book3);

        library.members.add(member1);
        library.members.add(member2);
        library.members.add(member3);
    }

    public static int chooseMenu() {
        System.out.print("Choose menu: ");
        return scan.nextInt();
    }

    public static void showBooks() {
        for (Book book : library.books) {
            System.out.println(book.id + " " + book.title);
        }
    }

    public static void showMembers() {
        for (Member member : library.members) {
            System.out.println(member.id + " " + member.name);
        }
    }

    public static void addMember() {
        Member member = new Member();

        System.out.print("ID: ");
        member.id = scan.next();

        System.out.print("Name: ");
        member.name = scan.next();

        try {
            library.addMember(member);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void borrowBook() {
        System.out.print("Member ID: ");
        String memberId = scan.next();

        System.out.print("Book ID: ");
        String bookId = scan.next();

        try {
            library.giveBook(bookId, memberId);
            System.out.println("Book borrowed successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void returnBook() {
        System.out.print("Member ID: ");
        String memberId = scan.next();

        System.out.print("Book ID: ");
        String bookId = scan.next();

        try {
            library.receiveBook(bookId, memberId);
            System.out.println("Book returned successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}