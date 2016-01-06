package cz.muni.fi.pv256.movio.uco410422.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cz.muni.fi.pv256.movio.uco410422.R;

/**
 * Created by vlado on 06.01.2016.
 */
public class InitFragment extends Fragment {

    private View v;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_init, container, false);

        return v;
    }
}
