## Задача 1. Записная книжка в SharedPreferences.
### Описание

Одно из самых популярных приложений на телефон - это заметки. Давайте создадим свои собственные.
Элементы приложения:
* EditText с заметкой.
* Кнопка “Сохранить” (при клике на нее, заметка сохраняется в SharedPreferences).

При перезапуске приложения, если заметка ранее была сохранена, она отображается в EditText компоненте.

### Реализация

1. Реализации программы заметки требуется ресурсный файл с компонентами EditText и Button. 
Создадим приложение и разместим эти компоненты во ViewGroup типа LinearLayout с вертикальной ориентацией.
Учитывая выше сказанное, ресурсный файл activity_main.xml примет следующий вид:

```  
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center_vertical"
    android:orientation="vertical"
    android:padding="50dp"
    tools:context=".MainActivity">

      <EditText
        android:id="@+id/inputNote"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />

    <Button
        android:id="@+id/btnSaveNote"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Сохранить" />

</LinearLayout>
```  
Где все элементы обозначены соответствующими Id. Лучшей практикой является перенос текстов из атрибута android:text и android:hint в файл string.xml.

2. Следующим шагом будет инициализация элементов View в MainActivity. Инициализацию элементов осуществим в отдельно созданном методе initViews().
Вместе с инициализацией так же осуществим реализацию нажатия кнопки btnSaveNote.
Инициализация элементов примет следующий вид:

```  
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.netodologia.lessons.R;

public class MainActivity extends AppCompatActivity {

    private EditText mInputNote;
    private Button mBtnSaveNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2n2_1);
        initViews();
    }

    private void initViews() {
        mInputNote = findViewById(R.id.inputNote);
        mBtnSaveNote = findViewById(R.id.btnSaveNote);
		
        mBtnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
```  

3. И так у нас имеется поле ввода и кнопка сохранения. При нажатии на кнопку "Сохранить" нужно, чтобы данные, которые ввел пользователь в поле EditText mInputNote, сохранились в SharedPreferences. 

Создадим SharedPreferences с именем "MyNote", добавив переменную myNoteSharedPref типа SharedPreferences:

```  
    private EditText mInputNote;
    private Button mBtnSaveNote;
    
	private SharedPreferences myNoteSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2n2_1);
        initViews();
    }

    private void initViews() {
        mInputNote = findViewById(R.id.inputNote);
        mBtnSaveNote = findViewById(R.id.btnSaveNote);

        myNoteSharedPref = getSharedPreferences("MyNote", MODE_PRIVATE);
        
        mBtnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
	
  ```  
    
4. Для сохранения текстового типа данных в myNoteSharedPref создадим ключ-константу NOTE_TEXT:
 
 ```  
   private static String NOTE_TEXT = "note_text";
 ```  
   
5. Для того чтобы сохранить данные, нужно получить объект SharedPreferences.Editor объекта myNoteSharedPref и воспользоваться методом putString(key, value):
* Поместим получение объекта и функцию сохранения текста в обработчик нажатия кнопки mBtnSaveNote. 
* После сохранения данных выведем на экран сообщение для пользователя:
  
  ```  
	 mBtnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor myEditor = myNoteSharedPref.edit();
                String noteTxt = mInputNote.getText().toString();
                myEditor.putString(NOTE_TEXT, noteTxt);
                myEditor.apply();
				Toast.makeText(MainActivity.this, "данные сохранены", Toast.LENGTH_LONG).show();
            }
        });

   ```  


6. Осталось вывести сохраненные данные (если таковые имеются) в поле mInputNote при перезапуке приложения. 
Если перезапустить приложение, то введенные данные в поле mInputNote исчезнут.

Создадим метод getDateFromSharedPref() и осуществим чтение данных из myNoteSharedPref:


```  
	private void getDateFromSharedPref(){
     String noteTxt = myNoteSharedPref.getString(NOTE_TEXT, "");
     mInputNote.setText(noteTxt);
    }
```  

	Строка mInputNote.setText(noteTxt); присваивает полученные данные полю mInputNote.
	
7.	Осталось вызвать данный метод при запуске приложения. Добавим данный метод в onCreate():
	
  ```  
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2n2_1);
        initViews();
        getDateFromSharedPref();
    }
  ```
