package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {
	
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	
	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {

		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Publisher publisher = new Publisher();
		publisher.setName("SS Publishers");
		publisher.setCity("Alle");
		publisher.setState("United Kingdom");
		publisherRepository.save(publisher);
		
		System.out.println("Publiher count = " + publisherRepository.count());
		
		Author eric = new Author("Eric", "Evans");
		Book domainDrivenDesign = new Book("Domain Driven Design", "10089611616");	
		
		eric.getBooks().add(domainDrivenDesign);
		domainDrivenDesign.getAuthors().add(eric);	
		domainDrivenDesign.setPublisher(publisher);
		publisher.getBooks().add(domainDrivenDesign);
		
		authorRepository.save(eric);
		bookRepository.save(domainDrivenDesign);
		publisherRepository.save(publisher);
		
		Author rod = new Author("Rod", "JohnSon");
		Book noEJB = new Book("J2EE Development without EJB", "110025500");
		
		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);
		noEJB.setPublisher(publisher);
		publisher.getBooks().add(noEJB);
		
		authorRepository.save(rod);
		bookRepository.save(noEJB);
		publisherRepository.save(publisher);
		
		System.out.println("Started n Bootstrap.");
		System.out.println("Number of books loaded: " + bookRepository.count());
		System.out.println("Publisher -> number of books published: " + publisher.getBooks().size());
	}

}
