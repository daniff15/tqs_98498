package com.tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ParameterType;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class BookSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDateTime iso8601Date(String year, String month, String day){
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0);
    }

	@Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
	public void a_book_with_the_title_written_by_published_in(final String title, final String author, final LocalDateTime published) {
		Date publicado = java.util.Date.from(published.atZone(ZoneId.systemDefault()).toInstant());
		Book book = new Book(title, author, publicado);
        library.addBook(book);
	}
	
	@Given("another book with the title {string}, written by {string}, published in {iso8601Date}")
	public void another_book_with_the_title_written_by_published_in(final String title, final String author, final LocalDateTime published) {
		Date publicado = java.util.Date.from(published.atZone(ZoneId.systemDefault()).toInstant());
		Book book = new Book(title, author, publicado);
        library.addBook(book);
	}

	@When("the customer searches for books published between {int} and {int}")
	public void the_customer_searches_for_books_published_between_and(final Integer from, final Integer to) {
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy");
		try {
			Date inicio = originalFormat.parse(from.toString());
			Date fim = originalFormat.parse(to.toString());
			result = library.findBooks(inicio, fim);
		} catch (ParseException var4) {
			var4.printStackTrace();
		}
	}

	@Then("{int} books should have been found")
	public void books_should_have_been_found(final int booksFound) {
    	assertEquals(result.size(), booksFound);
	}

	@Then("Book {int} should have the title {string}")
	public void book_should_have_the_title(final int position, final String title) {
    	assertEquals(result.get(position - 1).getTitle(), title);
	}
}
