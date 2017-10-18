package tlg.bot.mappers.base;

import java.util.List;

/**
 * интерфейс для маппера, который содержит все базовые методы для работы с таблицей бд
 */
public interface IMapper<T> {
    /**
     * получить все записи из таблицы бд
     * @return Список записей
     */
    List<T> findAll();

    /**
     * Получить диапазон запписей (пагинация)
     * @param from: смещение
     * @param ammount: количество записей
     * @return Список записей
     */
    List<T> findRange(int from, int ammount);

    /**
     * Получить запись по id
     * @param id: id искомой записи
     * @return Запись, хранимая по id
     */
    T findById(Long id);

    /**
     * Вставить запись в таблицу бд
     * @param data: запись, которую необходимо добавить в бд
     */
    void insert(T data);

    /**
     * Изменить запись в таблице бд
     * @param data: запись, которую нужно обновить (все поля кроме id, по полю id определяется сама запись)
     */
    void update(T data);

    /**
     * Удалить запись из таблицы бд
     * @param id: id удаляемой записи
     */
    void delete(Long id);

    /**
     * Получить количество записей в таблице бд
     * @return количество записей в таблице бд
     */
    Long size();

    /**
     * Очистить таблицу бд
     */
    void clear();

}


