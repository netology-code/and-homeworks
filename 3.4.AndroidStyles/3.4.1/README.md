## Задача №1
### Переключение цветов в приложении


**Задание:**

Среди пользователей приложений у всех разные вкусы. Хорошее приложение должно иметь варианты изменения дизайна под каждого пользователя. 

Возьмите [домашнее задание “Переключение языка”](https://github.com/netology-code/and-homeworks/tree/master/3.3.AppResources/3.3.2) из занятия 3.3.
Добавьте на экран переключения языков следующие элементы: 

* Spinner с выбором цветов — “черный, зеленый и синий”. 
* Кнопка ОК — по клику на кнопку во всем приложении меняется цветовая тема. 

Необходимо создать 3 цветовых темы для приложения - в черных, зеленых или синих тонах, применяемых к цвету всех элементов. 




**Выполнение**

1. В созданном в предыдущем ДЗ приложении с выбором языка нужно добавить описанные выше элементы интерфейса:
* Spinner с выбором цветов - “черный, зеленый и синий”. 
* Кнопка ОК - по клику на кнопку во всем приложении меняется цветовая тема. 


2. Далее создаём сами темы.


Создадим тему в xml файле стиле как стиль вручную.

1. Для этого надо открыть файл res/values/styles.xml.
2. Создать перед тегом `</resources>` новый стиль.
3. Указать, что новая тема наследуется от AppTheme через атрибут parent (как используется этот атрибут разбирали на занятии).


В результате получится простая тема:
```
   <style name="ThemeGreen" parent="AppTheme">

    </style>
```

4. Создадим цвета для темы, для этого надо открыть файл colors.xml и внести основные цвета:
```
<color name="colorPrimaryGreen">#2E8B57</color>
<color name="colorPrimaryDarkGreen">#006400</color>
<color name="colorAccentGreen">#6B8E23</color>
```

5. Создать такие же цвета для синей и чёрной темы
6. Вернемся к файлу styles и внесем цвета в тему:

```
    <style name="AppThemeGreen" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimaryGreen</item>
        <item name="colorButtonNormal">@color/colorPrimaryDarkGreen</item>
        <item name="android:textColor">@color/colorTextGreen</item>
        <item name="colorAccent">@color/colorAccentGreen</item>
    </style>
```

7. Создать оставшиеся 2 темы и также задать для них цвета


3. После завершения задания цветов, нужно реализовать переключение  Activity, и вставить в приложение код, отвечающий за переключение,  как это было рассказано на лекции.
Пример с лекции:
## Activity
```
public class ChangeThemeActivity extends Activity implements OnClickListener
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.main);
          
                    findViewById(R.id.button1).setOnClickListener(this);
          findViewById(R.id.button2).setOnClickListener(this);
          findViewById(R.id.button3).setOnClickListener(this);
    }

     @Override
     public void onClick(View v) 
     {
          // TODO Auto-generated method stub
          switch (v.getId())
          {

          case R.id.button1:
          Utils.changeToTheme(this, Utils.THEME_DEFAULT);
          break;
          case R.id.button2:
          Utils.changeToTheme(this, Utils.THEME_WHITE);
          break;
          case R.id.button3:
          Utils.changeToTheme(this, Utils.THEME_BLUE);
          break;
          }
     }
}
```


## Класс Utils
```
public class Utils
{
     private static int sTheme;

     public final static int THEME_DEFAULT = 0;
     public final static int THEME_WHITE = 1;
     public final static int THEME_BLUE = 2;

     /**
      * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
      */
     public static void changeToTheme(Activity activity, int theme)
     {
          sTheme = theme;
          activity.finish();

activity.startActivity(new Intent(activity, activity.getClass()));

     }

     /** Set the theme of the activity, according to the configuration. */
     public static void onActivityCreateSetTheme(Activity activity)
     {
          switch (sTheme)
          {
          default:
          case THEME_DEFAULT:
              activity.setTheme(R.style.FirstTheme);
              break;
          case THEME_WHITE:
              activity.setTheme(R.style.SecondTheme);
              break;
          case THEME_BLUE:
              activity.setTheme(R.style.Thirdheme);
              break;
          }
     }
}
```



**Результаты и сдача домашнего задания**


Готовый проект разместить на GitHub.

Первую и вторую задачи не нужно объединять в один проект. Пожалуйста, присылайте первую задачу отдельной ссылкой.

Примерный вид приложения:

![](https://i.imgur.com/0Yj44vJ.jpg)

![](https://i.imgur.com/OAr76gm.jpg)

![](https://i.imgur.com/cYU4aS0.jpg)
