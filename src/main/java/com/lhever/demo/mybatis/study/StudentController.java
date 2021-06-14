package com.lhever.demo.mybatis.study;

import com.lhever.demo.mybatis.study.dao.StudentDao;
import com.lhever.demo.mybatis.study.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2019/12/21 12:32
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2019/12/21 12:32
 * @modify by reason:{方法名}:{原因}
 */
@RestController
@RequestMapping("student")
public class StudentController {


    @Autowired
    private StudentDao studentDao;


    @ResponseBody
    @GetMapping("getBy")
    public Student getBy(Integer id) {
        return studentDao.findStudentById(id);
    }

    @ResponseBody
    @GetMapping("getBy0")
    public Student getBy0(Integer id) {
        return studentDao.findStudentById_(id);
    }






}
