Опис: 
- Ми хочему створити веб аплікацію зі зручним інтерфейсом для перегляду загальної інформації про фільми різних жанрів.
- Буде 3 ролі: адміністратор,  зареганий юзер і не зареганий юзер.
- Про можливості доступу до сторінок для всіх ролей ми будемо визначати в процесі розробки.
- Фільми будуть мати рейтинги (Зареганий юзер матиме можливість ставити оцінку за кожен фільм), можливість перегляду трейлерів і коментарів від інших юзерів (адміністратор буде мати можливість видалення коментарів).
- Зробити зручний пошук для пошуку фільмів за різними критеріями. 

Ціль: зробити якісну веб аплікацію, яка буде простою для користувачів різного віку.

Архітектура:

Backend
- Spring Data JPA 
- Spring Security
- Spring MVC
- MySQL

Frontend
- Bootstrap (HTML, CSS, JS)
- jQuery

Projects UML diagram:
![uml](https://user-images.githubusercontent.com/13065693/49940156-7c3be280-fee7-11e8-8c9e-b78bbe5bc003.png)

Users possibilities flow:
![newflow](https://user-images.githubusercontent.com/13065693/49940358-ee142c00-fee7-11e8-999b-94226d432e50.png)

Storage:

Er-diagram

![er](https://user-images.githubusercontent.com/13065693/49941411-118ca600-feeb-11e8-90e1-258f1a86dc43.png)

Saving and uploading videos and images on server

Для завантаження картинок і відео на сервер використовується інтерфейс MultipartResolver. Існують дві реалізації: CommonsMultipartResolver і StandardServletMultipartResolver, які дозволяють фреймворку завантажувати файли. За замовчуванням цей інтерфейс не включається і необхідно вказувати його у файлі конфігурації. Після налаштування будь-який запит про завантаження буде відправлятися цьому інтерфейсу.Фрагмент коду з файлу dispatcher-servlet.xml конфігурації для використання даного інтерфейсу

![viewresolver](https://user-images.githubusercontent.com/13065693/49941715-01c19180-feec-11e8-8ecc-7fe8b72d459e.png)

Картинки та відео зберігаються  на сервері в папках {шлях до папки apache-tomcat }/images i {шлях до папки apache-tomcat }/videos відповідно

Resilency:

Всі реквести проходять на сервері валідацію. Повідомнення про невалідні дані обробляються на фронтенді, і виводяться спеціально відведених місцях.У пакеті validator обробляються всі можливі валідації, що звязані з помилками користувачів(обробка йде майже на кожен ntity)

Security:

Для аутентифікації та авторизації в нашому проекті використовується Spring Security:
"Spring Security is a powerful and highly customizable authentication and access-control framework. It is the de-facto standard for securing Spring-based applications.

Spring Security is a framework that focuses on providing both authentication and authorization to Java applications. Like all Spring projects, the real power of Spring Security is found in how easily it can be extended to meet custom requirements"
Secutiry.xml -конфігураційни файл, в якому ми влесне і налаштували авторизацію і аутинтифікацію, а також налаштували обмеження на доступ до сторінок адміністратора для звичайних юзерів.

Microsoft Threat Modeling Tool:

Для моделювання загроз для нашого проекту ми скористались Microsoft Threat Modeling Tool


![1](https://user-images.githubusercontent.com/13065693/49945438-941a6300-fef5-11e8-811d-60c595660319.png)
![2](https://user-images.githubusercontent.com/13065693/49945455-9bda0780-fef5-11e8-9225-48032a3ecaff.png)
![3](https://user-images.githubusercontent.com/13065693/49945457-9bda0780-fef5-11e8-845a-7f59ec366737.png)
![4](https://user-images.githubusercontent.com/13065693/49945458-9bda0780-fef5-11e8-9f08-9feecdd307fb.png)
![5](https://user-images.githubusercontent.com/13065693/49945459-9bda0780-fef5-11e8-8bef-f1495b60791e.png)
![6](https://user-images.githubusercontent.com/13065693/49945462-9c729e00-fef5-11e8-8b99-52869f6dcffa.png)
![7](https://user-images.githubusercontent.com/13065693/49945463-9c729e00-fef5-11e8-808d-8f711be6c2fe.png)

Hosting services: Google cloud
URL: http://35.237.113.55/
for Admin role- login - admin - pass admin
