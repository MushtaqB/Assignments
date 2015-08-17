<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>
    <title>Product Management Page</title>
    <style type="text/css">
       	.responsemsg {padding: 15px;margin-bottom: 20px;border: 1px solid transparent;border-radius: 4px;color: #31708f;background-color: #d9edf7;border-color: #bce8f1;}
       	.error { color: red; font-weight: bold;}
    </style>
</head>
<body>
<div>
<div align="left">
<h1>
    Product Management
</h1>
</div>
<div align="right">
<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>
</div>
</div>
 
<c:url var="addProduct" value="/product/add" ></c:url>


 <script type="text/javascript">
	
 $(document).ready( function () {
	    $('#productList_table').DataTable();
	} );
 
	function getcategories(){
		
		var dept_id = $('#department_id_select').val();
		 $.ajax({
	         type: "GET",
	         url: "/ProductMgmt/categories",
	         data : {
					departmentId:dept_id
					},
					success : function(response) {						
						if( response!='null'&& response!="" ){
							var obj =$.parseJSON(response);
							var ddl = document.getElementById("cateogry_id_select");
							removeOptionSelected(ddl);	
							var values = new Array();	
							for(var i in obj) {
				                values.push({ key:i, value: obj[i] });
				           }
							for(var k in values) {
								   var category = values[k];
								    var theOption = new Option;
								     theOption.text = category.value;
								     theOption.value = category.key;
							     ddl.add(theOption);
							}
							}
					},
							error: function(e){
								 console.log(e);
								//alert('Error: ' + e);
							}
						});
	}
	
	function removeOptionSelected(elSel){
		  var i;
		  for (i = elSel.length - 1; i>=0; i--) {
			    if (elSel.options[i].value!="-1") {
				      elSel.remove(i);
		    	}
		  }
		};


</script>
<form:form action="${addProduct}" commandName="product">
 <c:if test="${not empty responseMsg}">
 <div class="responsemsg">${responseMsg}</div>
</c:if>
<table>
    <c:if test="${(product.id gt 0)}">
    <tr>
        <td>
            <form:label path="id">
              ID
            </form:label>
        </td>
        <td>
            <form:input path="id" readonly="true"   disabled="true" />
            <form:hidden path="id" />
        </td> 
    </tr>
    </c:if>
    <tr>
        <td>
            <form:label path="name">
       		  Product Name
            </form:label>
        </td>
        <td>
            <form:input path="name" />
        </td> 
      
        <td><form:errors path="name" cssClass="error"/></td>
        
    </tr>
      <tr>
        <td>
            <form:label path="desc">
               Desc
            </form:label>
        </td>
        <td>
            <form:input path="desc" />
        </td> 
    </tr>
  
     <tr>
        <td>
            <form:label path="departmentId">
              Department
            </form:label>
        </td>
        <td>
            <form:select id="department_id_select" path="departmentId" onchange="getcategories()" >
		   <form:option value="0" label=" Select "/>
		   <c:forEach items="${product.departmentlist}" var="department">
        		<form:option value="${department.id}" label="${department.name}" />
    		</c:forEach>
    		</form:select>
        </td>
    </tr>
      <tr>
        <td>
            <form:label path="categoryId">
               Category
            </form:label>
        </td>
        <td>
            <form:select id="cateogry_id_select" path="categoryId">
		   <form:option value="0" label=" Select "/>
		   <c:forEach items="${product.categoriesList}" var="category">
        		<form:option value="${category.id}" label="${category.name}"/>
    		</c:forEach>
    		</form:select>
        </td>
    </tr>
      <tr>
        <td>
            <form:label path="price">
                Price
            </form:label>
        </td>
        <td>
            <form:input path="price" />
        </td>
    </tr>
      <tr>
        <td>
            <form:label path="available"> 
              Available
            </form:label>
        </td>
        <td>
            <form:select  path="available">
		   <form:option value="0" label=" Select "/>
		   <form:options items="${product.availableMap}"/>
		   </form:select>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <c:if test="${(product.id gt 0)}">
                <input type="submit"
                    value="<spring:message text="Edit Product"/>" />
            </c:if>
            <c:if test="${!(product.id gt 0)}">
                <input type="submit"
                    value="<spring:message text="Add Product"/>" />
            </c:if>
        </td>
    </tr>
</table>  
</form:form>
<br>

<h3>Products List</h3>
<c:if test="${!empty listProducts}">
    <table id="productList_table" class="display">
    <thead>
    <tr>
        <th width="80">Product ID</th>
        <th width="120">Product Name</th>
        <th width="120">Product Description</th>
        <th width="120">Product Department</th>
        <th width="120">Product Category</th>
        <th width="120">Product price</th>
        <th width="120">Available</th>
        <th width="120">Location</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
     </thead>
     <tbody>
    <c:forEach items="${listProducts}" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.department.name}</td>
            <td>${product.category.name}</td>
            <td>${product.price}</td>
            <td>${product.available}</td>
            <td>${product.department.locationId}-${product.category.aisleId}</td>
            <td><a href="<c:url value='/edit/${product.id}' />" >Edit</a></td>
            <td><a href="<c:url value='/remove/${product.id}' />" >Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
    </table>
</c:if>
</body>
</html>