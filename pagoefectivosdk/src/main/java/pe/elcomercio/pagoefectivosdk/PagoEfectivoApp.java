package pe.elcomercio.pagoefectivosdk;

import android.annotation.SuppressLint;
import android.content.Context;

@SuppressLint("StaticFieldLeak")
public class PagoEfectivoApp {

    protected int serviceId;
    String accessKey;
    String secretKey;
    protected boolean isSandBox = false;
    static boolean initialized = false;

    protected static Context context;
    private static volatile PagoEfectivoSdk instance;

    protected PagoEfectivoApp() {
        if (instance != null) {
            throw new RuntimeException(
                    "Use initialize() method to get the single instance of this class.");
        }
    }

    /**
     * Getter for the PagoEfectivoApp instance.
     *
     * @return The PagoEfectivoApp instance
     */
    public static PagoEfectivoSdk initialize(Context context) {
        PagoEfectivoSdk result = instance;
        if (result == null) {
            synchronized (PagoEfectivoSdk.class) {
                result = instance;
                if (result == null) {
                    instance = result = new PagoEfectivoSdk();
                    initialized = true;
                    PagoEfectivoApp.context = context;
                }
            }
        }
        return result;
    }

    public static PagoEfectivoSdk getInstance() {
        return instance;
    }

    public void setSandBox(boolean sandBox) {
        isSandBox = sandBox;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}
