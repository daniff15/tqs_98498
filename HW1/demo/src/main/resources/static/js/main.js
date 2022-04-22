var valueDate = document.getElementById("input").value;
var today = new Date();
var newToday = new Date(today.getFullYear(), today.getMonth(), today.getDate());

if (valueDate == "") {
  document.getElementById("btn").disabled = true;
}

document.getElementById("input").addEventListener("change", (e) => {
  //Check for the input's value
  if (e.target.value == "") {
    document.getElementById("btn").disabled = true;
  } else {
    var varDate = new Date(e.target.value);
    if (varDate >= newToday) {
      document.getElementById("message").hidden = false;
      document.getElementById("btn").disabled = true;
    } else {
      document.getElementById("message").hidden = true;
      document.getElementById("btn").disabled = false;
    }
  }
});
