package com.example.GEEN.Controller;

import com.example.GEEN.GeenApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional //测试后数据会回滚
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeenApplication.class)
public class accountTest {
    @Autowired
    private AccountController accountController;
    @Autowired
    private UserInfoController userInfoController;

    private MockMvc mockMvc;

    private MockMvc mockMvc2;
    @Before
    public void setup() {
        // 初始化构建
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
        mockMvc2 = MockMvcBuilders.standaloneSetup(userInfoController).build();
    }
    @Test
    public void loginSuccessfully() throws Exception {
        String responseString = mockMvc.perform(   //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
                get("/api/v1/login")  //模拟请求的url,及请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("accountName","736941882@qq.com")
                        .param("password","123456"))
                .andExpect(
                        status().isOk()   //预期返回的状态码是200
                )
                .andReturn().getResponse().getContentAsString();  //将相应的数据转换为字符串
        System.out.println("登录测试用例1--------返回的数据 = " + responseString);
    }
    @Test
    public void loginWithWrongPassword() throws Exception {
        String responseString = mockMvc.perform(   //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
                get("/api/v1/login")  //模拟请求的url,及请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("accountName","736941882@qq.com")
                        .param("password","1"))
                .andExpect(
                        status().isOk()   //预期返回的状态码是200
                )
                .andReturn().getResponse().getContentAsString();  //将相应的数据转换为字符串
        System.out.println("登录测试用例2--------返回的数据 = " + responseString);
    }

    @Test
    public void loginWithWrongAccount() throws Exception {
        String responseString = mockMvc.perform(   //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
                get("/api/v1/login")  //模拟请求的url,及请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("accountName","736941882")
                        .param("password","123456"))
                .andExpect(
                        status().isOk()   //预期返回的状态码是200
                )
                .andReturn().getResponse().getContentAsString();  //将相应的数据转换为字符串
        System.out.println("登录测试用例3--------返回的数据 = " + responseString);
    }
    @Test
    public void registerSuccessfully() throws Exception {
        String responseString = mockMvc.perform(   //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
                get("/api/v1/register")  //模拟请求的url,及请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("accountName","test@qq.com")
                        .param("password","123456"))
                .andExpect(
                        status().isOk()   //预期返回的状态码是200
                )
                .andReturn().getResponse().getContentAsString();  //将相应的数据转换为字符串
        System.out.println("注册测试用例1--------返回的数据 = " + responseString);
    }
    @Test
    public void registerWithAccountExisted() throws Exception {
        String responseString = mockMvc.perform(   //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
                get("/api/v1/register")  //模拟请求的url,及请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("accountName","736941882@qq.com")
                        .param("password","123456"))
                .andExpect(
                        status().isOk()   //预期返回的状态码是200
                )
                .andReturn().getResponse().getContentAsString();  //将相应的数据转换为字符串
        System.out.println("注册测试用例2--------返回的数据 = " + responseString);
    }
    @Test
    public void registerWithWrongAccount() throws Exception {
        String responseString = mockMvc.perform(   //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
                get("/api/v1/register")  //模拟请求的url,及请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("accountName","736941882")
                        .param("password","123456"))
                .andExpect(
                        status().isOk()   //预期返回的状态码是200
                )
                .andReturn().getResponse().getContentAsString();  //将相应的数据转换为字符串
        System.out.println("注册测试用例3--------返回的数据 = " + responseString);
    }
    @Test
    public void validateSuccessfully() throws Exception {
        String responseString = mockMvc.perform(   //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
                get("/api/v1/verify")  //模拟请求的url,及请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("email","736941882@qq.com")
        )
                .andExpect(
                        status().isOk()   //预期返回的状态码是200
                )
                .andReturn().getResponse().getContentAsString();  //将相应的数据转换为字符串
        System.out.println("邮箱验证测试用例1--------返回的数据 = " + responseString);
    }
    @Test
    public void changePasswordSuccessfully() throws Exception {
        String responseString = mockMvc.perform(   //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
                get("/api/v1/changePassword")  //模拟请求的url,及请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("accountName","736941882@qq.com")
                .param("oldPassword","123456")
                .param("newPassword","123"))
                .andExpect(
                        status().isOk()   //预期返回的状态码是200
                )
                .andReturn().getResponse().getContentAsString();  //将相应的数据转换为字符串
        System.out.println("更改密码测试用例1--------返回的数据 = " + responseString);
    }
    @Test
    public void changePasswordWithWrongPassword() throws Exception {
        String responseString = mockMvc.perform(   //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
                get("/api/v1/changePassword")  //模拟请求的url,及请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("accountName","736941882@qq.com")
                        .param("oldPassword","12345")
                        .param("newPassword","123"))
                .andExpect(
                        status().isOk()   //预期返回的状态码是200
                )
                .andReturn().getResponse().getContentAsString();  //将相应的数据转换为字符串
        System.out.println("更改密码测试用例2--------返回的数据 = " + responseString);
    }
    @Test
    public void changePasswordWithAccountNotExisted() throws Exception {
        String responseString = mockMvc.perform(   //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
                get("/api/v1/changePassword")  //模拟请求的url,及请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("accountName","73694182@qq.com")
                        .param("oldPassword","12345")
                        .param("newPassword","123"))
                .andExpect(
                        status().isOk()   //预期返回的状态码是200
                )
                .andReturn().getResponse().getContentAsString();  //将相应的数据转换为字符串
        System.out.println("更改密码测试用例3--------返回的数据 = " + responseString);
    }

    @Test
    public void getInfoSuccessfully() throws Exception {
        String email = "761447951@qq.com";
        String responseString = mockMvc2.perform(   //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
                get("/api/v1/getInfo/"+email)  //模拟请求的url,及请求的方法是get
                        )
                .andExpect(
                        status().isOk()   //预期返回的状态码是200
                )
                .andReturn().getResponse().getContentAsString();  //将相应的数据转换为字符串
        System.out.println("获取用户信息测试用例1--------返回的数据 = " + responseString);
    }
    @Test
    public void getInfoWithAccountNotExisted() throws Exception {
        String responseString = mockMvc2.perform(   //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
                get("/api/v1/getInfo/1")  //模拟请求的url,及请求的方法是get
        )
                .andExpect(
                        status().isOk()   //预期返回的状态码是200
                )
                .andReturn().getResponse().getContentAsString();  //将相应的数据转换为字符串
        System.out.println("获取用户信息测试用例2--------返回的数据 = " + responseString);
    }
    @Test
    public void changeInfoSuccessfully() throws Exception {
        String responseString = mockMvc2.perform(   //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
                post("/api/v1/changeInfo")  //模拟请求的url,及请求的方法是post
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("accountName","736941882@qq.com")
                        .param("userName","12345")
                        .param("sex","男")
                        .param("age","18")
                        .param("weight","65")
                        .param("height","1.70")
                        .param("chestCircum","1")
                        .param("armCircum","30")
                        .param("hipCircum","0")
                        .param("waistline","0"))
                .andExpect(
                        status().isOk()   //预期返回的状态码是200
                )
                .andReturn().getResponse().getContentAsString();  //将相应的数据转换为字符串
        System.out.println("修改用户信息测试用例1--------返回的数据 = " + responseString);
    }
    @Test
    public void changeInfoWithInvalidAccount() throws Exception {
        String responseString = mockMvc2.perform(   //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
                post("/api/v1/changeInfo")  //模拟请求的url,及请求的方法是post
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("accountName","1")
                        .param("userName","12345")
                        .param("sex","男")
                        .param("age","18")
                        .param("weight","65")
                        .param("height","1.70")
                        .param("chestCircum","1")
                        .param("armCircum","30")
                        .param("hipCircum","0")
                        .param("waistline","0"))
                .andExpect(
                        status().isOk()   //预期返回的状态码是200
                )
                .andReturn().getResponse().getContentAsString();  //将相应的数据转换为字符串
        System.out.println("修改用户信息测试用例2--------返回的数据 = " + responseString);
    }
    @Test
    public void changeInfoWithWrongData() throws Exception {
        String responseString = mockMvc2.perform(   //执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
                post("/api/v1/changeInfo")  //模拟请求的url,及请求的方法是post
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("accountName","761447951@qq.com")
                        .param("userName","12345")
                        .param("sex","12")
                        .param("age","-1")
                        .param("weight","65")
                        .param("height","1.70")
                        .param("chestCircum","1")
                        .param("armCircum","30")
                        .param("hipCircum","0")
                        .param("waistline","0"))
                .andExpect(
                        status().isOk()   //预期返回的状态码是200
                )
                .andReturn().getResponse().getContentAsString();  //将相应的数据转换为字符串
        System.out.println("修改用户信息测试用例3--------返回的数据 = " + responseString);
    }
}
