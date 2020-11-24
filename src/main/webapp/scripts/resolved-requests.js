function populateRequests() {
    let xhr = new XMLHttpRequest

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 & xhr.status === 200) {
            let reqs = JSON.parse(xhr.response)
            let heading = document.getElementById('title')

            if(Object.keys(reqs).length === 0) {

                let p = document.createElement('P')
                p.innerText = 'You have no resolved requests.'
                heading.insertAdjacentElement("afterend",p)
            } else {
                let table = document.createElement('table')
                let headrow = document.createElement('tr')
                let th1 = document.createElement('th')
                let th2 = document.createElement('th')
                let th3 = document.createElement('th')
                let th4 = document.createElement('th')
                let th5 = document.createElement('th')
                th1.innerText = 'ID'
                th2.innerText = 'Amount'
                th3.innerText = 'Summary'
                th4.innerText = 'Image'
                th5.innerText = 'Status'
                headrow.append(th1)
                headrow.append(th2)
                headrow.append(th3)
                headrow.append(th4)
                headrow.append(th5)
                table.append(headrow)

                for(let i = 0; i < reqs.length; i++) {
                    let row = document.createElement('tr')
                    let req = reqs[i];
                    let td1 = document.createElement('td')
                    let td2 = document.createElement('td')
                    let td3 = document.createElement('td')
                    let td4 = document.createElement('td')
                    let td5 = document.createElement('td')

                    td1.innerText = req.id
                    td2.innerText = req.amount
                    td3.innerText = req.summary
                    
                    if(req.fileContent.buf.length > 0) {
                   		let img = document.createElement('img')
                    	img.src = "data:image/png;base64," + btoa(req.fileContent.buf);
                    	td4.append(img)
                    } else {
                    	td4.append('no image')
                    }

                    td5.innerText = req.status
                    
                    row.append(td1)
                    row.append(td2)
                    row.append(td3)
                    row.append(td4)
                    row.append(td5)
                    table.append(row)
                }

                heading.insertAdjacentElement("afterend",table)

            }

        }
    }

    xhr.open('POST','../GetRequests')
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send('level=employee&status=resolved')
}

window.onload = () => {
    populateRequests()
}