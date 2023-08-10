package com.mas.project.Exception;

import org.aspectj.weaver.ast.Not;

public class NotFoundException extends Exception{

    public NotFoundException(String message){
        super(message);
    }
}
