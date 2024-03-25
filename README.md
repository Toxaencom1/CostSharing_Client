Проект представления - Client

Описание:

В этом проекте мы создаем простой пользовательский интерфейс для демонстрации работы микросервисов DataBase и CalculateSession. Интерфейс позволяет:

    Просматривать список сессий.
    Добавлять новую сессию.
    Добавлять/изменять/удалять чеки.
    Добавлять/изменять/удалять факты оплаты.
    Добавлять/изменять/удалять использование продуктов.
    Получить результат расчета сессии.

Технологии:

    Java 17
    Spring Boot 
    Thymeleaf
    OpenFeign
    HTML
    CSS

Структура проекта:

    pom.xml: Файл Maven, содержащий зависимости проекта.
    src/main/java: Папка с исходным кодом Java.
    src/main/resources: Папка с ресурсами проекта, application.yml, CSS и HTML файлы.

Настройка проекта:

    Добавление зависимостей:
        Добавляем зависимости для Thymeleaf и OpenFeign.

    Настройка application.yml:
        Задаем порт, имя для микросервиса.
        Настраиваем CORS для разрешения кросс-доменных запросов.
        Настраиваем Thymeleaf.

    Создание пакетов и файлов:
        Создаем стандартные пакеты и классы для архитектуры Spring Web MVC.
        Создаем классы для работы с микросервисами DataBase и CalculateSession.
        Создаем классы DTO (Data Transfer Object) для передачи данных между HTML и Java.

    Создание интерфейсов Feign:
        Создаем интерфейсы Feign для взаимодействия с микросервисами.

    Реализация контроллера ClientController:
        Реализуем методы для обработки GET и POST запросов.
        Используем Thymeleaf для отображения шаблонов HTML.
        Используем OpenFeign для взаимодействия с микросервисами.
        Добавляем атрибуты в Model для передачи данных на HTML страницы.

    Создание HTML шаблонов:
        Создаем HTML шаблоны для отображения интерфейса.
        Используем Thymeleaf для отображения динамических данных.


Преимущества:

    Простой и понятный интерфейс.
    Возможность демонстрации работы микросервисов.
    Легко расширяемый.

Ограничения:

    Небольшой набор функций.
    Простой дизайн.
    Подходит не для всех платформ.

В следующих проектах:

    Расширение функциональности интерфейса.
    Улучшение дизайна интерфейса.
    Добавление авторизации и аутентификации.

Важно:

    Проект Client является простым примером использования Spring Boot для создания веб-приложения.
    