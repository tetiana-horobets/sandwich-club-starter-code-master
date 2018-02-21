package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String JSON_NAME_KEY = "name";
    private static final String JSON_MAIN_Name_KEY = "mainName";
    private static final String JSON_ALSO_KNOWN_AS_KEY = "alsoKnownAs";
    private static final String JSON_PLACE_OF_ORIGIN_KEY = "placeOfOrigin";
    private static final String JSON_DESCRIPTION_KEY = "description";
    private static final String JSON_IMAGE_KEY = "image";
    private static final String JSON_INGREDIENTS_KEY = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        String mainName = null;
        List<String> alsoKnownAsList = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has(JSON_NAME_KEY)){
                JSONObject subObject = jsonObject.getJSONObject(JSON_NAME_KEY);
                mainName = subObject.optString(JSON_MAIN_Name_KEY);

                JSONArray alsoKnownAsArray = subObject.getJSONArray(JSON_ALSO_KNOWN_AS_KEY);
                alsoKnownAsList = new ArrayList<>();
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    String alsoKnownAs = alsoKnownAsArray.optString(i);
                    alsoKnownAsList.add(alsoKnownAs);
                }
            }
            String placeOfOrigin = jsonObject.optString(JSON_PLACE_OF_ORIGIN_KEY);
            String description = jsonObject.optString(JSON_DESCRIPTION_KEY);
            String image = jsonObject.optString(JSON_IMAGE_KEY);

            JSONArray ingredientsArray = jsonObject.getJSONArray(JSON_INGREDIENTS_KEY);
            List<String> ingredientsList = new ArrayList<>();
            for (int i = 0; i < ingredientsArray.length(); i++) {
                String ingredients = ingredientsArray.optString(i);
                ingredientsList.add(ingredients);
            }
            return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
