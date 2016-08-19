package android.mars.crimeintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Mars on 05.07.2016.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    TextView labelAddCrime;
    Button addNewCrime;

    protected abstract Fragment createFragment();

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        if (findViewById(R.id.detail_fragment_container) == null) {
            labelAddCrime = (TextView) findViewById(R.id.label_add_new_crime);
            addNewCrime = (Button) findViewById(R.id.button_add_new_crime);
            addNewCrime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Crime crime = new Crime();
                    CrimeLab.get(getApplication()).addCrime(crime);
                    Intent intent = CrimePagerActivity.newIntent(getApplication(), crime.getId());
                    startActivity(intent);
                }
            });

            if (CrimeLab.get(getApplication()).getCrimes().size() != 0) {
                labelAddCrime.setVisibility(View.INVISIBLE);
                addNewCrime.setVisibility(View.INVISIBLE);
            }
        }

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

    }

}
