package com.assignement.productMgmt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assignement.productMgmt.model.Category;
import com.assignement.productMgmt.model.Product;
import com.assignement.productMgmt.model.ProductModel;
import com.assignement.productMgmt.service.CategoryService;
import com.assignement.productMgmt.service.DepartmentService;
import com.assignement.productMgmt.service.ProductService;
/**
 * The controllers implement the methods for the Product CRUD operations
 * 
 * @author Mushtaq Ahmed
 * @version     1.0
 * @since       2015-08-15
 * 
 * */
@Controller
public class ProductMgmtController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductMgmtController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private DepartmentService departmentService;
	
	private String responseMsg="";
	
/**
 * This methods is the request Mapping method of list Products
 * @param model
 * @return viewpageName  String
 */
	 @RequestMapping(value = "/index", method = RequestMethod.GET)
	    public String listProducts(Model model) {
		
		 logger.debug(" Enter in listProducts method ");
		 try{
		 	ProductModel productModel= new ProductModel();
		 	productModel.setDepartmentlist(departmentService.getAllRows());
		 	productModel.setCategoriesList(categoryService.getAllRows());
	        model.addAttribute("product",productModel);
	        model.addAttribute("listProducts", this.productService.getAllRows());
	        if(responseMsg!=null && ! responseMsg.isEmpty()){
	        	model.addAttribute("responseMsg",responseMsg);
	        	responseMsg="";
	        }
		 }catch(Exception e){
			 logger.error("This is Error message",e);
		 }
	        return "list_products";
	    }
	  
	 /**
	  * To Add product in the Database
	  * @param ModelObject p
	  * @return viewpageName  String
	  */
	    
	    @RequestMapping(value= "/product/add", method = RequestMethod.POST)
	    public String addProduct(@ModelAttribute("product") ProductModel p){
	    	 
	    	 logger.debug(" Enter in addProduct  method ");
	         Product productEntity = setProduct(p);
	        if(p.getId() == 0){
	            /**Adding New Product  **/
	        	 responseMsg="Product added Successfully";
	            this.productService.save(productEntity);
	        }else{
	        	
	        	   /**Editing already existing  Product  **/
	        	 responseMsg="Product updated Successfully";
	            this.productService.updateOrSave(productEntity);
	        }
	      
	        return "redirect:/index";
	         
	    }
	     
	    /**
	     * This method returns the list of Categories to the view page
	     * @param departmentId
	     * @return CategoriesMap Map
	     */
	    @RequestMapping(value= "/categories", method=RequestMethod.GET)
	    public @ResponseBody String getCategories(@RequestParam("departmentId") long departmentId){
	    	
	    	 String jsonresponse="";
	    	try {
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	 logger.debug(" Enter in getCategories method ");
	    	
	         Map<Long ,String> categoriesMap= new HashMap<Long, String>();
	      Set<Category> categoriesList= this.departmentService.findById(departmentId).getCategoriesLists();
	        for(Category category:categoriesList){
	        	categoriesMap.put(category.getId(), category.getName());
	        }
	       
				jsonresponse = objectMapper.writeValueAsString(categoriesMap);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				logger.error(" Error in getCategories" ,e);
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				logger.error(" Error in getCategories" ,e);
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error(" Error in getCategories" ,e);
				e.printStackTrace();
			}
	        
			return jsonresponse;
	    }
	    
	    /**
	     * This method deletes the product
	     * @param id
	     * @return viewpageName  String
	     */
	    @RequestMapping("/remove/{id}")
	    public String removeProduct(@PathVariable("id") int id){
	    	 
	    	 logger.debug(" Enter in removeProduct method ");
	        this.productService.delete(this.productService.findById(Long.valueOf(id)));
	        responseMsg="Product Deleted Successfully";
	          return "redirect:/index";
	    }
	  
	    /**
	     * To Edit the Product details
	     * @param id
	     * @param model
	     * @return viewpageName  String
	     */
	    @RequestMapping("/edit/{id}")
	    public String editProduct(@PathVariable("id") int id, Model model){
	
	    	 logger.debug(" Enter in editProduct method ");
	    	 ProductModel productModel=new ProductModel();
	    	 if(this.productService.findById(Long.valueOf(id))!=null){
	    	 productModel= getProductModel(this.productService.findById(Long.valueOf(id)));
	    	 }else{
	    		 
	    		 productModel.setDepartmentlist(departmentService.getAllRows());
				 productModel.setCategoriesList(categoryService.getAllRows());
	    	 }
			 	
	        model.addAttribute("product",productModel );
	        model.addAttribute("listProducts", this.productService.getAllRows());
	        return "list_products";
	    }
	    
	    /**
	     * Supporting method to populate ProductModel from Product
	     * @param product
	     * @return ProductModelObject ProdctModel
	     */
	        private ProductModel getProductModel(Product product){
	    		ProductModel productModel= new ProductModel();
	    		productModel.setId(product.getId());
	    		productModel.setAvailable(product.getAvailable());
	    		productModel.setDesc(product.getDescription());
	    		productModel.setName(product.getName());
	    		productModel.setPrice(product.getPrice());
	    		productModel.setCategoryId(product.getCategory().getId());
	    		productModel.setDepartmentId(product.getDepartment().getId());
	    		productModel.setDepartmentlist(departmentService.getAllRows());
			 	productModel.setCategoriesList(categoryService.getAllRows());
			 	
			return productModel;
	    	
	    }
	        /**
	         * To redirect to login page on application startup
	         * @param error
	         * @param logout
	         * @param model
	         * @return viewpageName String
	         */
	        @RequestMapping(value = "/login", method = RequestMethod.GET)
	    	public String login(@RequestParam(value = "error", required = false) String error,
	    			@RequestParam(value = "logout", required = false) String logout,Model model) {

	    		if (error != null) {
	    			 model.addAttribute("error", "Invalid username and password!");
	    		}
	    		if (logout != null) {
	    			model.addAttribute("msg", "You've been logged out successfully.");
	    		}
	    		
	    		return "login";

	    	}
	    /**
	     * To Populate the Porduct Entity object
	     * @param productModel
	     * @return Productobject Product
	     */
	    public Product setProduct(ProductModel productModel){
	    	Product product= new Product();
	    	if(productModel.getId()>0){
	    		
	    		product.setId(productModel.getId());
	    	}
	 
	    	product.setAvailable(productModel.getAvailable());
	    	product.setCategory(this.categoryService.findById(productModel.getCategoryId()));
	    	product.setPrice(productModel.getPrice());
	    	product.setDepartment(product.getCategory().getDepartment());
	    	product.setDescription(productModel.getDesc());
	    	product.setName(productModel.getName());
	    	
	    	
			return product;
	    	
	    }
	     
}
