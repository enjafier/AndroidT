package tugas.besar.fragments;


import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import tugas.besar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GeneratorFragment extends Fragment {

    private String message = "";
    private String type = "";
    private Button button_generate;
    private EditText editText1;
    private Spinner type_spinner;
    private ImageView imageView;
    private int size = 660;
    private int size_width = 660;
    private int size_height = 264;

    private TextView success_text;
    private ImageView success_imageview;

    private String time;

    private Bitmap myBitmap;

    public GeneratorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_generator, container, false);
        message = "";
        type = "QR Code";
        button_generate = view.findViewById(R.id.generate_button);
        editText1 = view.findViewById(R.id.edittext2);
        type_spinner = view.findViewById(R.id.type_spinner);
        imageView = view.findViewById(R.id.image_imageview);

        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0: type = "QR Code";break;
                    case 1: type = "Barcode";break;
                    default: type = "QR Code";break;
                }
                Log.d("type", type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                message = editText1.getText().toString();

                if (message.equals("") || type.equals(""))
                {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                    dialog.setTitle("Error");
                    dialog.setMessage("Invalid input!");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //  do nothing
                        }
                    });
                    dialog.create();
                    dialog.show();
                }
                else
                {
                    Bitmap bitmap = null;
                    try
                    {
                        bitmap = CreateImage(message, type);
                        myBitmap = bitmap;
                    }
                    catch (WriterException we)
                    {
                        we.printStackTrace();
                    }
                    if (bitmap != null)
                    {
                        imageView.setImageBitmap(bitmap);
                    }
                }
            }
        });
        return view;
    }

    public Bitmap CreateImage(String message, String type) throws WriterException
    {
        BitMatrix bitMatrix = null;
        // BitMatrix bitMatrix = new MultiFormatWriter().encode(message, BarcodeFormat.QR_CODE, size, size);
        switch (type)
        {
            case "QR Code": bitMatrix = new MultiFormatWriter().encode(message, BarcodeFormat.QR_CODE, size, size);break;
            case "Barcode": bitMatrix = new MultiFormatWriter().encode(message, BarcodeFormat.CODE_128, size_width, size_height);break;
            default: bitMatrix = new MultiFormatWriter().encode(message, BarcodeFormat.QR_CODE, size, size);break;
        }
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int [] pixels = new int [width * height];
        for (int i = 0 ; i < height ; i++)
        {
            for (int j = 0 ; j < width ; j++)
            {
                if (bitMatrix.get(j, i))
                {
                    pixels[i * width + j] = 0xff000000;
                }
                else
                {
                    pixels[i * width + j] = 0xffffffff;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }


}
