package com.clq.mybatisp;

/*
 *@author:C1q
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clq.mybatisp.mapper.UserMapper;
import com.clq.mybatisp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelect(){
        //查询tb_user记录
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
    @Test
    public void test1(){
        //查询tb_user记录
        User user = userMapper.findById(1L);
        System.out.println(user);
    }


    //插入
    @Test
    public void testInsert(){
        User user = new User();
//        user.setId(100L);
        user.setName("曹操");
        user.setPassword("111111");
        user.setAge(20);
        user.setUserName("caocao");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse("1990-01-01 00:00:00", dateTimeFormatter);
        user.setBirthday(localDateTime);
        int i = userMapper.insert(user);
        System.out.println(i);
    }

    //==================================================
    @Test
    public void testUpdate(){
        User user = new User();
        //更新记录的主键值
        user.setId(5L);
        user.setAge(100);//要更新的值
        user.setPassword("12222");//要更新的值
        //只将对象中不为NULL的值更新到数据库中
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    //根据条件进行更新
    @Test
    public void testUpdate2(){
        User user = new User();
        user.setAge(100);//要更新的值
        user.setPassword("12222");//要更新的值
        //设置条件
        QueryWrapper<User> queryWrapper  =new QueryWrapper<>();
        queryWrapper.eq("name","曹操");
        //只将对象中不为NULL的值更新到数据库中
        int i = userMapper.update(user,queryWrapper);
        System.out.println(i);
    }

    //根据条件进行更新，可以将为NULl的值更新到数据库
    @Test
    public void testUpdate3(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","曹操").set("birthday",null);
        //只将对象中不为NULL的值更新到数据库中
        int i = userMapper.update(null,updateWrapper);
        System.out.println(i);
    }
    //==========================================
    @Test
    public void testDelete(){
        //设置条件
//        QueryWrapper<User> queryWrapper  =new QueryWrapper<>();
//        queryWrapper.eq("age",100);
//        queryWrapper.eq("name","曹操1");

        User user = new User();
        user.setAge(100);
        user.setName("曹操1");
        QueryWrapper<User> queryWrapper  =new QueryWrapper<>(user);
        //根据条件删除
        int delete = userMapper.delete(queryWrapper);
        System.out.println(delete);
    }

    //批量删除
    @Test
    public void testDelete2(){

        //根据条件删除
        int delete = userMapper.deleteBatchIds(Arrays.asList(101L,102L,103L));
        System.out.println(delete);
    }

    //==================================
    @Test
    public void testSelectOne(){

        QueryWrapper<User> queryWrapper  =new QueryWrapper<>();
        queryWrapper.eq("name","张三");
        //根据条件查询，只能查询出一条记录，否则报错
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectCount(){

        QueryWrapper<User> queryWrapper  =new QueryWrapper<>();
//        queryWrapper.eq("name","张三");//等于
        queryWrapper.gt("age",20);//大于?
        //根据条件查询，只能查询出一条记录，否则报错
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println(count);
    }

    @Test
    public void testSelectPage(){

        //条件
        QueryWrapper<User> queryWrapper  =new QueryWrapper<>();
//        queryWrapper.eq("name","张三");//等于
        queryWrapper.gt("age",20);//大于?
        //用构造方法设置当前页码，每页记录数
        int pageIndex =  2;//当前页码
        int size = 3;//每页记录数
        Page<User> page =new Page<>(pageIndex,size);

        IPage<User> userIPage = userMapper.selectPage(page, queryWrapper);
        long pages = userIPage.getPages();//总页数
        long total = userIPage.getTotal();//总记录数
        //记录列表
        List<User> records = userIPage.getRecords();
        System.out.println(records);

    }

    //===========================================
    @Test
    public void testEq() {
        //条件
        QueryWrapper<User> queryWrapper  =new QueryWrapper<>();
        queryWrapper.eq("name","曹操");//等于
        queryWrapper.gt("age",20);//大于?
        queryWrapper.in("user_name","caocao");

        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    @Test
    public void testEq2() {
        //条件
        LambdaQueryWrapper<User> queryWrapper  =new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName,"曹操");//等于
        queryWrapper.gt(User::getAge,20);//大于?
        queryWrapper.in(User::getUserName,"caocao");

        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    @Test
    public void testEq3() {
        //条件
        LambdaQueryWrapper<User> queryWrapper  =new LambdaQueryWrapper<>();
        String name = null;
        Integer age = 20;
        queryWrapper.eq(name!=null && !name.equals(""),User::getName,name);//等于
        queryWrapper.gt(age!=null,User::getAge,age);//大于?

        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }


    @Test
    public void testWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //SELECT id,user_name,password,name,age,email FROM tb_user WHERE name LIKE ?
        //Parameters: %曹%(String)
        wrapper.likeRight("name", "曹");
        wrapper.select("id","name");

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }

    }



}