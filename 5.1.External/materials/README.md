# Пример работы с внешним файловым хранилищем

## Задача
Создать приложение, которое из системной папки Downloads получает изображение и выводит его в ImageView.
При запуске приложение должно записывать информацию о запуске в private (частное) внешнее файловое хранилище. Его можно найти в папке Android, если откроете диспетчер файлов в телефоне.

## Решение
1. В Манифест нужно добавить разрешения на работу с внешним файловым хранилищем.
Вставить 2 строки перед `</manifest>`. 

```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

2. В разметку нужно добавить ImageView и присвоить ему id android:id="@+id/imageView".

Получится примерно такой код:

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="357dp"
        android:layout_height="660dp"
        android:layout_marginStart="16dp"
        android:layout_marginLeft="16dp"
        android:layout_marginTop="25dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@android:drawable/btn_star_big_on" />
</android.support.constraint.ConstraintLayout>
```
4. В MainActivity нужно создать функцию для выполнения логики приложения (загрузка картинки) и назвать ее, например, LoadImg().

5. Нужно добавить метод для проверки доступности хранилища.

```
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
```

6. В методе LoadImg() нужно реализовать получение пути к внешнему публичному файловому хранилищу, а именно к папке Downloads с помощью `Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS` и проверить возможность работы с хранилищем с помощью метода из шага 5 .

7. Через Класс файл нужно открыть файл.

```
File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "1.jpg");
```

8. Используя класс Bitmap, нужно загрузить картинку в память.

```
 Bitmap b = BitmapFactory.decodeFile(file.getAbsolutePath());
```

9. Нужно вывести картинку в `imageView` с помощью метода `setImageBitmap(...)`.

Общий код метода LoadImg() получится следующим:

```
     private void LoadImg()
     {

         ImageView view = (ImageView) findViewById(R.id.imageView);
         if (isExternalStorageWritable()) {


             File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                     "1.jpg");

             Bitmap b = BitmapFactory.decodeFile(file.getAbsolutePath());
             view.setImageBitmap(b);
             Toast.makeText(this, file.getAbsolutePath(), Toast.LENGTH_LONG).show();
         } else {
             Toast.makeText(this, "File Error", Toast.LENGTH_LONG).show();
         }
     }
```
10. Нужно проверить и запросить у пользователя разрешения на работу с внешним файловым хранилищем. 
В методе onCreate() нужно добавить запрос статуса данного разрешения:

```
/получаем статус разрешения на чтение из файлового хранилища
        int permissionStatus = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
```

Затем нужно добавить проверку полученного статуса:

```
if (permissionStatus == PackageManager.PERMISSION_GRANTED)
```

Если проверка пройдена, то можно вызывать наш метод и грузить им картинку, иначе надо запросить разрешение:

```
 if (permissionStatus == PackageManager.PERMISSION_GRANTED) {

           LoadImg();
        }
        else
        {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_READ_STORAGE);
        }
```

Появилась неизвестная константа `REQUEST_CODE_PERMISSION_READ_STORAGE`. Это просто должно быть некое число, по которому мы потом сможем отфильтровать конкретное событие. В любом случае её нужно объявить в начале класса.

```
public static final int REQUEST_CODE_PERMISSION_READ_STORAGE = 10;
```

После метода onCreate надо переопределить метод `onRequestPermissionsResult`, который обработает результат запроса у пользователя разрешения. В нём будет опять же использоваться объявленная выше константа, общий код метода такой:

```
@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_READ_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    LoadImg();
                } else {
                    // permission denied
                }
                return;
        }
    }
```
Если права дали, то также нужно грузить картинку. Иначе — ничего не делаем, прав нет.

**На этом работа с публичным внешним файловым хранилищем завершена!**

11. В частное внешнее файловое хранилище нужно создать файл с логами и записать в него строку `"App loaded"`.
В методе `LoadImg()`, убедившись, что хранилище смонтировано, нужно сделать запись:

```
 File logFile = new File(getApplicationContext().getExternalFilesDir(null),"log.txt");
             try {
                 FileWriter logWriter = new FileWriter(logFile);
                 logWriter.append("App loaded");
                 logWriter.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
```



## Общий код MainActivity:

```
package ru.comdcomp.filedemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_PERMISSION_READ_STORAGE = 10;
    public static final int REQUEST_CODE_PERMISSION_WRITE_STORAGE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //получаем статус разрешения на чтение из файлового хранилища
        int permissionStatus = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {

           LoadImg();
        }
        else
        {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_READ_STORAGE);
        }

     }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_READ_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    LoadImg();
                } else {
                    // permission denied
                }
                return;
        }
    }

     private void LoadImg()
     {

         ImageView view = (ImageView) findViewById(R.id.imageView);
         if (isExternalStorageWritable()) {

             File logFile = new File(getApplicationContext().getExternalFilesDir(null),"log.txt");
             try {
                 FileWriter logWriter = new FileWriter(logFile);
                 logWriter.append("App loaded");
                 logWriter.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }

             File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                     "1.jpg");

             Bitmap b = BitmapFactory.decodeFile(file.getAbsolutePath());
             view.setImageBitmap(b);
             Toast.makeText(this, file.getAbsolutePath(), Toast.LENGTH_LONG).show();
         } else {
             Toast.makeText(this, "File Error", Toast.LENGTH_LONG).show();
         }
     }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }



}

```
