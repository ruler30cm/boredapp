#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_id_ac_ui_cs_mobileprogramming_muhammadrizkidarmawan_boredapp_fragment_AboutMeFragment_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from Rizdar😊";
    return env->NewStringUTF(hello.c_str());
}