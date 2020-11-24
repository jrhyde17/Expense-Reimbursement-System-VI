function populateRequests() {
    let xhr = new XMLHttpRequest

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 & xhr.status === 200) {
            let reqs = JSON.parse(xhr.response)
            let heading = document.getElementById('title')

            if(Object.keys(reqs).length === 0) {

                let p = document.createElement('P')
                p.innerText = 'No requests have been resolved.'
                heading.insertAdjacentElement("afterend",p)
            } else {
                let table = document.createElement('table')
                let headrow = document.createElement('tr')
                let th1 = document.createElement('th')
                let th2 = document.createElement('th')
                let th3 = document.createElement('th')
                let th4 = document.createElement('th')
                let th5 = document.createElement('th')
                let th6 = document.createElement('th')
                let th7 = document.createElement('th')
                th1.innerText = 'ID'
                th2.innerText = 'Employee'
                th3.innerText = 'Amount'
                th4.innerText = 'Summary'
                th5.innerText = 'Image'
                th6.innerText = 'Status'
                th7.innerText = 'Manager'
                headrow.append(th1)
                headrow.append(th2)
                headrow.append(th3)
                headrow.append(th4)
                headrow.append(th5)
                headrow.append(th6)
                headrow.append(th7)
                table.append(headrow)

                for(let i = 0; i < reqs.length; i++) {
                    let row = document.createElement('tr')
                    let req = reqs[i];
                    let td1 = document.createElement('td')
                    let td2 = document.createElement('td')
                    let td3 = document.createElement('td')
                    let td4 = document.createElement('td')
                    let td5 = document.createElement('td')
                    let td6 = document.createElement('td')
                    let td7 = document.createElement('td')

                    td1.innerText = req.id
                    td2.innerText = req.employee
                    td3.innerText = req.amount
                    td4.innerText = req.summary
                    
                    if(req.fileContent.buf.length > 0) {
                   		let img = document.createElement('img')
                    	img.src = "data:image/png;base64," + btoa(req.fileContent.buf);
                    	td5.append(img)
                    } else {
                    	td5.append('no image')
                    }

                    td6.innerText = req.status
                    td7.innerText = req.manager
                    
                    
                    row.append(td1)
                    row.append(td2)
                    row.append(td3)
                    row.append(td4)
                    row.append(td5)
                    row.append(td6)
                    row.append(td7)
                    table.append(row)
                }

                heading.insertAdjacentElement("afterend",table)

            }

        }
    }

    xhr.open('POST','../../GetRequests')
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send('level=manager&status=resolved')
}

window.onload = () => {
    populateRequests()
}