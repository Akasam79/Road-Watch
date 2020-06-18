function link(role, page){
    //alert("Role: "+role+" Page: "+page);
    if(page==="watchboard.jsp"){
        window.location.href = page;
    }else if(role==='admin'){
        window.location.href = page;
    }else if(role===page.substr(0, (page.length-4))){
        window.location.href = page;
    }else{
        alert("Access Denied.");
    }
}

function logout(){
    window.location.href = "logout.jsp";
}

