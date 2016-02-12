# bowling
Консольное приложения для подсчета очков в боулинге

## Сборка
Для того, что бы собрать приложение из исходников надо предварительно установить Maven и выполнить в консоли:
```
mvn clean package
```

## Запуск
Для того что бы запустить собранный проект надо выполнить в консоли:
```
mvn exec:java -q \
  -Dexec.mainClass=ru.kurtov.bowling.Game \
  -Dexec.args="Петя Дима 3 4 X 4 3 X - 4 X 3 4 6 / - 4 9 - 3 5 X 4 5 8 - 4 2 7 2 2 3 3 6 2 / 4 4 4"
```

Эта команда выведет в консоль заполненную таблицу для боулинга для двух игроков:
```
┌─────────┬───┬───┬───┬───┬───┬───┬───┬───┬───┬─────┐
│   Имя   │ 1 │ 2 │ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │ 9 │  10 │
├─────────┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┬─┤
│Петя     │3│4│4│3│-│4│3│4│-│4│3│5│4│5│4│2│2│3│2│/│4│
│         ├─┴─┼─┴─┼─┴─┼─┴─┼─┴─┼─┴─┼─┴─┼─┴─┼─┴─┼─┴─┴─┤
│         │  7│ 14│ 18│ 25│ 29│ 37│ 46│ 52│ 57│   71│
└─────────┴───┴───┴───┴───┴───┴───┴───┴───┴───┴─────┘
┌─────────┬───┬───┬───┬───┬───┬───┬───┬───┬───┬─────┐
│   Имя   │ 1 │ 2 │ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │ 9 │  10 │
├─────────┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┬─┤
│Дима     │X│ │X│ │X│ │6│/│9│-│X│ │8│-│7│2│3│6│4│4│ │
│         ├─┴─┼─┴─┼─┴─┼─┴─┼─┴─┼─┴─┼─┴─┼─┴─┼─┴─┼─┴─┴─┤
│         │ 30│ 56│ 76│ 95│104│122│130│139│148│  156│
└─────────┴───┴───┴───┴───┴───┴───┴───┴───┴───┴─────┘
```

В первой строке содержатся порядковые номера фреймов, во второй - сбитые кегли, в третьей - очки.

## Параметры
Сначала вводятся имена игроков. Как только введен параметр, который может трактоваться,
как количество сбитых кегель (0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, /, X, -) - ввод имен прекращается и начинается ввод результатов бросков.
Броски вводятся в той же последовательности, что и в самой игре: броски группируются по фреймам, а сами фреймы чередуются между игроками.

Далее приводится пояснение как броски соотносятся с игроками на примере входных параметров из раздела "Запуск".
```
 ┌────────┬─┬───┬─┬───┬─┬───┬─┬──────────────────.......──────────────────┬─┬─┐
 │        │ │   │ │   │ │   │ │                                           │ │ │ 
Петя Дима 3 4 X 4 3 X - 4 X 3 4 6 / - 4 9 - 3 5 X 4 5 8 - 4 2 7 2 2 3 3 6 2 / 4 4 4
      │       │     │     │     │ │                                             │ │ 
      └───────┴─────┴─────┴─────┴─┴──────────────.......────────────────────────┴─┘
```

Броски можно прекратить вводить в любой момент. В этом случае выведется в консоль не полность заполненная таблица с очками

## Отчет о покрытии тестами
Отчет о покрытии кода тестами хронится в виде статического сайта и доступен в директории [target/site/jacoco](https://github.com/kurtov/bowling/tree/master/target/site/jacoco)

## Атрефакты CI
В качестве CI используется Jankins. Атрефакты полученные после успешной сборки скопированы средстави "Redeploy Artifacts" в директорию [jenkinsArtifact/ru/kurtov/bowling/](https://github.com/kurtov/bowling/tree/master/jenkinsArtifact/ru/kurtov/bowling)
