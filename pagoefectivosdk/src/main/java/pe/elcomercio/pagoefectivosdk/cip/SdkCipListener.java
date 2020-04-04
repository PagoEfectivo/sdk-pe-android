package pe.elcomercio.pagoefectivosdk.cip;

import java.util.List;

import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipEntity;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError;


interface SdkCipListener {
    void onCipSuccessful(CipEntity cipEntity);

    void onCipError(List<CipError> errorList);

    void onCipFailure(String message);

    void onRefreshToken();
}
