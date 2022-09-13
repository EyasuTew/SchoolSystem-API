package com.product.school.utility;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PaginationMaker {

    public Pageable createPage(int page, int size){
        return createPage(page, size, "","");
    }

//    public Pageable createPage(int page, int size,String sortBy){
//        return createPage(page, size, sortBy,"");
//    }
    public Pageable createPage(int page, int size, String sortBy,String sortOrder) {
        if(sortBy==null || sortBy.isEmpty()){
            sortBy="id";
        }

        if(sortOrder==null || sortOrder.isEmpty()){
            sortOrder="DESC";
        }

        if (sortOrder.equals("ASC")) {
            return PageRequest.of(
                    page,
                    size,
                    Sort.by(sortBy).ascending()
            );
        }
        else {
            return PageRequest.of(
                    page,
                    size,
                    Sort.by(sortBy).descending()
            );
        }
    }
}
