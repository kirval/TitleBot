package tlg.bot.mappers.base;

/**
 * Created by Denis on 25.08.2017.
 * Дополнительно содержит метод поиска по имени. Применяется если таковое поле есть в таблице
 */
public interface INamedMapper<T> extends IMapper<T> {
    T findByName(String name);
}
