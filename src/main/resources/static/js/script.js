//var itemValue = document.getElementById('item');
//console.log(document.getElementById('item'));
//if (itemValue === null) {
//    document.getElementById('items').style.display = 'none';
//} else {
//    document.getElementById('items').style.display = 'block';
//}

//window.setTimeout(() => {
//    document.getElementById('msgItem').style.display = 'none'
//  }, 2000)
//window.setTimeout(() => {
//    document.getElementById('msgGate').style.display = 'none'
//  }, 2000)



var msgItem = document.getElementById('msgItem').textContent;
console.log(msgItem);
if (msgItem === null || msgItem === "") {
    document.getElementById('msgItem').parentElement.style.display = 'none';
} else {
    document.getElementById('msgItem').parentElement.style.display = 'block';
}

var msgGate = document.getElementById('msgGate').textContent;
console.log(msgGate);
if (msgGate === null || msgGate === "") {
    document.getElementById('msgGate').parentElement.style.display = 'none';
} else {
    document.getElementById('msgGate').parentElement.style.display = 'block';
}



var tags = document.getElementsByTagName("button");
var searchText = ["KEYS", "FOOD", "BOTTLE", "LAMP", "CAGE", "BIRD", "ROD", "GOLD", "COINS", "DIAMONDS", "JEWELRY", "SILVER"];
var injectedHTML = ['<img src="img/Key_04.png" class="itembutton"/>', '<img src="img/lamp.png" class="itembutton"/>', '<img src="img/food.png" class="itembutton"/>', '<img src="img/bootle.png" class="itembutton"/>'];

for (var j = 0; j < searchText.length; j++) {
    for (var i = 0; i < tags.length; i++) {
        if (tags[i].textContent === searchText[j]) {
            tags[i].innerHTML = '<img src="img/' + searchText[j] + '.png" class="itembutton"/>'
        }
    }
}

