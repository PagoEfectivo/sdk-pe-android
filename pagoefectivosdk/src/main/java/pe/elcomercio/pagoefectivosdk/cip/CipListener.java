package pe.elcomercio.pagoefectivosdk.cip;

import java.util.List;

import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipEntity;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError;

public interface CipListener {

    void onCipSuccessful(CipEntity cipEntityGenerated);

    void onCipError(List<CipError> message);

    void onCipFailure(String message);

}
