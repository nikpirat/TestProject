а. Проверка свободен ли билет - 10 баллов:
а1. Пользователь указывает ticketId (число), а сервер возвращает ответ
(boolean) если билет свободен или нет (ответ будет базироваться на данных,
которые вы создали в вашей базе данных).

б. Создание чек ин (check-in) багажу- 10 баллов:
б1. Пользователь предоставляет индекс места назначения (destinationId) и
индекс багажа (baggageId) - оба индекса являются числами.
б2. Сервер вернет boolean ответ если чекин был успешен.
в. Добавление возможности купона для скидок (coupon code) - 10 баллов:

в1. Пользователь предоставляет индекс купона (coupon id) и цену билета
(double).
в2. Сервер вернет валидный ответ, если купон существует - создайте заранее
таблицу с валидными купонами. Если купон существует, то вернется новая цена
в зависимости от скидки, которая выбирается рандомально из списка - 10%,
50%, 60%. Если купон не существует, то сервер вернет ошибку.

г. Локальный кеш (cache) внутри вашей памяти - 15 баллов:
г1. Кеш должен быть создан вами и нельзя использовать внешние библиотеки
как spring / guava cache. Например, если идут одни и те же запросы подряд, то
ответы будут возвращаться с кеша, а не с базы данных. Вы должны понять, что
кеш намного меньше базы данных


#Для Запуска :

1) Соберите проект сборщиком типа Maven
2) Перейти по стандартному адресу localhost:8080/