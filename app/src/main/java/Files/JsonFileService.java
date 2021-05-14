package Files;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import com.example.myapplication.Galery.MusicInfo;
import com.example.myapplication.Galery.Post;
import com.example.myapplication.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class JsonFileService {
    private static final String MUSIC_ITEMS_ARRAY_NAME = "music_items";
    private static final String IMAGE_URL_SECTION = "image_url";
    private static final String MUSIC_URL_SECTION = "music_url";
    private static final String TITLE_SECTION = "title";
    private static final String DESCRIPTION_SECTION = "description";

    private final String fileName;
    private final Context context;


    public JsonFileService(String fileToWorkWith, Context context) {
        this.fileName = fileToWorkWith;
        this.context = context;
    }


    public void updateFile(List<Post> items,  Context context) {
        try {
            JSONArray jsonArray = serializeObjects(items,context);
            JSONObject instance = new JSONObject();
            instance.put(MUSIC_ITEMS_ARRAY_NAME, jsonArray);
            tryWriteToFile(instance.toString());
        } catch (JSONException ex) {
            System.out.println(ex);
        }

    }

    public List<Post> readFromFile() {
        System.out.println("1readFromFile");
        String plainText = tryReadFromFile();
        if (plainText == null || plainText.equals("")) {
            System.out.println("2");
            return Collections.emptyList();
        }

        try {
            System.out.println("3");
            JSONObject jsonObject =  new JSONObject(plainText);
            JSONArray jsonItems = jsonObject.getJSONArray(MUSIC_ITEMS_ARRAY_NAME);
            System.out.println("cont:"+jsonItems.length());
            return deserializeObjects(jsonItems);
        } catch (JSONException ex) {
            System.out.println(ex);
            return Collections.emptyList();
        }
    }

    private List<Post> deserializeObjects(JSONArray jsonArray) throws JSONException {
        List<Post> items = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i += 1) {
            JSONObject jsonItem = jsonArray.getJSONObject(i);
           // Uri imageUrl = Uri.parse(jsonItem.getString(IMAGE_URL_SECTION)) ;

            String imagePath = jsonItem.getString(IMAGE_URL_SECTION) ;
            Uri imageUrl = Uri.fromFile(new File(imagePath));
            String title = jsonItem.getString(TITLE_SECTION);
            String description = jsonItem.getString(DESCRIPTION_SECTION);
          //  Uri misicUri = Uri.parse(jsonItem.getString(MUSIC_URL_SECTION));
            System.out.println("add "+ title);
            System.out.println("add "+ imageUrl);
            System.out.println("add "+ imagePath);
            System.out.println("add "+ description);
        //    System.out.println("add "+ misicUri);
            items.add(new Post(imagePath,imageUrl,title, description,null));

        }
        System.out.println("total "+ items.size());
        return items;
    }

    private JSONArray serializeObjects(List<Post> items, Context context) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (Post item : items) {
                JSONObject jsonItem = new JSONObject();
                String path = item.get_imagePath();
                if(path == null )
                {
                 //  path = Util.getFilePath(item.get_imageUri());
                  //  path =  item.get_imageUri().getEncodedPath();
                    path =   Util.getPathFromUri(context,item.get_imageUri());;
                    System.out.println("-------------------------------"+path);
                }
                System.out.println(path);
                jsonItem.put(IMAGE_URL_SECTION, path);
                jsonItem.put(TITLE_SECTION, item.get_header()).toString();
                jsonItem.put(DESCRIPTION_SECTION, item.get_description());
              //  jsonItem.put(MUSIC_URL_SECTION, item.get_musicInfo().music);
                jsonArray.put(jsonItem);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
            System.out.println(ex.getStackTrace());
        }
        return jsonArray;
    }

    private String tryReadFromFile() {
        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            String result = new BufferedReader(new InputStreamReader(inputStream)).readLine();
            System.out.println("Reading " + result.length() + " characters");
            inputStream.close();
            return result;
        } catch (IOException e) {
            return "";
        }
    }

    private void tryWriteToFile(String text) {
        try {
            System.out.println("Writing " + text.length() + " characters");
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }
}
