# Bank REST - обновлённая реализация (русский)

Проект содержит:
- Spring Boot 3, Java 17
- PostgreSQL + Liquibase
- Spring Security + JWT
- Роли: ROLE_ADMIN, ROLE_USER
- Шифрование номера карты и маскирование

## DB настройки (готово по твоим данным)
БД: `bank-rest`
Пользователь: `bankrest`
Пароль: `bankrest`

## Быстрый запуск (локально)
1. Убедись, что PostgreSQL запущен и user/DB настроены.
2. mvn clean package
3. mvn spring-boot:run

Swagger: http://localhost:8080/swagger-ui.html

Учётные данные: admin/admin123 (создаётся автоматически при старте приложения)
