package src.com.atguigu.myssm.basedao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> {
    public final String url="jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
    public final String user="root";
    public final String pwd="123456";
    public final String DRIVER="com.mysql.jdbc.Driver";

    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet rs;
    //T的Class对象
    private Class entityClass;

    public BaseDAO(){
        //getClass()获取Class对象,当前我们执行new FRuitdaoimpl,创建的是
        //那么子类构造反对调用父类无参构造方法
        //所以getClass先执行.获取FruitDaoipm的class
        //getGenericSuperclass获取的是basedao的class
        Type genericType =getClass().getGenericSuperclass();
        //参数化类型
        Type[] actualTypeArguments=((ParameterizedType) genericType).getActualTypeArguments();
        Type actualType =actualTypeArguments[0];
       // System.out.println(actualType.getTypeName());
        try {
            entityClass=Class.forName(actualType.getTypeName());
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    protected Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(url,user,pwd);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    protected void close(ResultSet rs,PreparedStatement preparedStatement,Connection connection){
        try {
            if (rs!=null){
                rs.close();
            }
            if (preparedStatement!=null){
                preparedStatement.close();
            }
            if (connection!=null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    //给预处理命令对象设置参数
    private void setParams(PreparedStatement preparedStatement,Object... parames) throws SQLException {
        if (parames!=null&&parames.length>0){
            for (int i = 0; i < parames.length; i++) {
                preparedStatement.setObject(i+1,parames[i]);
            }
        }
    }

    //执行更新,返回影响行数
    protected int executeUpdate(String sql,Object... params){
        boolean insertFlag=false;
        insertFlag=sql.trim().toUpperCase().startsWith("INSERT");
        try {
            connection=getConnection();
            if (insertFlag){
                preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            }else {
                preparedStatement = connection.prepareStatement(sql);
            }
            setParams(preparedStatement,params);
            int count=preparedStatement.executeUpdate();

            if (insertFlag){
                rs = preparedStatement.getGeneratedKeys();
                if (rs.next()){
                    return ((Long)rs.getLong(1)).intValue();
                }
            }else {
                preparedStatement = connection.prepareStatement(sql);
            }
            return count;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs,preparedStatement,connection);
        }
        return 0;
    }


    //通过反射技术给obj对象property属性赋property值
    private void setValue(Object obj,String property,Object propertyValue){
        Class clazz=obj.getClass();
        try {
            //获取property这个字符串对于的属性名比如"fid"去找obj对象中fid属性
            Field field=clazz.getDeclaredField(property);
            if (field!=null){
                field.setAccessible(true);
                field.set(obj,propertyValue);
            }
        }catch (NoSuchFieldException |IllegalAccessException e){
            e.printStackTrace();
        }
    }

    //执行查询,返回List
    protected List<T> executeQuery(String sql,Object... params)  {
        List<T> list=new ArrayList<>();
        try {
            connection=getConnection();
            preparedStatement=connection.prepareStatement(sql);
            setParams(preparedStatement,params);
            rs=preparedStatement.executeQuery();
            //通过Rs可以获取结果的元数据
           ResultSetMetaData resultSetMetaData= rs.getMetaData();
           int columnCount=resultSetMetaData.getColumnCount();
           while (rs.next()){
               T entity=(T) entityClass.newInstance();

               for (int i = 0; i < columnCount; i++) {
                   String columnName=resultSetMetaData.getColumnName(i+1);
                   Object columnValue=rs.getObject(i+1);
                   setValue(entity,columnName,columnValue);
               }

                list.add(entity);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }
        return list;
    }

    //执行查询,返回单个实体对象
    protected T load(String sql,Object... params)  {
        try {
            connection=getConnection();
            preparedStatement=connection.prepareStatement(sql);
            setParams(preparedStatement,params);
            rs=preparedStatement.executeQuery();
            //通过Rs可以获取结果的元数据
            ResultSetMetaData resultSetMetaData= rs.getMetaData();
            int columnCount=resultSetMetaData.getColumnCount();
            if (rs.next()){
                T entity=(T) entityClass.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName=resultSetMetaData.getColumnName(i+1);
                    Object columnValue=rs.getObject(i+1);
                    setValue(entity,columnName,columnValue);
                }

                return entity;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }
        return null;
    }

    //执行复杂查询,返回统计结果
    protected Object[] executeComplexQuery(String sql,Object... params)  {
        try {
            connection=getConnection();
            preparedStatement=connection.prepareStatement(sql);
            setParams(preparedStatement,params);
            rs=preparedStatement.executeQuery();
            //通过Rs可以获取结果的元数据
            ResultSetMetaData resultSetMetaData= rs.getMetaData();
            int columnCount=resultSetMetaData.getColumnCount();
            Object[] columnValueArr=new Object[columnCount];
            if (rs.next()){
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue=rs.getObject(i+1);
                    columnValueArr[i]=columnValue;
                }

                return columnValueArr;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
