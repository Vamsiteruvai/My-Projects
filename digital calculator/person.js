function appendToresult(value) {
    document.querySelector("#text").value+=value;
}
function clearDisplay() {
    document.querySelector("#text").value='';
}
function deletedata() {
    var display=document.querySelector("#text").value;
    document.querySelector("#text").value=display.slice(0,-1);
     
}
function calculate() {
    var dis=document.querySelector("#text");
    var res=eval(dis.value); 
    document.querySelector("#text").value=res;    
}
 
