# Как обратиться к gsm-модулю
Чтобы обратиться к gsm-модулю, зайдите в ./app/src/main/java/com/example/boilerchurch/MainActivity.java. Измените переменную number на тот номер, который установлен в вашем gsm-модуле.

# Переменная выглядит вот так:
String number = "<your number>";

# Что на данный момент умеет приложение?
Приложение может звонить и отправлять смс. Команды пока не расписал. Нажав на кнопку ПОЛУЧИТЬ ИНФОРМАЦИЮ, приложение будет звонить на номер, который вы указали в переменной number. Кнопка ОТПРАВИТЬ КОМАНДУ отправит на номер переменной number смс с текстом "Текстовое СМС". Взимается плата в зависимости от вашего мобильного оператора.
