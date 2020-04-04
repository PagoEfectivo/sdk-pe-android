package pe.elcomercio.pagoefectivosdk.cip;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.pagoefectivosdk.R;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipRequest;
import pe.elcomercio.pagoefectivosdk.models.GenerateCipRequest;

import static pe.elcomercio.pagoefectivosdk.util.Helper.dateToString;

public final class CipHelper {

    private CipHelper() {
        throw new IllegalAccessError("CipEntity Helper class");
    }

    public static List<CipError> validateGenerateCipRequestObject(
            CipRequest cipRequest,
            Context context) {

        List<CipError> errorRequireds = new ArrayList<>();

        if (cipRequest == null) {
            errorRequireds.add(new CipError(
                    0,
                    "CipRequest",
                    context.getString(R.string.object_not_null)));
        } else {
            if (cipRequest.getCurrency() == null) {
                errorRequireds.add(new CipError(
                        511,
                        "Currency",
                        context.getString(R.string.currency_required)));
            }

            if (cipRequest.getAmount() == 0) {
                errorRequireds.add(new CipError(
                        513,
                        "Amount",
                        context.getString(R.string.amount_required)));
            }

            if (cipRequest.getUserEmail() == null || cipRequest.getUserEmail().isEmpty()) {
                errorRequireds.add(new CipError(
                        313,
                        "Email",
                        context.getString(R.string.user_email_required)));
            }

            if (cipRequest.getTransactionCode() == null ||
                    cipRequest.getTransactionCode().isEmpty()) {
                errorRequireds.add(new CipError(
                        515,
                        "TransactionCode",
                        context.getString(R.string.transaction_code_required)));
            }
        }
        return errorRequireds;
    }

    public static List<CipError> validateSearchCipListRequestObject(
            List<Integer> searchRequestList,
            Context context) {

        List<CipError> errorRequireds = new ArrayList<>();
        if (searchRequestList == null) {
            errorRequireds.add(new CipError(
                    0,
                    "SearchRequest",
                    context.getString(R.string.object_not_null)));
        } else if (searchRequestList.isEmpty()) {
            errorRequireds.add(new CipError(
                    0,
                    "SearchRequest",
                    context.getString(R.string.not_empty_request)));
        } else {

            for (int searchRequestCip : searchRequestList) {

                if (searchRequestCip <= 0) {
                    errorRequireds.add(new CipError(
                            0,
                            "cip",
                            context.getString(R.string.one_element_invalid)));
                }
            }
        }

        return errorRequireds;
    }

    public static GenerateCipRequest mappingRequest(CipRequest cipRequest) {

        GenerateCipRequest GenerateCipRequest = new GenerateCipRequest();

        if (cipRequest != null) {
            if (cipRequest.getCurrency() != null) {
                GenerateCipRequest.setCurrency(cipRequest.getCurrency().toString());
            }

            if (cipRequest.getAmount() != 0) {
                GenerateCipRequest.setAmount(cipRequest.getAmount());
            }

            if (cipRequest.getTransactionCode() != null) {
                GenerateCipRequest.setTransactionCode(cipRequest.getTransactionCode().trim());
            }

            if (cipRequest.getAdminEmail() != null) {
                GenerateCipRequest.setAdminEmail(cipRequest.getAdminEmail().trim());
            }

            if (cipRequest.getDateExpiry() != null) {
                GenerateCipRequest.setDateExpiry(dateToString(cipRequest.getDateExpiry()));
            }

            if (cipRequest.getPaymentConcept() != null) {
                GenerateCipRequest.setPaymentConcept(cipRequest.getPaymentConcept().trim());
            }

            if (cipRequest.getAdditionalData() != null) {
                GenerateCipRequest.setAdditionalData(cipRequest.getAdditionalData().trim());
            }

            if (cipRequest.getUserEmail() != null) {
                GenerateCipRequest.setUserEmail(cipRequest.getUserEmail().trim());
            }

            if (cipRequest.getUserName() != null) {
                GenerateCipRequest.setUserName(cipRequest.getUserName().trim());
            }

            if (cipRequest.getUserLastName() != null) {
                GenerateCipRequest.setUserLastName(cipRequest.getUserLastName().trim());
            }

            if (cipRequest.getUserUbigeo() != null) {
                GenerateCipRequest.setUserUbigeo(cipRequest.getUserUbigeo().trim());
            }

            if (cipRequest.getUserCountry() != null) {
                GenerateCipRequest.setUserCountry(cipRequest.getUserCountry().trim());
            }

            if (cipRequest.getUserDocumentType() != null) {
                GenerateCipRequest.setUserDocumentType(cipRequest.getUserDocumentType().toString());
            }

            if (cipRequest.getUserDocumentNumber() != null) {
                GenerateCipRequest.setUserDocumentNumber(cipRequest.getUserDocumentNumber().trim());
            }

            if (cipRequest.getUserPhone() != null) {
                GenerateCipRequest.setUserPhone(cipRequest.getUserPhone().trim());
            }

            if (cipRequest.getUserCodeCountry() != null) {
                GenerateCipRequest.setUserCodeCountry(cipRequest.getUserCodeCountry().trim());
            }
        }

        return GenerateCipRequest;
    }

    static SecureModel getSecure() {
        return new SecureModel();
    }
}
