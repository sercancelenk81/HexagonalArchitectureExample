package com.ej.common.exception;



public class SellerNotFoundException extends RuntimeException {
    public static final String SELLER_NOT_FOUND = " Email Adresi İle Başka Bir Satıcı Bulunmamaktadır!";

    public SellerNotFoundException(String email) {
        super(email+SELLER_NOT_FOUND);
    }
}
