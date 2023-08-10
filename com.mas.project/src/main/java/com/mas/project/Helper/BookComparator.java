package com.mas.project.Helper;

import com.mas.project.Model.Book;


import java.util.Comparator;

public class BookComparator implements Comparator<Book>{

    @Override
    public int compare(Book b1, Book b2) {

        String title1 = b1.getTitle();
        String title2 = b2.getTitle();
        int titleCompare = title1.compareTo(title2);
        if(titleCompare!=0){
            return titleCompare;
        }
        Integer age1 = b1.getBookAge();
        Integer age2 = b2.getBookAge();

        return age1.compareTo(age2);
    }
}

