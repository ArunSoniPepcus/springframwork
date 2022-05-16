package com.pepcus.services;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pepcus.models.Book;
import com.pepcus.models.Shelves;
import com.pepcus.repositorys.BookRepository;
import com.pepcus.repositorys.ShelvesRepository;

@Service
public class ShelvesService {

  @Autowired
  private ShelvesRepository shelvesRepository;
  @Autowired
  private BookRepository bookRepository;

  public Shelves saveShelves(Shelves shelves) {

    shelvesRepository.save(shelves);
    return shelves;
  }

  public Shelves addBookInShelves(List<Book> books, Integer bookid) {

    Shelves existingShelves = shelvesRepository.getById(bookid);

    List<Book> existBooks = existingShelves.getBookRelation();

    for (Book book : books) {
      book.setAddedOn(new Date());
      existBooks.add(book);
    }
    return shelvesRepository.save(existingShelves);

  }
}
