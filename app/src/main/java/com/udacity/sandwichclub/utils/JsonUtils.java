package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        final String SANDWICH_NAME = "name";
        final String SANDWICH_MAIN_NAME = "mainName";
        final String SANDWICH_KNOWN_AS = "alsoKnownAs";
        final String SANDWICH_ORIGIN = "placeOfOrigin";
        final String SANDWICH_DESC = "description";
        final String SANDWICH_IMAGE = "image";
        final String SANDWICH_INGREDIENTS = "ingredients";

        try {
            JSONObject sandwichJson = new JSONObject(json);

            JSONObject sandwichName = sandwichJson.getJSONObject(SANDWICH_NAME);

            String mainName = sandwichName.getString(SANDWICH_MAIN_NAME);

            JSONArray otherNames = sandwichName.getJSONArray(SANDWICH_KNOWN_AS);
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < otherNames.length(); i++) {
                alsoKnownAs.add(otherNames.getString(i));
            }

            String placeOfOrigin = sandwichJson.getString(SANDWICH_ORIGIN);

            String description = sandwichJson.getString(SANDWICH_DESC);

            String image = sandwichJson.getString(SANDWICH_IMAGE);

            JSONArray ingredientsList = sandwichJson.getJSONArray(SANDWICH_INGREDIENTS);
            List<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsList.length(); i++) {
                ingredients.add(ingredientsList.getString(i));
            }

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
