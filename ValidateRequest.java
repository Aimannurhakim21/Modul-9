public class ValidateRequest {
    private boolean validateRequest(CustomerInquiryRequest request, int customerIDFieldLength, int productFieldLength) {
        if (request.CustomerProduct.ProductNumber != null && request.Customer.CustomerID != null) {
            if (request.CustomerProduct.ProductNumber.equals("") && request.Customer.CustomerID.equals("")) {
                // Both were populated
                throw new BusinessException(HandledErrors.InvalidBothParameterMessage);
            } else if (request.Customer.CustomerID.equals("") && request.CustomerProduct.ProductNumber.equals("")) {
                // if objects were instantiated but not populated
                throw new BusinessException(HandledErrors.CustomerEmptyMessage);
            } else if (!request.Customer.CustomerID.equals("")) {
                // Note: ProductNumber was equal
                // to string.empty Check Customer ID length
                if (request.Customer.CustomerID.length() > customerIDFieldLength) {
                    throw new BusinessException(HandledErrors.CustomerInvalidLengthMessage);
                }
            } else {
                // Note: CustomerID was equal
                // to string.empty check Product Length
                if (request.CustomerProduct.ProductNumber.length() > productFieldLength) {
                    throw new BusinessException(HandledErrors.ProductInvalidLengthMessage);
                }
            }
        } else if (request.CustomerProduct.ProductNumber == null && request.Customer.CustomerID == null) {
            // Both were null
            throw new BusinessException(HandledErrors.CustomerEmptyMessage);
        } else if (request.CustomerProduct.ProductNumber == null) {
            // ProductNumber was null and CustomerID was not null
            if (request.Customer.CustomerID.length() > customerIDFieldLength) {
                throw new BusinessException(HandledErrors.CustomerInvalidLengthMessage);
            }
        } else { // ProductNumber not null and CustomerID was null
            // Check Product Length
            if (request.CustomerProduct.ProductNumber.length() > productFieldLength) {
                throw new BusinessException(HandledErrors.ProductInvalidLengthMessage);
            }
        }
        // Set objects below with formatted data i.e PadLeft
        if (request.Customer.CustomerID != null) {
            request.Customer.CustomerID = String.format("%" + customerIDFieldLength + "s", request.Customer.CustomerID)
                    .replace(' ', '0');
        }
        if (request.CustomerProduct.ProductNumber != null) {
            request.CustomerProduct.ProductNumber = String
                    .format("%" + productFieldLength + "s", request.CustomerProduct.ProductNumber).replace(' ', '0');
        }
        return true;
    }
}
