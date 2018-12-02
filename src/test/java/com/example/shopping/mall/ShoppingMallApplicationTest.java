package com.example.shopping.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.shopping.mall.model.ClassModel;
import com.example.shopping.mall.model.ProductModel;
import com.example.shopping.mall.repository.ClassRepository;
import com.example.shopping.mall.repository.ProductRepository;
import com.example.shopping.mall.service.IProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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

    @Autowired
    private ProductRepository productRepository;

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
        ProductModel productModel = new ProductModel();
        ObjectMapper mapper = new ObjectMapper();
        productModel.setType("A");
        try {
            String json = mapper.writeValueAsString(productModel);
            logger.info("*** Send JSON: {}", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPage() {
        logger.info("all[{}]", productRepository.findAll());
        PageHelper.startPage(0, 1, true);
        PageInfo<ProductModel> info = new PageInfo<>(productRepository.findAll());
        logger.info("PageHelper[{}]", info);
    }

    @Autowired
    IProductService iProductService;

    @Test
    public void testFindAll() {
        ProductModel productModel = new ProductModel();
        productModel.setType("A");
        System.out.println(" Test  ," + iProductService.findAll(productModel).toString());
    }
}
