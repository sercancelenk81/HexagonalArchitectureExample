package com.ej.common.enums;

public enum CreateBillResponseType {
    CREATE_BILL_SUCCESS("Fatura Başarı ile oluşturuldu."),
    CREATE_BILL_FAILURE("Fatura oluşturulamadı.");

    private String message;

    public String getMessage() {
        return message;
    }

    CreateBillResponseType(String message) {
        this.message=message;
    }
}
