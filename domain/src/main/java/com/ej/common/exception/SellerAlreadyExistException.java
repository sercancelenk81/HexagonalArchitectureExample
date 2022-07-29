package com.ej.common.exception;



public class SellerAlreadyExistException extends RuntimeException {
    public static final String SELLER_ALREADY_EXIST = " Email Adresi İle Başka Bir Satıcı Bulunmaktadır!";

    public SellerAlreadyExistException(String email) {
        super(email+SELLER_ALREADY_EXIST);
    }
}
