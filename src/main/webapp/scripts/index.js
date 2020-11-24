function getLoginStatus() {
    let xhr = new XMLHttpRequest()

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 & xhr.status === 200) {
            let flags = JSON.parse(xhr.response)

            if(flags.loginFailed) {
                let form = document.getElementById('formDiv');
                let div = document.createElement('div')
                div.id = 'errorDiv'
                form.insertAdjacentElement('beforeend',div)
                let msg = document.createElement('p')
                msg.id = 'error'
                msg.innerText = 'Login Failed'
                div.append(msg)
            }
        }
    }

    xhr.open('GET','./AjaxEndpoint')
    xhr.send()
}

function flushLoginStatus() {
    let xhr = new XMLHttpRequest()
    xhr.open('POST','./AjaxEndpoint')
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send('action=flushLogin')
}

window.onload = () => {
    getLoginStatus()
    flushLoginStatus()
}