package com.example.avellb155max.appcalorias.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gustavo on 08/11/15.
 */
public class Utils {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat dateFormatDia = new SimpleDateFormat("dd/MM");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static CharSequence formatDate(Date date) {
        return dateFormat.format(date);
    }

    public static CharSequence formatDateDia(Date date) {
        return dateFormatDia.format(date);
    }

    public static Date parsetDate(String string) {
        try {
            return dateFormat.parse(string);
        } catch (ParseException e) {
            Log.e("Parsing", "Erro ao fazer parsing da data '" + string + "'. " + e);
            return null;
        }
    }

    public static CharSequence formatTime(Date date) {
        return timeFormat.format(date);
    }

    public static Date parsetTime(String string) {
        try {
            return timeFormat.parse(string);
        } catch (ParseException e) {
            Log.e("Parsing", "Erro ao fazer parsing da hora '"+string+"'. " + e);
            return null;
        }
    }
}