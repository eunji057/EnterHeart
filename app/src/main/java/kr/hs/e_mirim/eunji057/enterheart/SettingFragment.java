package kr.hs.e_mirim.eunji057.enterheart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] setting = {"공지사항", "버전정보", "개발자 정보", "알림", "보안"};

        View view=inflater.inflate(R.layout.fragment_setting, container, false);
        final ListView settingListView=(ListView)view.findViewById(R.id.setting_list);
        final ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, setting);

        settingListView.setAdapter(arrayAdapter);

        return view;
    }

}
