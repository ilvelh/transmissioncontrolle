package ca.teyssedre.android.transmissioncontrol.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import ca.teyssedre.android.transmissioncontrol.R;
import ca.teyssedre.android.transmissioncontrol.model.TransmissionProfile;

public class ProfileEditFragment extends Fragment {

    public static final String ARG_PROFILE = "profile";
    private TransmissionProfile mItem;

    public ProfileEditFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(ARG_PROFILE)) {

            mItem = getArguments().getParcelable(ARG_PROFILE);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                String title = mItem.getProfileName() != null && mItem.getProfileName().length() > 0 ?
                        mItem.getProfileName() : getResources().getString(R.string.title_item_detail);
                appBarLayout.setTitle(title);
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.server_item_edit, container, false);

        EditText name = (EditText) rootView.findViewById(R.id.server_name);
        EditText domain = (EditText) rootView.findViewById(R.id.domain_name);
        CheckBox ssl = (CheckBox) rootView.findViewById(R.id.enable_ssl);
        CheckBox auth = (CheckBox) rootView.findViewById(R.id.enable_auth);
        final EditText username = (EditText) rootView.findViewById(R.id.user_name);
        final EditText password = (EditText) rootView.findViewById(R.id.password);

        username.setEnabled(auth.isChecked());
        password.setEnabled(auth.isChecked());

        auth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                username.setEnabled(b);
                password.setEnabled(b);
            }
        });

        if (mItem != null) {

            name.setText(mItem.getProfileName());
            domain.setText(mItem.getServerName());
            ssl.setChecked(mItem.isSsl());
            auth.setChecked(mItem.hasAuthentication());
            username.setText(mItem.getUsername());
            password.setText(mItem.getPassword());
        }
        return rootView;
    }
}
