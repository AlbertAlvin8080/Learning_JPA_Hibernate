package br.com.albert.tests.crud;

import br.com.albert.model.dao.BookDAO;
import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.Author;
import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.Book;
import br.com.albert.model.entity.CriteriaQuery_EntityGraphs.BookStore;
import br.com.albert.persistence.ConnectionFactory;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CrudTest01 {
    public static void main(String[] args) {
        char op = '0';
        Scanner sc = new Scanner(System.in);
        BookDAO dao = new BookDAO();

        do {
            System.out.println("1 - Show All Books");
            System.out.println("2 - Insert Book");
            System.out.println("3 - Update Book");
            System.out.println("4 - Delete Book");
            System.out.println("0 - END");
            op = sc.nextLine().charAt(0);

            System.out.println();
            switch (op) {
                case '1' -> {
//                    dao.findAll().forEach(System.out::println);
                    dao.findAllV2().forEach(b -> {
                        System.out.println(b);
                        System.out.println(
                                b.getAuthorList().stream().map(Author::getName)
                                        .collect(Collectors.joining("; ", "Author(s): ", ""))
                        );
                        System.out.println(
                                b.getBookStoreList().stream().map(BookStore::getName)
                                        .collect(Collectors.joining("; ", "BookStore(s): ", ""))
                        );
                        System.out.println("--------------------------");
                    });
                }
                case '2' -> {
                    System.out.println("Provide a name for the Book: ");
                    String bookName = sc.nextLine();
                    System.out.println("Now for the Author: ");
                    String authorName = sc.nextLine();
                    System.out.println("Now for the BookStore: ");
                    String bookStoreName = sc.nextLine();

                    Book book = dao.insertBook(new Book(bookName), authorName, bookStoreName);
                    System.out.println("Inserted Book [" + book + "]");
                }
                case '3' -> {
                    System.out.println("Provide the old name:");
                    String oldName = sc.nextLine();
                    System.out.println("Provide a new name:");
                    String newName = sc.nextLine();
                    Book book = dao.updateBook(oldName, newName);
                    if (book != null) {
                        System.out.println("Book updated: [" + book + "]");
                    } else {
                        System.out.println("The book doesn't exist.");
                    }
                }
                case '4' -> {
                    System.out.println("Provide the name for deletion:");
                    String name = sc.nextLine();
                    Book book = dao.deleteBook(name);
                    if (book != null) {
                        System.out.println("Book deleted: [" + book + "]");
                    } else {
                        System.out.println("The book doesn't exist.");
                    }
                }
                case '0' -> {
                    System.out.println("# Shutting down...");
                }
                default -> {
                    System.out.println("# INVALID OPTION #");
                }
            }
            System.out.println();

        } while (op != '0');
    }
}
