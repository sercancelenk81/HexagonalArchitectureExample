package com.ej.common.exception;

public class BillNoAlreadyExistException extends RuntimeException {
    public static final String BILL_ALREADY_EXIST = " Nolu Başka Bir Fatura Bulunmaktadır!";

    public BillNoAlreadyExistException(String billNo) {
        super(billNo+BILL_ALREADY_EXIST);
    }
}
