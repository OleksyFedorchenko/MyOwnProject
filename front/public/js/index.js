 `let serverURL = "http://localhost:8080/";`

      function getClients(){
        $.ajax({
            url: serverURL + "clients",
            method: "GET",
            dataType: "json",
            contentType: "application/json",
            success: function(response){
                console.log(response);
                
                $.each(response, function(key, value){
                    
                    $("#clientsTable tbody").append(
                        `
                        <tr>
    
                        <td>${value.id}</td>    
                        <td>${value.firstName}</td>    
                        <td>${value.lastName}</td>    
                        <td>${value.phoneNumber}</td>    
                        <td>${value.birthday}</td>    
                        
                        </tr>
                        `
                    )
                });
            }
        });
    }

  function addClient(){
        let firstName = $("#inputFirstName").val();
        let lastName = $("#inputLastName").val();
        let phoneNumber = $("#inputPhoneNumber").val();
        let birthday = $("#birthday").val();
        let email = $("#inputEmail").val();
        let password = $("#inputPassword").val();
        
        
        let client = {
            email: email,
            password: password,
            clientEntity: {
            firstName: firstName,
                lastName: lastName,
                    phoneNumber: phoneNumber,
                        birthday: birthday,
                            }
                
        };
        
        
            
            $.ajax({
             
                url: serverUrl + "clients",
                method: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(client, clientLog),
                complete: function(data){
                    if(data.status == 201){
                        alert("Client Added");
//                        $("#clientsTable tbody").empty();
//                        getClients();
//                        $("#register")[0].reset();
                    }
                }
            });
    }