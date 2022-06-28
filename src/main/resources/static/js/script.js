const dialogCollection = document.getElementsByClassName('dialog')
const openCollection = document.getElementsByClassName('open-button')
const closeCollection = document.getElementsByClassName('close-button')
const addPatient = document.querySelector('.addButton')
const addDialog = document.querySelector('.dialogAdd')
const closeAddDialog = document.querySelector('.close-button-dialogAdd')
const dialogAddError = document.getElementById('dialogAddError')


for (let i = 0; i < openCollection.length; i++) {
    openCollection[i].addEventListener('click',function (){
        const dialog = dialogCollection.item(i)
        dialog.showModal();
    });

}

for (let i = 0; i < closeCollection.length; i++) {
    closeCollection[i].addEventListener('click',function (){
        const close = dialogCollection.item(i)
        close.close();
    });
}

addPatient.addEventListener('click', function () {
    addDialog.showModal();
});

closeAddDialog.addEventListener('click', function () {
    addDialog.close();
})

dialogAddError.showModal();