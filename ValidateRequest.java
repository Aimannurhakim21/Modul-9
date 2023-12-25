public class ValidateRequest {
    private void validateRequest(CustomerInquiryRequest request, int customerFieldLength, int productFieldLength) {
        checkCustomerInquiryNotNullOrEmpty(request);
        checkCustomerInquiryNullOrEmpty(request);
        checkCustomerIDValid(request, customerFieldLength);
        checkProductNumberValid(request, productFieldLength);
    }

    private void checkCustomerInquiryNotNullOrEmpty(CustomerInquiryRequest request) {
        if (!isNullOrEmpty(request.CustomerProduct.ProductNumber) && !isNullOrEmpty(request.Customer.CustomerID)) {
            // Both were populated
            throw new BusinessException(HandledErrors.InvalidBothParameterMessage);
        }
    }

    private void checkCustomerInquiryNullOrEmpty(CustomerInquiryRequest request) {
        if (isNullOrEmpty(request.Customer.CustomerID) && isNullOrEmpty(request.CustomerProduct.ProductNumber)) {
            // Both are null or empty string
            throw new BusinessException(HandledErrors.CustomerEmptyMessage);
        }
    }

    private void checkCustomerIDValid(CustomerInquiryRequest request, int customerIDFieldLength) {
        if (!isNullOrEmpty(request.Customer.CustomerID)) {
            // Check Customer ID length
            if (request.Customer.CustomerID.length() > customerIDFieldLength) {
                throw new BusinessException(HandledErrors.CustomerInvalidLengthMessage);
            }
            // Pad the left of the customer id
            request.Customer.CustomerID = padLeft(request.Customer.CustomerID, customerIDFieldLength, '0');
        }
    }

    private void checkProductNumberValid(CustomerInquiryRequest request, int productFieldLength) {
        if (!isNullOrEmpty(request.CustomerProduct.ProductNumber)) {
            // Check Product Length
            if (request.CustomerProduct.ProductNumber.length() > productFieldLength) {
                throw new BusinessException(HandledErrors.ProductInvalidLengthMessage);
            }
            // Pad the left of the product number
            request.CustomerProduct.ProductNumber = padLeft(request.CustomerProduct.ProductNumber, productFieldLength,
                    '0');
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    private String padLeft(String value, int length, char padChar) {
        return String.format("%" + length + "s", value).replace(' ', padChar);
    }

}
