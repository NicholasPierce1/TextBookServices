/**
 * Create new map for li tags
 */

liMap = new Map();


/**
 * @function get_list_items
 *
 * Create a list of ul tags and list items
 * Sets nav bar id and li id to liMap
 * key: nav bar id (ie: SupervisorAddBooks)
 * Value: li id (ie: /AddBooks)
 */
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

/**
 * @onload
 *
 * Determines if the user is a student or supervisor
 * Creates response and set the login user info
 * Creates input tag list for user and student
 * Calls function get_list_items to get the li map
 * For loop for each li to add an onclick listener to create manual forms
 */

window.onload = () => {

    let userInfo = new UserInfo();
    let StudentInfo = new StudentInfo();

    console.log("Navbar:", document.getElementsByTagName('nav')[0].id);
    if((document.getElementsByTagName('nav')[0].id) == 'Student'){
        window.sessionStorage.setItem('nav', (document.getElementsByTagName('nav')[0].id));
        console.log("Student Menu");
    }
    if((document.getElementsByTagName('nav')[0].id) == 'Supervisor'){
        window.sessionStorage.setItem('nav', (document.getElementsByTagName('nav')[0].id));
        console.log("Supervisor Menu");
    }

    let response = JSON.parse(window.getElementById("hiddenInput").value);
    let loginUserInfo = LoginUserInfo.createLoginUserInfoFromJson(response.LoginUserInfo);
    SessionStorage.setItem("loginUserInfo", loginUserInfo);


    UserInfo.getInputTaglist();
    StudentInfo.getInputTaglist();

    get_list_items();

    liList = document.getElementsByTagName('li');

    for(let i = 0; i < liList.length; i++){
        liList[i].onclick = createManualForm;
    }

}

/**
 * @function createManualForm
 * Creates a form manually, sets and logs important information
 * (Name, Navbar ID, Li, Key, Value)
 *
 * @param {1} url
 * the element clicked on
 */
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
    form.name = `${submenu}`;


    submitManualForm(form);


}

/**
 * @function submitManualForm
 * submits the form
 * @param {1} form
 * the form
 */

function submitManualForm(form){
    form.submit();
}