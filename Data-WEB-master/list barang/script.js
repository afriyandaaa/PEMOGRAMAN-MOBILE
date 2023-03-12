//tangkap beberapa element html
let modal = document.getElementById("modal");
let floting_button = document.getElementById("floting_button");
let modal_bg = document.getElementById("modal_bg");
let addlish_form = document.getElementById("addlish_form");
let root = document.getElementById('root');
let subtitle = document.getElementById('subtitle');



//tambahkan data kesubtitle
subtitle.innerHTML = new Date().toLocaleDateString();


//data list belanja
let data_lish_belanja = [];

//menambahkan event listener ke floting button
floting_button.addEventListener('click', ()=>{

    //memunculkan modal
    if(modal.style.display == "none"){
        showModal();
        return
    }
    //sembunyia kembali
        hideModal();
})

//menambahkan event listener ke modal bg
modal_bg.addEventListener("click",()=>{

    //sembunyia kembali
    hideModal();
})

//tambahkan vent listener submit ke addlist form
addlish_form.addEventListener("submit", (event)=>{

    //stop form dari reload page
    event.preventDefault();

    //tangkap value dari masing masing input field
    let barang = event.target.barang.value
    let harga = event.target.harga.value

    //push data kedalam data list belanja
    data_lish_belanja.push({
        nama_barang : barang,
        harga_barang : harga,
        tanggal : new Date().toLocaleDateString()
    })

    //clear input filed
    event.target.barang.value = "";
    event.target.harga.value = "";

    hideModal();
    readerToHtml();

})

//show modal
function showModal(){
    modal.style.display="flex";
    floting_button.style.backgroundColor = 'gray';
    floting_button.style.transform = 'rotate(45deg)';
}

//hide modal
function hideModal(){
    modal.style.display="none";
    floting_button.style.backgroundColor = '#F280B6';
    floting_button.style.transform = 'rotate(0deg)';
}

//reader function
function readerToHtml(){

    //clear element div
    root.innerHTML = "";

    //perulangan
    data_lish_belanja.forEach((e, i)=>{
        root.innerHTML += `
        <div class="card">
            <small>${e.tanggal}</small>
            <div>
                ${e.nama_barang} <span>Rp. ${e.harga_barang}</span>
            </div>
            <button onclick="handleDelete(${i})">Selesai</button>
        </div>
        `
    });

}

//function untuk delete item pada array datalisthbelanja
function handleDelete(index){

    data_lish_belanja.splice(index, 1);
    readerToHtml();
}