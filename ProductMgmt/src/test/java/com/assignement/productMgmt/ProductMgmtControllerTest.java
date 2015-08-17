package com.assignement.productMgmt;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.assignement.productMgmt.model.Category;
import com.assignement.productMgmt.model.Department;
import com.assignement.productMgmt.model.Product;
import com.assignement.productMgmt.model.ProductModel;
import com.assignement.productMgmt.service.CategoryService;
import com.assignement.productMgmt.service.DepartmentService;
import com.assignement.productMgmt.service.ProductService;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ RequestContextUtils.class })
public class ProductMgmtControllerTest extends TestCase {

    @Mock
    private ProductService productService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private Model model;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private ProductMgmtController controller;

    @Test
    public void testAddProduct() throws Exception {
        MockitoAnnotations.initMocks(this);

        ProductModel productModel = new ProductModel();
        productModel.setName("sample");
        productModel.setAvailable(true);
        productModel.setCategoryId(1L);
        productModel.setDepartmentId(1L);
        productModel.setDesc("sample");
        productModel.setPrice(100L);

        Category category = new Category();

        Mockito.when(categoryService.findById(1L)).thenReturn(category);

        String redirct = controller.addProduct(productModel);
        Assert.assertNotNull(redirct);
        assertEquals("redirect:" + "/index", redirct);

    }

    @Test
    public void testEditProduct() throws Exception {

        Category cat = new Category();
        cat.setId(1L);

        Department dep = new Department();
        dep.setId(1L);

        Product product = new Product();
        product.setId(1L);
        product.setName("sample");
        product.setAvailable(true);
        product.setCategory(cat);
        product.setDepartment(dep);
        product.setDescription("sample");
        product.setPrice(100L);

        List<Department> depList = new ArrayList<Department>();
        depList.add(dep);
        List<Category> catList = new ArrayList<Category>();
        catList.add(cat);

        Mockito.when(productService.findById(1L)).thenReturn(product);
        Mockito.when(departmentService.getAllRows()).thenReturn(depList);
        Mockito.when(categoryService.getAllRows()).thenReturn(catList);

        String redirct = controller.editProduct(1, model);

        Assert.assertNotNull(redirct);
        assertEquals("list_products", redirct);

    }

    @Test
    public void testRemoveProduct() throws Exception {

        Product product = new Product();
        product.setId(1L);

        Mockito.when(productService.findById(1L)).thenReturn(product);

        String redirect = controller.removeProduct(1);
        assertEquals("redirect:" + "/index", redirect);

    }

    public Product setProduct(ProductModel productModel) {
        Product product = new Product();
        if (productModel.getId() > 0) {

            product.setId(productModel.getId());
        }

        product.setAvailable(productModel.getAvailable());
        product.setCategory(this.categoryService.findById(productModel.getCategoryId()));
        product.setPrice(productModel.getPrice());
        product.setDepartment(this.departmentService.findById(1L));
        product.setDescription(productModel.getDesc());
        product.setName(productModel.getName());

        return product;

    }

}