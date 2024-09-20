document.addEventListener("DOMContentLoaded", function () {
    console.log("JS CARGADO");
    let roleToRemove=document.getElementById("selected-role-to-delete");
    let lastDisabled;

    let removeRoleButtons = document.getElementsByClassName("remove-role-class");

    for (let removeRoleButton of removeRoleButtons) {
        removeRoleButton.addEventListener("click", function () {
            roleToRemove.value = removeRoleButton.value;
            roleToRemove.textContent= removeRoleButton.value;
            
            if(lastDisabled){
                lastDisabled.disabled=false;
            }
            document.getElementById(roleToRemove.value+'option').disabled=true;
            lastDisabled= document.getElementById(roleToRemove.value+'option')

            document.getElementById('role-to-delete').value=roleToRemove.value;
            console.log(roleToRemove.value);
        });
    }
});
