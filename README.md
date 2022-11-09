# 1. Что будет в выводе после выполнения метода check()?
```java
public String doSmth(){
try {
    System.out.println("some try");
    return "from main method";
} finally {
    System.out.println("finally");
    return "from finally block";
}
}

public void check() {
  System.out.println(doSmth());
}
``` 

## В выводе будет
```sh
some try
finally
from finally block
```

Так как finally выполняется в практически в любом случае, даже если, например, мы добавим блок catch, выбрасывающий ошибку:
```java
try {
    throw new ArrayIndexOutOfBoundsException();
}
catch (Exception ex){
    throw new OutOfMemoryError();
}
finally {
    return "from finally block";
}
```
А данный метод НЕ выкинет StackOverflowError из-за бесконечной рекурсии, а просто зависнет:
```java
public static void work(){
        try {
            work();
        } finally {
            work();
        }
    }
```


# 2. Поиск в БД с созданием если не нашли. id - pk в таблице User
```java
public T orElseGet(Supplier<? extends T> other){}

{
	var user = userRepository.findById(userData.getId()).orElseGet(createUser(userData));
}

    private Supplier<User> createUser(UserDataDTO userData) {
        var user = new User();
        user.setId(userData.getId());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setMiddleName(user.getMiddleName());
        user.setCreationDate(LocalDateTime.now());
        userRepository.saveAndFlush(user);

        return () -> user;
    }
```
## Есть ли проблемы в коде выше? Если есть то какие?

- В createUser поля копируются вручную, если будут изменены поля, придется менять и этот фрагмент кода.
- В базу данных записывается ID, переданный из вне.
- Сохранение в базу осуществляется во вспомогательном методе, если при сохранении станет необходимо реализовывать дополнительную логику, этот фрагмент придется менять.
- Если первая строка не является напоминанием сигнатуры метода orElseGet объекта Optional, а является частью кода, ее следует убрать.
