package assignment3.jcnelson.partb;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NavigationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NavigationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationFragment extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NavigationFragment.
     */
    public static NavigationFragment newInstance() {
        NavigationFragment fragment = new NavigationFragment();
        return fragment;
    }

    public NavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        Button songsButton = (Button) view.findViewById(R.id.songsButton);
        songsButton.setOnClickListener(this);

        Button videosButton = (Button) view.findViewById(R.id.videosButton);
        videosButton.setOnClickListener(this);

        Button wallpapersButton = (Button) view.findViewById(R.id.wallpapersButton);
        wallpapersButton.setOnClickListener(this);

        Button mailingListButton = (Button) view.findViewById(R.id.mailingListButton);
        mailingListButton.setOnClickListener(this);
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.songsButton:
                intent = new Intent(getActivity(), SongActivity.class);
                startActivity(intent);
                break;

            case R.id.videosButton:
                intent = new Intent(getActivity(), VideoActivity.class);
                startActivity(intent);
                break;

            case R.id.wallpapersButton:
                intent = new Intent(getActivity(), WallpaperActivity.class);
                startActivity(intent);
                break;

            case R.id.mailingListButton:
                enterEmail();
                break;

            default:
                break;
        }
    }

    private void enterEmail() {

        // create a new AlertDialog Builder
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        final EditText email = new EditText(getActivity());

        builder.setTitle(R.string.emailTitle); // title bar string
        builder.setMessage(R.string.emailMessage); // message to display
        builder.setView(email);

        // provide an OK button that simply dismisses the dialog
        builder.setPositiveButton(R.string.buttonAdd,
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int button)
                    {
                        HomeActivity.mailingList.add(email.getText().toString());
                    } // end method onClick
                } // end anonymous inner class
        ); // end call to method setPositiveButton

        builder.setNegativeButton(R.string.buttonCancel, null);
        builder.show(); // display the Dialog
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
