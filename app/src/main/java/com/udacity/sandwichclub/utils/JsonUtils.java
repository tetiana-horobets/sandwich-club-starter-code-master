package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject subObject = jsonObject.getJSONObject("name");

            String mainName = subObject.getString("mainName");

            JSONArray alsoKnownAsArray = subObject.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                String alsoKnownAs = alsoKnownAsArray.getString(i);
                alsoKnownAsList.add(alsoKnownAs);
            }

            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            String description = jsonObject.getString("description");
            String image = jsonObject.getString("image");

            JSONArray ingredientsArray = jsonObject.getJSONArray("ingredients");
            List<String> ingredientsList = new ArrayList<>();
            for (int i = 0; i < ingredientsArray.length(); i++) {
                String ingredients = ingredientsArray.getString(i);
                ingredientsList.add(ingredients);
            }
            return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
