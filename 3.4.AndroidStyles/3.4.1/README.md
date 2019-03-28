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

Используя встроенный редактор тем

Tools -> Theme Editor

В правой панели настройки темы в выпадающем списке тем (1 на рисунке) выбираем строчку Create New Theme:

![](https://i.imgur.com/U5YLuxZ.png)

И на основе темы AppTheme создаём собственную, называя её в соответствии с заданием.

3. После создания темы, нужно выбрать подходящие этой теме цвета:

![](https://i.imgur.com/ypYmwJJ.png)

Предупреждение о том, что тема не используется, можно игнорировать, т.к тема пока не применяется к приложению, а будет применятся когда это нам потребуется сделать по действию пользователя.


4. После завершения задания цветов, нужно реализовать переключение  Activity, и вставить в приложение код, отвечающий за переключение,  как это было рассказано на лекции.
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
