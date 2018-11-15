package com.app.checkout51.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.checkout51.Factory.OfferModelFactory;
import com.app.checkout51.R;
import com.app.checkout51.models.OfferModel;
import com.app.checkout51.utils.CommonUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ArrayList<OfferModel> offersList = new ArrayList<OfferModel>();
    private OfferAdapter offerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setupModelWithData();
        ListView offersListView = (ListView) findViewById(R.id.offers_list_view);
        offerAdapter = new OfferAdapter(getApplicationContext());
        offersListView.setAdapter(offerAdapter);
    }

    public void setupModelWithData() {

        String jsonResponseBatches = CommonUtils.getSharedInstance().readFromFile(this);

        try {

            final JSONObject jsonBatchObject = new JSONObject(jsonResponseBatches);

            JSONArray jsonOffers = jsonBatchObject.getJSONArray("offers");

            for(int j = 0; j < jsonOffers.length(); j++) {
                JSONObject jsonOffer = jsonOffers.getJSONObject(j);
                OfferModel offer = OfferModelFactory.decodeJSON(jsonOffer);
                offersList.add(offer);
            }

            Collections.sort(offersList);

        } catch (JSONException e) {
            Log.d("Error In File Read", e.toString());
        }
    }

    public class OfferAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater mInflater;

        OfferAdapter(Context context) {
            mContext = context;
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return offersList.size();
        }

        @Override
        public Object getItem(int position) {
            return offersList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View row = convertView;
            OfferViewHolder offerViewHolder = null;

            if (row == null) {
                row =mInflater.inflate(R.layout.offer_list_item, parent, false);
                offerViewHolder = new OfferViewHolder(row);
                row.setTag(offerViewHolder);

            } else {
                offerViewHolder = (OfferViewHolder) row.getTag();
            }

            Picasso.with(MainActivity.this).load(offersList.get(position).
                                                        getImageURL()).fit().into(offerViewHolder.imgOffer);
            offerViewHolder.txtName.setText(getResources().getString(R.string.offer_name_title) + ": " +
                                                                        offersList.get(position).getName());
            offerViewHolder.txtCashBack.setText(getResources().getString(R.string.cash_back_title) + ": " +
                                                                        Double.toString(offersList.get(position).getCashBack()));

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Not Implemented as not required in the project
                }
            });
            return row;
        }
    }

    public class OfferViewHolder {

        private ImageView imgOffer;
        private TextView txtName;
        private TextView txtCashBack;

        public OfferViewHolder(View view) {
            imgOffer = (ImageView) view.findViewById(R.id.offer_image);
            txtName = (TextView) view .findViewById(R.id.offer_name_text);
            txtCashBack = (TextView) view .findViewById(R.id.offer_cash_back_text);
        }
    }
}
