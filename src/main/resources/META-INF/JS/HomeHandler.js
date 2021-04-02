/**
 * @Author Chase Staples
 * @DateCreated 03/18/21
 *
 *
 */


liMap = new Map();
userInfoMap = new Map();


function get_list_items(){

    ulList = document.getElementsByTagName('ul');

    for(let i = 0; i < ulList.length; i++){

        let menu = ulList[i].id;

        if(menu == undefined && menu.length == 0){
            console.log(`Menu: ${menu}`);
        }

        liList = ulList[i].getElementsByTagName('li');

        for(let j = 0; j < liList.length; j++){

            let menu = window.sessionStorage.getItem('nav');;
            //console.log(menu);
            let submenu = liList[j].id;
            //console.log(submenu);

            let key = liList[j].id;
            let value = "/" + liList[j].id;

            //console.log(`Menu: ${menu} \n Submenu: ${submenu}`);

            liMap.set(`${menu}${key}`, `${value}`);
        }
    }

    console.log(liMap);
}



window.onload = () => {
    console.log("Navbar:", document.getElementsByTagName('nav')[0].id);
    if((document.getElementsByTagName('nav')[0].id) == 'Student'){
        window.sessionStorage.setItem('nav', (document.getElementsByTagName('nav')[0].id));
        console.log("Student Menu");
    }
    if((document.getElementsByTagName('nav')[0].id) == 'Supervisor'){
        window.sessionStorage.setItem('nav', (document.getElementsByTagName('nav')[0].id));
        console.log("Supervisor Menu");
    }

    get_list_items();

    liList = document.getElementsByTagName('li');

    for(let i = 0; i < liList.length; i++){
        liList[i].onclick = createManualForm;


    }

function createManualForm(url){


    const name = url.srcElement.innerHTML;
    const submenu = url.srcElement.parentElement.id;
    const navbar = window.sessionStorage.getItem('nav');
    const key = `${navbar}${submenu}`;

    console.log(`Name: ${name}`);
    console.log(`Navbar: ${navbar}`)
    console.log(`Submenu: ${submenu}`);
    console.log(`key: ${key}`);
    console.log('Value:', liMap.get(key));

    let toForm = liMap.get(key);

    let form = document.createElement('form');

    form.method = "GET";
    form.action = `${toForm}`;
    form.name = `${submenu}`
    form.id = `${submenu}`

    submitManualForm(form);


}

function submitManualForm(form){
    form.submit();
}}