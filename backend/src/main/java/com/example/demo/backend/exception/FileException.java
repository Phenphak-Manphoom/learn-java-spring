package com.example.demo.backend.exception;

public class FileException extends BaseException {

    public FileException(String code) {
        super("file." + code);
        
    }

    public static FileException fileNull(){
        return new FileException("file.null");
    }
    public static FileException fileMaxSize(){
        return new FileException("file.max.size");
    }

    public static FileException unsupported(){
        return new FileException("unsupported.file.type");
    }
}
