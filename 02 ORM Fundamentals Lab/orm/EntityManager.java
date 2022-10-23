package orm;

import orm.anotations.Column;
import orm.anotations.Entity;
import orm.anotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager<E> implements DBContext<E> {

    private final Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws SQLException, IllegalAccessException {
        String tableName = this.getTableName(entity.getClass());
        String fieldList = this.getDBFieldsWithoutIdentity(entity);
        String valueList = this.getValuesWithoutIdentity(entity);

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)",
                tableName, fieldList, valueList);

        return this.connection.prepareStatement(sql).execute();
    }

    private boolean doInsert(E entity, Field primary) throws IllegalAccessException, SQLException {
        String query = "INSERT INTO " + this.getTableName(entity.getClass()) + " ";
        String columns = "(";
        String values = "(";

        Field[] fields = entity.getClass().getDeclaredFields();

        //get all columns from User without id column
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            if (!field.isAnnotationPresent(Id.class)) {
                columns += "`" + this.getColumnName(field) + "`";

                Object value = field.get(entity);

                if (value instanceof Date) {
                    values += "'" + new SimpleDateFormat("yyyy-MM-dd").format(value) + "'";
                } else if (value instanceof Integer) {
                    values += value;
                } else {
                    values += "'" + value + "'";
                }

                if (i < fields.length - 1) {
                    values += ",";
                    columns += ",";
                }
            }
        }
        query += columns + ") VALUES " + values + ")";
        return connection.prepareStatement(query).execute();
    }

    private boolean doUpdate(E entity, Field primary) throws IllegalAccessException, SQLException {
        String query = "UPDATE " + this.getTableName(entity.getClass()) + " SET ";
        String columnAndValue = "";
        String where = "";
        Field[] fields = entity.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            Object value = field.get(entity);
            if (field.isAnnotationPresent(Id.class)) {
                where += " WHERE " + this.getColumnName(field) + " = " + value;
            } else {
                if (value instanceof Date) {
                    columnAndValue += this.getColumnName(field) + " = '"
                            + new SimpleDateFormat("yyyy-MM-dd").format(value) + "'";
                } else if (value instanceof Integer) {
                    columnAndValue += this.getColumnName(field) + " = " + value;
                } else {
                    columnAndValue += this.getColumnName(field) + " = '" + value + "'";
                }
                if (i < fields.length - 1) {
                    columnAndValue += ",";
                }
            }
        }
        query += columnAndValue + where;
        return connection.prepareStatement(query).execute();
    }

    @Override
    public Iterable<E> find(Class<E> entityType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException{
        return find(entityType, null);
    }

    @Override
    public Iterable<E> find(Class<E> entityType, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = this.getTableName(entityType);

        String sql = String.format("Select * from %s %s",
                tableName, where.equals("") ? "" : "WHERE " + where);

        ResultSet resultSet = this.connection.prepareStatement(sql).executeQuery();

        List<E> resul = new ArrayList<>();

        E entity = this.createEntity(entityType, resultSet);
        while (entity != null){
            resul.add(entity);
            entity = this.createEntity(entityType, resultSet);
        }
        return  resul;
    }

    @Override
    public E findFirst(Class<E> entityType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return findFirst(entityType, "");
    }

    @Override
    public E findFirst(Class<E> entityType, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = this.getTableName(entityType);

        String sql = String.format("Select * from %s %s limit 1",
                tableName, where.equals("") ? "" : "WHERE " + where);

        ResultSet resultSet = this.connection.prepareStatement(sql).executeQuery();

        return this.createEntity(entityType, resultSet);
    }

    private E createEntity(Class<E> entityType, ResultSet resultSet) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!resultSet.next()) {
            return null;
        }

        E entity = entityType.getDeclaredConstructor().newInstance();

        Field[] declaredFields = entityType.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(Column.class) &&
                    !declaredField.isAnnotationPresent(Id.class)) {
                continue;
            }

            Column columnAnnotation = declaredField.getAnnotation(Column.class);

            String fieldName = columnAnnotation == null ? declaredField.getName() :
                    columnAnnotation.name();

            String value = resultSet.getString(fieldName);
            entity = this.fillData(entity, declaredField, value);

        }
        return entity;
    }

    private E fillData(E entity, Field field, String value) throws IllegalAccessException {
        field.setAccessible(true);
        if (field.getType() == long.class || field.getType() == Long.class) {
            field.setLong(entity, Long.parseLong(value));
        }else if (field.getType() == int.class || field.getType() == Integer.class) {
            field.setInt(entity, Integer.parseInt(value));
        } else if (field.getType() == LocalDate.class) {
            field.set(entity, LocalDate.parse(value));
        } else if (field.getType() == String.class) {
            field.set(entity, value);
        } else {
            throw new ORMException("Unsupported type " + field.getType());
        }
        return entity;
    }

    private String getTableName(Class<?> clazz) {
        Entity annotation = clazz.getAnnotation(Entity.class);
        if (annotation == null) {
            throw new ORMException("Provided class does not have Entity annotation");
        }
        return annotation.name();
    }

    private String getDBFieldsWithoutIdentity(E entity) {
        return Arrays.stream(
                        entity.getClass().getDeclaredFields())
                .filter(f -> f.getAnnotation(Column.class) != null)
                .map(f -> f.getAnnotation(Column.class).name())
                .collect(Collectors.joining(","));
    }

    private String getValuesWithoutIdentity(E entity) throws IllegalAccessException {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        List<String> result = new ArrayList<>();

        for (Field declaredField : declaredFields) {
            if (declaredField.getAnnotation(Column.class) == null) {
                continue;
            }

            declaredField.setAccessible(true);
            Object value = declaredField.get(entity);
            result.add("\"" + value.toString() + "\"");
        }
        return String.join(",", result);
    }

    private String getColumnName(Field field) {
        //for each field get its column
        String columnName = field.getAnnotation(Column.class).name();

        if (columnName.isEmpty()) {
            columnName = field.getName();
        }

        return columnName;
    }

}
