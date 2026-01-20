console.log("Theme script loaded");

let currentTheme = getTheme();
changeTheme(currentTheme);

function changeTheme() {
    //set theme to web page
    document.querySelector("html").classList.add(currentTheme);
    //set listener to chnage theme htton
    const changethemebutton = document.querySelector("#theme_change_button");

    
   
    changethemebutton.addEventListener("click", function(event) {
        const oldtheme = currentTheme;
        console.log("chnaged theme button clciked");
        if (currentTheme === 'dark') {
            currentTheme = 'light';

        }else{
            currentTheme = 'dark';  

        }
        //local storage update
        setTheme(currentTheme);
        //update web page
         document.querySelector('html').classList.remove(oldtheme);
        document.querySelector("html").classList.add(currentTheme);

        // change text of button
        changethemebutton.querySelector('span').textContent = currentTheme=="light"?"dark":"light";
    });
}
// set theme to local storage
function setTheme(theme){
    localStorage.setItem('theme', theme);

}


//get theme from local storage
function getTheme(theme){
    let themme = localStorage.getItem('theme');
    if(themme)
        return themme
    else
        return 'light';
}