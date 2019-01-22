var itemValue = document.getElementById('item');
console.log(itemValue);
if (itemValue === null) {
    document.getElementById('items').style.display = 'none';
} else {
    document.getElementById('items').style.display = 'block';
}

window.setTimeout(() => {
    document.getElementById('msgItem').style.display = 'none'
  }, 2000)
window.setTimeout(() => {
    document.getElementById('msgGate').style.display = 'none'
  }, 2000)