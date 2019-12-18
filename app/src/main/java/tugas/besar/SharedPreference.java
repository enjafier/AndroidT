package tugas.besar;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
       public static final String SCAN = "scanlos";

        public static final String SCAN_RESULT = "scanloss";

        SharedPreferences sp;
        SharedPreferences.Editor spEditor;

        public SharedPreference(Context context){
            sp = context.getSharedPreferences(SCAN, Context.MODE_PRIVATE);
            spEditor = sp.edit();
        }

        public void saveSPString(String keySP, String value){
            spEditor.putString(keySP, value);
            spEditor.commit();
        }

        public String getSpRESULT(){
            return sp.getString(SCAN_RESULT, "");
        }
}
