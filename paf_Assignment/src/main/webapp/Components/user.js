$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validatebillForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 }
 // If valid------------------------
var type = ($("#hidbidSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "userAPI", 
 type : type, 
 data : $("#formuser").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onbillSaveComplete(response.responseText, status); 
 } 
 }); 
});
 
function onbillSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divbillsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hidbidSave").val(""); 
$("#formuser")[0].reset(); 
}

$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidbidSave").val($(this).data("UserID")); 
		 $("#U_Fname").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#U_Lname").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#Uemail").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#Uaddress").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#U_NIC").val($(this).closest("tr").find('td:eq(4)').text());
		 $("#U_gender").val($(this).closest("tr").find('td:eq(4)').text());
		 $("#U_DoB").val($(this).closest("tr").find('td:eq(4)').text());
		});
 
 $(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "userAPI", 
		 type : "DELETE", 
		 data : "UserID=" + $(this).data("UserID"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onbillDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
 
 
 function onbillDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divbillsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
//client model--------------------------------
function validatebillForm()
{
	// uid
	if ($("#UserID").val().trim() == "")
	{
	return "Insert user id.";
	}
	// first name
	if ($("#U_Fname").val().trim() == "")
	{
	return "Insert user first name.";
	}
	

// U_Lname-------------------------------
if ($("#U_Lname").val().trim() == ""){
		
		return "Insert last name.";
}
	// vat------------------------
	if ($("#Uemail").val().trim() == ""){
		
		return "Insert user email.";
	}
	// address-----------------------
	if ($("#Uaddress").val().trim() == ""){
		
		return "Insert user address.";
	}
	
	// U_NIC------------------------
	if ($("#U_NIC").val().trim() == ""){
		
		return "Insert user nic.";
	}
	// U_gender----------------------
	if ($("#U_gender").val().trim() == ""){
		
		return "Insert user User gender.";
	}
	
	// U_DoB----------------------
	if ($("#U_DoB").val().trim() == ""){
		
		return "Insert user User date of birth.";
	}

return true;
}

 
 