function loadInfo() {
    let xhr = new XMLHttpRequest
    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 & xhr.status === 200) {
            let flags = JSON.parse(xhr.response)
            let infoBox = document.getElementById('infobox')
            infoBox.innerText = flags.info
        }
    }
    
    xhr.open('GET','../AjaxEndpoint')
    xhr.send()
}

window.onload = () => {
    loadInfo()
}