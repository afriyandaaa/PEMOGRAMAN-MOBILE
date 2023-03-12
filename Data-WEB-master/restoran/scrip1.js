
//rawon, soto betawi, esteh,krupuk, telur asin-->
let totalHargaMinuman = 0;
let dring = [
    {
        name:`Ice Blend`,
        stok: 2,
        harga: 30000,
        image: './assets/images/Ice Blend.jpg'
    },
    {
        name:`Ice Drink`,
        stok:8,
        harga: 17000,
        image: './assets/images/Ice Drink.jpg'
    },
    {
        name:`Fruit Tea`,
        stok: 12,
        harga: 15500,
        image: './assets/images/Fruit Tea2.jpg'
    },
    {
        name:`Leci`,
        stok: 12,
        harga: 15500,
        image: './assets/images/leci.jpg'
    },
    {
        name:`Flavoured Soda`,
        stok: 20,
        harga: 22000,
        image: './assets/images/FlavouredSoda.jpg'
        
    },
    {
        name:`Milk Tea`,
        stok: 25,
        harga: 20000,
        image: './assets/images/Milk Tea.jpg'

    },
    {
        name:`es kopi susu`,
        stok: 15,
        harga: 20000,
        image: './assets/images/es kopi susu.jpg'

    },
    {
        name:`tiramisu`,
        stok: 27,
        harga: 30000,
        image: './assets/images/tiramisu.jpg'

    },
    {
        name:`Latte`,
        stok: 20,
        harga: 25000,
        image: './assets/images/late.jpg'

    },
    {
        name:`Vanilla Latte`,
        stok: 15,
        harga: 28000,
        image: './assets/images/vanilla late.jpg'

    },
    {
        name:`Kopi tubruk`,
        stok: 55,
        harga: 15000,
        image: './assets/images/kopi tubruk.jpg'

    },
]

let cart = [

];

let pembelian =[

];

function debug(){
    console.log(pembelian); 
}

function checkAvailable(){
    var available = true;
    for(var i = 0; i<cart.length; i++){
        for(var j = 0; j<dring.length; j++){
            if(cart[i].name === dring[j].name){
                if(dring[j].stok < cart[i].jumlah){
                    available = false;
                    alert(`Stok ${dring[j].name} tinggal ${dring[j].stok}`);
                    break;
                }
            }  
        }
        if(!available){
            break;
        }
    }

    return available
}

function orderdring(){
    if(checkAvailable()){
        for(var x = 0; x<cart.length; x++){
            for(var y = 0; y<dring.length; y++){
                if(cart[x].name === dring[y].name){  
                        dring[y].stok -= cart[x].jumlah;
                }
            }
        }
        var cartList = document.getElementById('cartList');

        // UNTUK MATIKAN CARTLIST
        cartList.setAttribute('style','display:none');
        alert(`Pesanan telah diterima, Mohon menunggu, Total Harga : Rp${toRupiah(totalHargaMinuman)},00`);
        cart.push(totalHargaMinuman);
        pembelian.push(cart);
        totalHargaMinuman = 0;
        cart = [];
        generateData();    
    }
    console.log(pembelian); 
    console.log(dring);
}

function addtoCart(index) {
    console.log(dring[index].name);
    var hasExist = false;
    var hasEmpty = false;
    if(dring[index].stok <= 0){
        alert(`${dring[index].name} habis, silahkan pesan menu lainnya`);
        hasEmpty = true;
    }
    for(var i = 0; i<cart.length; i++){
        if(dring[index].name === cart[i].name){
            if(dring[index].stok - cart[i].jumlah <=0){
                alert(`${dring[index].name} habis, silahkan pesan menu lainnya`);
                hasEmpty = true;
                break;
            }else{
                totalHargaMinuman += cart[i].harga;
                //console.log(totalHargaMinuman);
                cart[i].jumlah ++;
                hasExist = true;
                break;
            }      
        }
    }
    if(!hasExist && !hasEmpty){
        let obj ={
            name: dring[index].name,
            harga: dring[index].harga,
            jumlah: 1,
            image: dring[index].image,
        }
        totalHargaMinuman +=dring[index].harga;
        cart.push(obj);
    }
    generateData();
    var cartlist = document.getElementById('cartList');
    if(cart.length !== 0){
        cartlist.setAttribute('style', 'display:inline-block');
    }
}

function removedring(value){
    
    //console.log(cart[value].jumlah);
    if(cart[value].jumlah > 0){
        totalHargaMinuman -=cart[value].harga;
        cart[value].jumlah--;
    }   
    if(cart[value].jumlah === 0){
        cart.splice(value,1);
        
    }
    generateData();
    var cartlist = document.getElementById('cartList');
    if(cart.length !== 0){
        cartlist.setAttribute('style', 'display:inline-block');
    }else{

        // UNTUK MATIKAN CARTLIST

        
        cartlist.setAttribute('style', 'display:none');
    }
}

function toRupiah(harga){
    var result = '';
    harga = String(harga);
    var arr = [];
    var count = 0;
    for(var i = harga.length-1; i>=0; i--){
        if(count === 3 && harga[i] !=undefined){
            arr.push('.');
            arr.push(harga[i]);
            count = 1;
            // console.log(count,i,'MASUK'); 
        }else{
            arr.push(harga[i]);
            count++;
            //console.log(count,i-1);
        }
    }
    //console.log(arr);
    for(var i = arr.length-1; i>=0; i--){
        result += arr[i];
    }
    return result;
}

//console.log(toRupiah(1910450));

function generateData(){
    const dringList = document.getElementById('dringList');
    const cartList = document.getElementById('cartList');
    dringList.innerHTML = '';
    cartList.innerHTML = '';
    
    for(var i =0; i<dring.length; i++){
        let name = dring[i].name;
        let stok = dring[i].stok;
        let harga = dring[i].harga;
        let image = dring[i].image;
      
        let divCard = document.createElement('div');
        divCard.classList.add('card')

    
        let imageData = document.createElement('img')
        imageData.setAttribute("src",image);
        divCard.appendChild(imageData);
    
        let title = document.createElement('p');
        title.innerHTML = name;
        divCard.appendChild(title);

        let divAction = document.createElement('div');
        divAction.classList.add('action');

        let spanData = document.createElement('span');
        spanData.innerHTML = `Rp ${toRupiah(harga)},00 | Stok : ${stok}`;
        divAction.appendChild(spanData);

        let buttonAdd = document.createElement('button');
        buttonAdd.innerHTML = '<i class="fas fa-cart-plus"></i> Pesan';
        buttonAdd.setAttribute('value', i);
        buttonAdd.setAttribute('onclick', 'addtoCart(this.value)');
        divAction.appendChild(buttonAdd);
        divCard.appendChild(divAction);
        //console.log(divCard);
        dringList.appendChild(divCard);
    
    }

    let totalDiv = document.createElement('div');
    totalDiv.classList.add('total');

    let totalh1 = document.createElement('h1');
    totalh1.innerHTML = `TOTAL : Rp${toRupiah(totalHargaMinuman)},00`;
    totalDiv.appendChild(totalh1);

    let totalhr = document.createElement('hr');
    totalDiv.appendChild(totalhr);
    //console.log(totalDiv);
    cartList.appendChild(totalDiv);

    //console.log('BelumMasuk');
    for(var x =0; x<cart.length; x++){
        
        let name = cart[x].name;
        let jumlah = cart[x].jumlah;
        let harga = cart[x].harga;
        let image = cart[x].image;
        //console.log('MASUK');
        let divCardx = document.createElement('div');
        divCardx.classList.add('card-order') ;  
        //console.log(divCardx);

        let divCardDetail = document.createElement('div');
        divCardDetail.classList.add('detail');

        let imageData = document.createElement('img')
        imageData.setAttribute("src",image);
        divCardDetail.appendChild(imageData);
        
        let dringName = document.createElement('p');
        // dringName.setAttribute('id','nameCart')
        dringName.innerHTML = name;
        divCardDetail.appendChild(dringName);

        let dringJumlah = document.createElement('span');
        dringJumlah.innerHTML = jumlah;
        divCardDetail.appendChild(dringJumlah);
        
        divCardx.appendChild(divCardDetail);

        let buttonCancel = document.createElement('button');
        buttonCancel.setAttribute('value', x );
        buttonCancel.setAttribute('id', 'cancelCart' );
        buttonCancel.setAttribute('onclick', 'removedring(this.value)');
        buttonCancel.innerHTML = '<i class="fas fa-trash"></i> Hapus';
        divCardx.appendChild(buttonCancel);
        //console.log(divCardx);
    
        cartList.appendChild(divCardx);
    }

    let divbutton = document.createElement('div');
    divbutton.classList.add("card-finish");

    let buttonOrder = document.createElement('button');
    //buttonOrder.classList.add('order');
    buttonOrder.setAttribute('onclick', 'orderdring()');
    buttonOrder.innerHTML = 'ORDER SEKARANG';
    divbutton.appendChild(buttonOrder);
    cartList.appendChild(divbutton);


  

}
generateData()