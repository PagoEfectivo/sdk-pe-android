# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

-keep class pe.elcomercio.pagoefectivosdk.models.** { *; }

# necesita mantener el nombre porque es un punto de entrada para el usuario
-keep class pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk { *; }
-keep class pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk$Builder { *; }
-keep class pe.elcomercio.pagoefectivosdk.cip.usermodel.** { *; }
-keep interface pe.elcomercio.pagoefectivosdk.cip.CipListener {*;}
-keep interface pe.elcomercio.pagoefectivosdk.cip.SearchListener {*;}
-keep class pe.elcomercio.pagoefectivosdk.util.Currency {*;}
-keep class pe.elcomercio.pagoefectivosdk.util.DocumentType {*;}
-keep enum pe.elcomercio.pagoefectivosdk.resource.* {*;}
-keepclassmembers class pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk {
    public <methods>;
}

-keepclassmembers class pe.elcomercio.pagoefectivosdk.PagoEfectivoApp {
    public <methods>;
}

-dontwarn javax.annotation.**
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**