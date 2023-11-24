# ReadMe_Lesson8

Файл со скриптом лежит тут: /usr/local/sbin
Общая переменная для пути выполнения подскриптов
scriptPath="/home/vagrant/лк8182/lesson8"

## Автоматизация развертывания WebBooks на ВМ
• настройка учетных записей - done - adduser.sh
• установка компонентов и зависимостей - done - работает - логирует - installing_comp.sh
• выполнение миграций для БД - done - migration_db_script
• сборка приложения - done - работает - логирует - buildapp_script
• создание systemd unit - создан - логирует
• запуск приложения - done - launch_app_script
• установка и настройка Nginx в качестве фронта (можно без SSL) - done -настройка идет через копирование в конфиге migration_db_script, сразу после установки компонентов


