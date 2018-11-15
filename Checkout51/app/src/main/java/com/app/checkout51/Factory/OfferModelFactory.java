package com.app.checkout51.Factory;

import com.app.checkout51.models.OfferModel;

import org.json.JSONException;
import org.json.JSONObject;

public class OfferModelFactory {

    public static OfferModel decodeJSON(JSONObject root) throws JSONException {

        final int id  = root.getInt("offer_id");
        final String name = root.getString("name");
        final String imageURL = root.getString("image_url");
        final double cashBack = root.getDouble("cash_back");

        return new OfferModel(id, name, imageURL, cashBack);

    }
}
