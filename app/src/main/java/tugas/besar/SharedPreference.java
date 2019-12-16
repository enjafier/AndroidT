package tugas.besar;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
       public static final String SP_AGENT_APP = "spAgentApp";

        public static final String SP_ID = "spId";
        public static final String SP_RESULT = "spEmail";

        public static final String SP_SUDAH_LOGIN = "spSudahLogin";

        SharedPreferences sp;
        SharedPreferences.Editor spEditor;

        public SharedPreference(Context context){
            sp = context.getSharedPreferences(SP_AGENT_APP, Context.MODE_PRIVATE);
            spEditor = sp.edit();
        }

        public void saveSPString(String keySP, String value){
            spEditor.putString(keySP, value);
            spEditor.commit();
        }

        public void saveSPInt(String keySP, int value){
            spEditor.putInt(keySP, value);
            spEditor.commit();
        }

        public void saveSPBoolean(String keySP, boolean value){
            spEditor.putBoolean(keySP, value);
            spEditor.commit();
        }

        public String getSpId(){
            return sp.getString(SP_ID, "");
        }

        public String getSpRESULT(){
            return sp.getString(SP_RESULT, "");
        }

        public Boolean getSPSudahLogin(){
            return sp.getBoolean(SP_SUDAH_LOGIN, false);
        }
}
