## Задача 2. AppBar приложения.
### Описание

Навигация в приложении - очень важный атрибут качественного продукта. 
Объедините все предыдущие работы в единое приложение с помощью App-Bar с переходом на нужный экран приложения через меню AppBar-а.

### Реализация

1. Для реализации данной задачи нужно добавить виджет Toolbar из библиотеки android.support.v7 в ресурсный файл activity_main.xml

```  
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center_vertical"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <android.support.v7.widget.Toolbar
        android:id="@+id/my_toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        android:elevation="4dp"
        android:theme="@style/ThemeOverlay.AppCompat.ActionBar"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light" />

</FrameLayout>

```  

2. Если экран, данного Activity, отображает панель управления Toolbar, то данный класс Activity должен наследоваться от класса AppCompatActivity:

```  
	public class MainActivity extends AppCompatActivity {

	}
	
```

3. Выберем стиль Theme.AppCompat.Light.NoActionBar для главной темы приложения и пропишем в манифест файле:
   
  ```  
   <application 
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"
    />
  ```  

4. Добавим выпадающий список меню в панель управления Toolbar. 
	Для этого создадим файл menu_main.xml в папке res/menu/ (res/menu/menu_main.xml)
	Добавим кнопку навигации в menu_main.
	
  ```  
	<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context="com.netodologia.myapplication.MainActivity">
  
	<item
        android:id="@+id/action_settings"
        android:orderInCategory="100"
        android:title="Settings"
        app:showAsAction="never" />
		
     <item
        android:id="@+id/action_open_notes"
        android:orderInCategory="100"
        android:title="Записная книжка"
        app:showAsAction="never" />
</menu>
  ```  

5. Для того чтобы созданное меню появилась на панели управления, нужно переопределить метод onCreateOptionsMenu(Menu menu) в классе MainActivity,
 где нужно добавить файл menu_main.xml, указав полный путь к нему (R.menu.menu_main): 

```  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
```  

6. И так после запуска программы у нас появится панель управление с выпадающим меню. Теперь рассмотрим, как реализовать то или иное действие при нажатии на меню и выпадающего списка.
	Для этого в классе MainActivity переопределим метод onOptionsItemSelected(MenuItem item), которая вызывается при нажатии на элементы из списка menu_main.xml файла:

* Установим проверку нажатии элемента по Id (строка  if (id == R.id.action_open_notes) )
	
  ```  
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_open_notes) {
            Toast.makeText(MainActivity.this, "Отркыть записную книжку", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
	
  ```  

* Скомпилируем и запустим наше приложение.
	
7. Осталось при нажатии на меню списка: "Записная книжка", добавить функцию реализации открытия класса MainActivity из программы <Записная книжка>.
	Для этого скопируем ресурсный файл activity_main.xml из программы <Записная книжка> и перенесем в нашу программу. 
	Так как у нас уже существует activity_main.xml, то вставим скопированный файл xml под именем activity_notes.xml.
	Так же поступим и с классом MainActivity из программы <Записная книжка>: скопируем и вставим под названием NotesActivity.
	И так оба класса и ресурсные файлы расположены у нас в одной программе. 
	Для того чтобы открыть нужный класс активити при нажатии кнопки меню, нужно выполнить следующую команду:
	
      ```  
	     Intent intentNotes = new Intent(MainActivity.this, NotesActivity.class);
         startActivity(intentNotes);
	  
      ```  
  
Получаем Intent нужного активити и вызываем метод startActivity, который на входе получает Intent.
	
8. Все экраны активити должны быть прописаны в манифест-файле. Добавим NotesActivity в файл AndroidManifest.xml.

    ```  
	<activity android:name=".NotesActivity"/>
 
     ```  


Файл AndroidManifest.xml примет следующий вид:

   ```  
	<?xml version="1.0" encoding="utf-8"?>
	<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.netodologia.lessons">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.AppCompat.Light.NoActionBar">
       
	   <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
		
        <activity android:name=".NotesActivity"/>
    </application>

    </manifest>
  ```


Скомпилируем и запустим приложение.

9. Для добавление остальных работ нужно:
 * дополнить menu_main.xml файл нужными элементами меню,
 * обработать события при соответствующем нажатии на меню элемента выпадающего списка,
 * добавить файлы работ в программу,
 * реализовать вызов каждой активити для соответствующего меню.
