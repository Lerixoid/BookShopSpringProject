package com.mas.project;

import com.mas.project.Model.*;
import com.mas.project.Repository.BookRepository;
import com.mas.project.Repository.CartRepository;
import com.mas.project.Repository.CategoryRepository;
import com.mas.project.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.sql.SQLClientInfoException;
import java.time.LocalDate;

@SpringBootApplication
public class Application implements CommandLineRunner {


	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private CartRepository cartRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*Category scienceFiction = new Category("science-fiction");
		categoryRepository.save(scienceFiction);
		System.out.println(scienceFiction);


		/*Book book1 = new Book("1984", "George Orwell",LocalDate.of(1949, 6,8 ),
				"Nineteen Eighty-Four is a dystopian social science fiction novel and cautionary tale written by English writer George Orwell. It was published on 8 June 1949 by Secker & Warburg as Orwell's ninth and final book completed in his lifetime.",
			BookRating.VERYPOPULAR,150.00, null,"/images/book1.png",5 );
		System.out.println(book1);*/
		/*
		Category fantasy = new Category("fantasy");
		categoryRepository.save(fantasy);

		Book book2 = new Book("Harry Potter", "J.K.Rowling",LocalDate.of(1949, 6,8 ),"Harry Potter and the Philosopher's Stone is a fantasy novel written by British author J. K. Rowling. The first novel in the Harry Potter series and Rowling's debut novel, it follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry.",
				BookRating.BESTSELLER,100.00, fantasy,"/images/book4.png",4 );
		bookRepository.save(book2);
		Book book3 = new Book("Harry Potter 2", "J.K.Rowling",LocalDate.of(1949, 6,8 ),"Harry Potter and the Philosopher's Stone is a fantasy novel written by British author J. K. Rowling. The first novel in the Harry Potter series and Rowling's debut novel, it follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry.",
				BookRating.BESTSELLER,120.00, fantasy,"/images/book6.png",5 );
		bookRepository.save(book3);
		Book book4 = new Book("Harry Potter 3", "J.K.Rowling",LocalDate.of(1949, 6,8 ),"Harry Potter and the Prisoner of Azkaban is a fantasy novel written by British author J. K. Rowling and is the third in the Harry Potter series. The book follows Harry Potter, a young wizard, in his third year at Hogwarts School of Witchcraft and Wizardry.",
				BookRating.BESTSELLER,160.00, fantasy,"/images/book7.png",4 );
		bookRepository.save(book4);
		Book book5 = new Book("Harry Potter 4", "J.K.Rowling",LocalDate.of(1949, 6,8 ),"Harry Potter and the Goblet of Fire is a fantasy novel written by British author J. K. Rowling and the fourth novel in the Harry Potter series. ",
				BookRating.BESTSELLER,160.00, fantasy,"/images/book5.png",2 );
		bookRepository.save(book5);
		Book book6 = new Book("Bogul Mamak", "George Orwell",LocalDate.of(1949, 6,8 ),"Satirical allegorical novella by George Orwell, first published in England on 17 August 1945. The book tells the story of a group of farm animals who rebel against their human farmer, hoping to create a society where the animals can be equal, free, and happy. ",
				BookRating.VERYPOPULAR,150.00, scienceFiction,"/images/book2.png",2 );
		bookRepository.save(book6);
		Book book7 = new Book("Sherlock2", "C. Doyle",LocalDate.of(1949, 6,8 ),"Sherlock HolmesThe Complete Novels and StoriesVolume IISince his first appearance in Beeton's Christmas Annual in 1887, Sir Arthur Conan Doyle's Sherlock Holmes has been one of the most beloved fictional characters ever created. Now, in two paperback volumes, ",
				BookRating.VERYPOPULAR,150.00, scienceFiction,"/images/book8.png",5 );
		bookRepository.save(book7);
		Category detective = new Category("detective");
		categoryRepository.save(detective);
		Book book8 = new Book("Sherlock Holmes", "C. Doyle",LocalDate.of(1949, 6,8 ),"Sherlock HolmesThe Complete Novels and StoriesVolume IISince his first appearance in Beeton's Christmas Annual in 1887, Sir Arthur Conan Doyle's Sherlock Holmes has been one of the most beloved fictional characters ever created. Now, in two paperback volumes. ",
				BookRating.VERYPOPULAR,150.00, detective,"/images/book3.png",4 );
		bookRepository.save(book8);
		*/

		/*Comment comment = new Comment("I like this book, it is very good", LocalDate.of(2022, 5,8),"Brenan Savage",book1 );
		System.out.println(comment);
		commentRepository.save(comment);*/

		/*Cart cart = new Cart ();
		cart.addBook(book1);
		cartRepository.save(cart);
		bookRepository.save(book1);*/

		//System.out.println(bookRepository.getReferenceById(1).getPublisher().getName());


	}

}
