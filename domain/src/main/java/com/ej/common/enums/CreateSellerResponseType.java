package com.ej.common.enums;

public enum CreateSellerResponseType {
    CREATE_SELLER_SUCCESS("Satıcı Başarı ile oluşturuldu."),
    CREATE_SELLER_FAILURE("Satıcı oluşturulamadı.");

    private String message;

    public String getMessage() {
        return message;
    }

    CreateSellerResponseType(String message) {
        this.message=message;
    }
}
