package kr.hs.e_mirim.eunji057.enterheart;


import android.*;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhonebookFragment extends Fragment {
    ListView listPerson;
    public PhonebookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
        View view = inflater.inflate(R.layout.fragment_phonebook, container, false);
        listPerson = (ListView) view.findViewById(R.id.listPerson);

        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.READ_CONTACTS)) {

            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }
        getList();
        return view;
    }


    public void getList(){

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] projection = new String[] {
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        };

        String[] selectionArgs = null;

        //정렬
        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
        //조회해서 가져온다
        Cursor contactCursor = getActivity().managedQuery(uri, projection, null, selectionArgs, sortOrder);

        //정보를 담을 array 설정
        ArrayList persons = new ArrayList();

        if(contactCursor.moveToFirst()){
            do{
                persons.add(contactCursor.getString(1) + "/" + contactCursor.getString(0));
            }while(contactCursor.moveToNext());
        }

        //리스트에 연결할 adapter 설정
        final ArrayAdapter adp = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, persons);

        //리스트뷰에 표시
        listPerson.setAdapter(adp);
        listPerson.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str=(String)adp.getItem(i);
                Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
