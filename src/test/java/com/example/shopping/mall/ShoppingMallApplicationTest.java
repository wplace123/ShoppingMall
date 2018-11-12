package com.example.shopping.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.shopping.mall.model.ClassModel;
import com.example.shopping.mall.repository.ClassRepository;

/**
 * 
 * @author ryan wang
 * @date 2018/11/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingMallApplicationTest {

    Logger logger = LoggerFactory.getLogger(ShoppingMallApplicationTest.class);

    @Autowired
    private ClassRepository classRepository;

    @Test
    public void contextLoads() {}

    @Test
    public void testClassRepository() {

        // 創建一個
        ClassModel model = new ClassModel();
        model.setType("hu");
        classRepository.save(model);
        // 查詢全部
        logger.info(classRepository.findAll().toString());
        // 計算數量

    }

    // @Autowired
    // private OrdersService ordersService;
    // @Autowired
    // private OrdersRepository orderRepository;

    @Test
    public void testOerderService() {
        // ObjectMapper mapper = new ObjectMapper();
        // try {
        // String json = mapper.writeValueAsString(ordersService.getAll());
        // logger.info("*** Send JSON: {}", json);
        // } catch (JsonProcessingException e) {
        // e.printStackTrace();
        // }
    }

}
